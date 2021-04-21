package diplom.blog.controller;

import diplom.blog.api.response.AuthResponse;
import diplom.blog.api.response.CheckResponse;
import diplom.blog.model.DtoModel.CaptchaDTO;
import diplom.blog.model.DtoModel.NewUserDTO;
import diplom.blog.service.AuthService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ApiAuthController {

    private final CheckResponse checkResponse;
    private final AuthService authService;

    public ApiAuthController(CheckResponse checkResponse
            , AuthService authService) {
        this.checkResponse = checkResponse;
        this.authService = authService;
    }

    @GetMapping("/api/auth/check")
    private CheckResponse check() {
        return checkResponse;
    }

    @GetMapping("/api/auth/captcha")
    private CaptchaDTO captcha() {
        return authService.captcha();
    }


    @PostMapping("/api/auth/register")
    private AuthResponse register(@RequestBody @Valid NewUserDTO user) {
        return authService.register(user);

    }

}
