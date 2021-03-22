package diplom.blog.controller;

import diplom.blog.api.response.CheckResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

private final CheckResponse checkResponse;

    public ApiAuthController(CheckResponse checkResponse) {
        this.checkResponse = checkResponse;
    }

    @GetMapping("/check")
    private CheckResponse check(){
        return checkResponse;
    }

//    GET /api/auth/check
//    POST /api/auth/login
//    GET /api/auth/logout
//    GET /api/auth/captcha
//    POST /api/auth/register
//    POST /api/auth/restore
//    POST /api/auth/password
}
