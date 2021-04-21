package diplom.blog.service;


import com.github.cage.Cage;
import com.github.cage.YCage;
import diplom.blog.api.response.AuthResponse;
import diplom.blog.model.CaptchaCodes;
import diplom.blog.model.DtoModel.CaptchaDTO;
import diplom.blog.model.DtoModel.NewUserDTO;
import diplom.blog.model.User;
import diplom.blog.repo.CaptchaCodesRepository;
import diplom.blog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Component
public class AuthService {

    @Value("${blog.lifeTimeCaptchaCode}")
    private String lifeTimeCaptchaCodeString;
    private Cage cage;
    private final CaptchaCodesRepository captchaCodesRepository;
    private final UserRepository userRepository;
    private final String CAPTCHA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private final String CODE = "abcdefghijklmnopqrstuvwxyz1234567890";
    HashMap<String, String> respMap = new HashMap<>();
    private StringBuilder secretCode;
    private StringBuilder captchaBaseCode;
    private AuthResponse authResponse;

    public AuthService(CaptchaCodesRepository captchaCodesRepository
            , UserRepository userRepository) {
        this.captchaCodesRepository = captchaCodesRepository;
        this.userRepository = userRepository;
    }


    public AuthResponse register(NewUserDTO user) {


        List<User> emailResp = userRepository.findByEmail(user.getEmail());
        CaptchaCodes capCod = captchaCodesRepository.findBySecretCode(user.getCaptchaSecret());
        cage = new YCage();

        if (user.getPassword().length() <= 6) {
            System.out.println("user.getPassword() - " + user.getPassword());
            respMap.put("password", "Пароль короче 6-ти символов");
        }
        if (emailResp.size() > 0) {
            respMap.put("email", "Этот e-mail уже зарегистрирован");
            System.out.println("emailResp.size() - " + emailResp.size());
        }
        if (!user.getName().matches("([А-Яа-яA-Za-z0-9-_]+)")) {
            System.out.println("user.getName().matches(\"([А-Яа-яA-Za-z0-9-_]+) -  " + user.getName().matches("([А-Яа-яA-Za-z0-9-_]+)"));
            respMap.put("name", "Имя указано неверно");
        }

        BufferedImage image = cage.drawImage(new StringBuilder("data:image/png;base64,")
                .append(user.getCaptcha())
                .toString());

        if (capCod.getCode().equals(createCaptchaString(image))) {

            System.out.println("capCod.getCode().equals(createCaptchaString(image))  - " + capCod.getCode().equals(createCaptchaString(image)));
            respMap.put("captcha", "Код с картинки введён неверно");
        }

        authResponse = new AuthResponse();

        if (respMap.isEmpty()) {
            authResponse.setResult(true);
            return authResponse;
        } else if (!respMap.isEmpty()) {
            authResponse.setResult(false);
            authResponse.setErrors(respMap);
        }
        return authResponse;
    }

    public CaptchaDTO captcha() {
        cage = new YCage();
        Date dateForComparisons = new Date(new Date().getTime() - (Long.parseLong(lifeTimeCaptchaCodeString) * 1000));
        captchaCodesRepository.deleteAllByTimeBefore(dateForComparisons);
        BufferedImage image = cage.drawImage(generateCaptcha());
        captchaBaseCode.append(createCaptchaString(image));
        captchaCodesRepository.save(new CaptchaCodes(new Date(), captchaBaseCode.toString(), secretCode.toString()));
        return new CaptchaDTO(secretCode.toString(), captchaBaseCode.toString());

    }

    private byte[] toByteArray(BufferedImage bi, String format)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;

    }

    private String generateCaptcha() {
        secretCode = new StringBuilder();
        captchaBaseCode = new StringBuilder("data:image/png;base64,");
        StringBuffer captchaBuffer = new StringBuffer();
        Random random = new Random();
        int codeLenth = 15 + (int) (Math.random() * 10);
        for (int i = 0; i < codeLenth; i++) {
            int index = (int) (random.nextFloat() * CODE.length());
            secretCode.append(CODE.charAt(index));
        }
        int captchaLength = 4 + (int) (Math.random() * 2);
        while (captchaBuffer.length() < captchaLength) {
            int index = (int) (random.nextFloat() * CAPTCHA.length());
            captchaBuffer.append(CAPTCHA.charAt(index));
        }
        return captchaBuffer.toString();
    }

    private BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }

    private String createCaptchaString(BufferedImage image) {
        BufferedImage resizedImage = (resize(image, 100, 35));
        byte[] bytes = new byte[0];
        try {
            bytes = toByteArray(resizedImage, "png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(bytes);
    }
}
