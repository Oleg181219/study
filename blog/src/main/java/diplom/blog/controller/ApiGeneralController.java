package diplom.blog.controller;

import diplom.blog.api.request.CommentRequest;
import diplom.blog.api.request.ModerationRequest;
import diplom.blog.api.request.SettingRequest;
import diplom.blog.api.response.*;
import diplom.blog.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {

    private final InitResponse initResponse;
    private final SettingsService settingsService;
    private final PostService postService;
    private final TagService tagService;
    private final StatisticsService statisticsService;
    private final StorageService storageService;

    public ApiGeneralController(InitResponse initResponse
            , SettingsService settingsService
            , PostService postService
            , TagService tagService
            , StatisticsService statisticsService
            , StorageService storageService) {
        this.initResponse = initResponse;
        this.settingsService = settingsService;
        this.postService = postService;
        this.tagService = tagService;
        this.statisticsService = statisticsService;
        this.storageService = storageService;
    }

    @GetMapping("/settings")
    public SettingsResponse settings() {
        return settingsService.getGlobalSettings();

    }

    @PutMapping("/settings")
    public SettingsResponse setSettings(@RequestBody SettingRequest settingRequest
            , Principal principal) {
        return settingsService.setGlobalSettings(settingRequest, principal);

    }

    @GetMapping("/init")
    public InitResponse init() {
        return initResponse;
    }

    @GetMapping("/tag")
    public TagResponse tag(@RequestParam(required = false) String query) {
        return tagService.getTags(query);
    }

    @GetMapping("/calendar")
    public CalendarResponse calendar() {
        return postService.calendar();
    }

    @PostMapping("/moderation")
    public ResponseEntity<ModerationResponse> moderation(@RequestBody ModerationRequest moderationRequest
            , Principal principal) {
        return postService.moderation(moderationRequest, principal);
    }

    @PostMapping("/comment")
    public ResponseEntity<?> comment(@RequestBody CommentRequest commentRequest, Principal principal) {
        return postService.comment(commentRequest, principal);
    }

    @GetMapping("/statistics/my")
    public StatisticResponse myStatistic(Principal principal) {
        return statisticsService.myStatistics(principal);
    }

    @GetMapping("/statistics/all")
    public StatisticResponse allStatistic(Principal principal) {
        return statisticsService.allStatistics(principal);
    }

    @PostMapping(value = "/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image
            , Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String pathToSavedFile = storageService.store(image);
        UriComponents uri = UriComponentsBuilder.newInstance()
                .path("/{root}/{file_uri}")
                .buildAndExpand(storageService.getRootLocation(), pathToSavedFile);
        System.out.println("uri.toUriString()    "+uri.toUriString());
        return ResponseEntity.ok(uri.toUriString().substring(26));
    }


}
