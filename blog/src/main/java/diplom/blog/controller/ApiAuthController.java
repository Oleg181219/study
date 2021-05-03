package diplom.blog.controller;

import diplom.blog.api.request.LoginRequest;
import diplom.blog.api.response.AuthResponse;
import diplom.blog.api.response.LoginResponse;
import diplom.blog.api.response.LogoutResponse;
import diplom.blog.model.DtoModel.CaptchaDTO;
import diplom.blog.model.DtoModel.NewUserDTO;
import diplom.blog.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {



    private final AuthService authService;

    public ApiAuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(){
        return authService.logout();
    }

    @GetMapping("/check")
    public ResponseEntity<LoginResponse> check(Principal principal) {
        if (principal == null) {
            return ResponseEntity.ok(new LoginResponse());
        }
        return authService.check(principal);
    }

    @GetMapping("/captcha")
    public CaptchaDTO captcha() {
        return authService.captcha();
    }


    @PostMapping("/register")
    public AuthResponse register(@RequestBody @Valid NewUserDTO user) {
        return authService.register(user);
    }


}
