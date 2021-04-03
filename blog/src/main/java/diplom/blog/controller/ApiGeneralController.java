package diplom.blog.controller;

import diplom.blog.api.response.InitResponse;
import diplom.blog.api.response.SettingsResponse;
import diplom.blog.api.response.TagResponse;
import diplom.blog.service.PostService;
import diplom.blog.service.SettingsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {

    private final InitResponse initResponse;
    private final SettingsService settingsService;
    private final PostService postService;

    public ApiGeneralController(InitResponse initResponse, SettingsService settingsService, PostService postService) {
        this.initResponse = initResponse;
        this.settingsService = settingsService;
        this.postService = postService;
    }

    @GetMapping("/settings")
    private SettingsResponse settings()  {
        return settingsService.getGlobalSettings();

    }

    @GetMapping("/init")
    private InitResponse init() {
        return initResponse;
    }

    @GetMapping("/tag")
    private TagResponse tag(){
        return postService.getTags();
    }

}
