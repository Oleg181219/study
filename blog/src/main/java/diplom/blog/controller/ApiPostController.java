package diplom.blog.controller;

import diplom.blog.api.response.PostResponse;
import diplom.blog.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/api")
public class ApiPostController {

    private final PostService postService;


    public ApiPostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/post")
    private PostResponse posts(@RequestParam("mode") String mode) {
        return postService.getPost(mode);
    }

    @GetMapping("/post/search")
    private PostResponse postsSearch(@RequestParam("query") String query) {
        return postService.getPostsSearch(query);
    }

    @GetMapping("/post/byDate")
    private PostResponse postSearchByDate(@RequestParam("date")String date){
        return postService.getPostSearchByDate(date);
    }
}
