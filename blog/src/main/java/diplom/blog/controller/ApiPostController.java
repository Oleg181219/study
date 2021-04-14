package diplom.blog.controller;

import diplom.blog.api.response.PostResponse;
import diplom.blog.model.DtoModel.PostByIdDTO;
import diplom.blog.model.DtoModel.PostDTO;
import diplom.blog.service.PostService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ApiPostController {

    private final PostService postService;


    public ApiPostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/post")
    private PostResponse posts(@RequestParam("mode") String mode,
                               @RequestParam("offset") int offset,
                               @RequestParam("limit") int limit) {
        return postService.getPost(offset, limit, mode);
    }

    @GetMapping("/post/search")
    private PostResponse postsSearch(@RequestParam("offset") int offset,
                                     @RequestParam("limit") int limit,
                                     @RequestParam("query") String query) {
        return postService.getPostsSearch(offset, limit, query);
    }

    @GetMapping("/post/byDate")
    private PostResponse postSearchByDate(@RequestParam("offset") int offset,
                                          @RequestParam("limit") int limit,
                                          @RequestParam("date") String date) {
        return postService.getPostSearchByDate(offset, limit, date);
    }

    @GetMapping("/post/byTag")
    private PostResponse postSearchByTag(@RequestParam("offset") int offset,
                                         @RequestParam("limit") int limit,
                                         @RequestParam("tag") String tag) {
        return postService.getPostSearchByTag(offset, limit, tag);
    }
    @GetMapping("/post/{id}")
    private PostByIdDTO postSearchById(@PathVariable long id) {
        return postService.findById(id);
    }


    @RequestMapping(method = {RequestMethod.OPTIONS, RequestMethod.GET}, value = "/**/{path:[^\\\\.]*}")
    public String redirectToIndex() {
        return "forward:/";
    }
}
