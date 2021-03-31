package diplom.blog.controller;

import diplom.blog.api.response.PostResponse;
import diplom.blog.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ApiPostController {

    private final PostService postService;


    public ApiPostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/post")
    private PostResponse posts() {
        return postService.getPost();

    }
}
