package diplom.blog.service;


import com.github.cage.Cage;
import com.github.cage.YCage;
import diplom.blog.api.request.LoginRequest;
import diplom.blog.api.response.AuthResponse;
import diplom.blog.api.response.LoginResponse;
import diplom.blog.api.response.LogoutResponse;
import diplom.blog.api.response.UserLoginResponse;
import diplom.blog.model.CaptchaCode;
import diplom.blog.model.DtoModel.CaptchaDTO;
import diplom.blog.model.DtoModel.NewUserDTO;
import diplom.blog.model.User;
import diplom.blog.repo.CaptchaCodesRepository;
import diplom.blog.repo.GlobalSettingsRepository;
import diplom.blog.repo.PostRepository;
import diplom.blog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Component
public class AuthService {

    private final GlobalSettingsRepository globalSettingsRepository;
    private final AuthenticationManager authenticationManager;
    private final CaptchaCodesRepository captchaCodesRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    @Value("${blog.lifeTimeCaptchaCode}")
    private String lifeTimeCaptchaCodeString;
    private Cage cage;
    private StringBuilder secretCode;
    private StringBuilder captchaBaseCode;

    public AuthService(GlobalSettingsRepository globalSettingsRepository
            , AuthenticationManager authenticationManager
            , CaptchaCodesRepository captchaCodesRepository
            , UserRepository userRepository
            , PostRepository postRepository) {
        this.globalSettingsRepository = globalSettingsRepository;
        this.authenticationManager = authenticationManager;
        this.captchaCodesRepository = captchaCodesRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }


    public AuthResponse register(NewUserDTO user) {
        HashMap<String, String> respMap = new HashMap<>();
        var emailResp = userRepository.findByEmail(user.getEmail());
        var capCod = captchaCodesRepository.findBySecretCode(user.getCaptchaSecret());
        cage = new YCage();
        if (user.getPassword().length() <= 6) {
            respMap.put("password", "Пароль короче 6-ти символов");
        }
        if (emailResp != null) {
            respMap.put("email", "Этот e-mail уже зарегистрирован");
        }
        if (!user.getName().matches("([А-Яа-яA-Za-z0-9-_]+)")) {
            respMap.put("name", "Имя указано неверно. ");
        }
        BufferedImage image = cage.drawImage(user.getCaptcha());
        if (capCod.getCode().equals(createCaptchaString(image))) {
            respMap.put("captcha", "Код с картинки введён неверно");
        }
        var authResponse = new AuthResponse();
        if (respMap.isEmpty()) {
            authResponse.setResult(true);

            var newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setName(user.getName());
            newUser.setPassword(passwordEncoder().encode(user.getPassword()));
            newUser.setIsModerator(-1);
            newUser.setRegTime(new Date());
            userRepository.save(newUser);

            return authResponse;
        } else {
            authResponse.setResult(false);
            authResponse.setErrors(respMap);
        }
        return authResponse;
    }

    //=================================================================================
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        var authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail()
                        , loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return ResponseEntity.ok(getLoginResponse(user.getUsername()));
    }

    //=================================================================================
    public ResponseEntity<LogoutResponse> logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        var logoutResponse = new LogoutResponse();
        logoutResponse.setResult(true);
        return ResponseEntity.ok(logoutResponse);
    }

    //=================================================================================
    public ResponseEntity<LoginResponse> check(Principal principal) {
        return ResponseEntity.ok(getLoginResponse(principal.getName()));
    }

    //=================================================================================
    public CaptchaDTO captcha() {
        cage = new YCage();
        var dateForComparisons = new Date(new Date().getTime() - (Long.parseLong(lifeTimeCaptchaCodeString) * 1000));
        captchaCodesRepository.deleteAllByTimeBefore(dateForComparisons);

        BufferedImage image = cage.drawImage(generateCaptcha());
        captchaBaseCode.append(createCaptchaString(image));
        captchaCodesRepository.save(new CaptchaCode(new Date(), captchaBaseCode.toString(), secretCode.toString()));
        return new CaptchaDTO(secretCode.toString(), captchaBaseCode.toString());

    }

    //----------------------------------------------------------------------------------
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    private byte[] toByteArray(BufferedImage bi)
            throws IOException {

        var baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "png", baos);
        return baos.toByteArray();

    }

    private String generateCaptcha() {
        secretCode = new StringBuilder();
        captchaBaseCode = new StringBuilder("data:image/png;base64,");
        var captchaBuffer = new StringBuilder();
        var random = new Random();
        int codeLength = 15 + (random.nextInt() * 10);
        for (var i = 0; i < codeLength; i++) {
            var code = "abcdefghijklmnopqrstuvwxyz1234567890";
            int index = (random.nextInt() * code.length());
            secretCode.append(code.charAt(index));
        }
        int captchaLength = 4 +  (random.nextInt() * 2);
        while (captchaBuffer.length() < captchaLength) {
            var captcha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            int index =  (random.nextInt() * captcha.length());
            captchaBuffer.append(captcha.charAt(index));
        }
        return captchaBuffer.toString();
    }

    private BufferedImage resize(BufferedImage img) {
        Image tmp = img.getScaledInstance(100, 35, Image.SCALE_SMOOTH);
        var dimg = new BufferedImage(100, 35, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }

    private String createCaptchaString(BufferedImage image) {
        BufferedImage resizedImage = (resize(image));
        var bytes = new byte[0];
        try {
            bytes = toByteArray(resizedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(bytes);
    }

    private LoginResponse getLoginResponse(String email) {
        var curentUser = userRepository.findByEmail(email);

        var userLoginResponse = new UserLoginResponse();
        userLoginResponse.setEmail(curentUser.getEmail());
        userLoginResponse.setModeration(curentUser.getIsModerator() == 1);
        int newPosts = postRepository.findAllByModerationStatus().size();
        userLoginResponse.setModerationCount(curentUser.getIsModerator() == 1 ? newPosts : 0);
        userLoginResponse.setId((long) curentUser.getId());
        userLoginResponse.setName(curentUser.getName());
        userLoginResponse.setPhoto(curentUser.getPhoto());
        userLoginResponse.setSettings(Objects.equals(globalSettingsRepository.getGlobalSettingsById(3L).getValue(), "YES"));

        var loginResponse = new LoginResponse();
        loginResponse.setResult(true);
        loginResponse.setUserLoginResponse(userLoginResponse);

        return loginResponse;
    }
}
