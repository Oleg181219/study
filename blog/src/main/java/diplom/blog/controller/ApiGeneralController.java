package diplom.blog.controller;

import diplom.blog.api.response.CalendarResponse;
import diplom.blog.api.response.InitResponse;
import diplom.blog.api.response.SettingsResponse;
import diplom.blog.api.response.TagResponse;
import diplom.blog.service.PostService;
import diplom.blog.service.SettingsService;

import diplom.blog.service.TagService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {

    private final InitResponse initResponse;
    private final SettingsService settingsService;
    private final PostService postService;
    private final TagService tagService;

    public ApiGeneralController(InitResponse initResponse
            , SettingsService settingsService
            , PostService postService
            , TagService tagService) {
        this.initResponse = initResponse;
        this.settingsService = settingsService;
        this.postService = postService;
        this.tagService = tagService;
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
    private TagResponse tag(@RequestParam(required = false) String query){
        return tagService.getTags(query);

    }

    @GetMapping("/calendar")
    private CalendarResponse calendar(){
        return postService.calendar();
    }



}
