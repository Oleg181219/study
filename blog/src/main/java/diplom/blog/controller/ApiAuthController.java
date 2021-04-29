package diplom.blog.controller;

import diplom.blog.api.request.LoginRequest;
import diplom.blog.api.response.AuthResponse;
import diplom.blog.api.response.LoginResponse;
import diplom.blog.model.DtoModel.CaptchaDTO;
import diplom.blog.model.DtoModel.NewUserDTO;
import diplom.blog.repo.UserRepository;
import diplom.blog.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {


    private final UserRepository userRepository;
    private final AuthService authService;

    public ApiAuthController(UserRepository userRepository, AuthService authService) {

        this.userRepository = userRepository;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/check")
    private ResponseEntity<LoginResponse> check(Principal principal) {
        if (principal == null) {
            return ResponseEntity.ok(new LoginResponse());
        }
        return authService.check(principal);
    }

    @GetMapping("/captcha")
    private CaptchaDTO captcha() {
        return authService.captcha();
    }


    @PostMapping("/register")
    private AuthResponse register(@RequestBody @Valid NewUserDTO user) {
        return authService.register(user);
    }


}
