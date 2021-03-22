package diplom.blog.controller;

import diplom.blog.api.response.InitResponse;
import diplom.blog.api.response.SettingsResponse;
import diplom.blog.service.SettingsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {

    private final InitResponse initResponse;
    private final SettingsService settingsService;

    public ApiGeneralController(InitResponse initResponse, SettingsService settingsService) {
        this.initResponse = initResponse;
        this.settingsService = settingsService;
    }

    @GetMapping("/settings")
    private SettingsResponse settings() {
        return settingsService.getGlobalSettings();

    }

    @GetMapping("/init")
    private InitResponse init() {

        return initResponse;
    }
//    GET /api/init
//    GET /api/settings
//    GET /api/tag
//    GET /api/calendar
//    POST /api/comment
//    POST /api/image
//    POST /api/moderation
//    POST /api/profile/my
//    GET /api/statistics/my
//    GET /api/statistics/all
//    PUT /api/settings

}
