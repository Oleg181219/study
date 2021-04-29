package diplom.blog.controller;

import diplom.blog.api.response.PostResponse;
import diplom.blog.model.DtoModel.PostByIdDTO;
import diplom.blog.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api")
public class ApiPostController {

    private final PostService postService;


    public ApiPostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/post")
//    @PreAuthorize("hasAuthority('user:write')")
    private PostResponse posts(@RequestParam("mode") String mode,
                               @RequestParam("offset") int offset,
                               @RequestParam("limit") int limit) {
        return postService.allPost(offset, limit, mode);
    }

    @GetMapping("/post/search")
//    @PreAuthorize("hasAuthority('user:write')")
    private PostResponse postsSearch(@RequestParam("offset") int offset,
                                     @RequestParam("limit") int limit,
                                     @RequestParam("query") String query) {
        return postService.postsSearch(offset, limit, query);
    }

    @GetMapping("/post/byDate")
//    @PreAuthorize("hasAuthority('user:write')")
    private PostResponse postSearchByDate(@RequestParam("offset") int offset,
                                          @RequestParam("limit") int limit,
                                          @RequestParam("date") String date) {
        return postService.postsSearchByDate(offset, limit, date);
    }

    @GetMapping("/post/byTag")
//    @PreAuthorize("hasAuthority('user:write')")
    private PostResponse postSearchByTag(@RequestParam("offset") int offset,
                                         @RequestParam("limit") int limit,
                                         @RequestParam("tag") String tag) {
        return postService.postsSearchByTag(offset, limit, tag);
    }

    @GetMapping("/post/{id}")
//    @PreAuthorize("hasAuthority('user:write')")
    private PostByIdDTO postSearchById(@PathVariable long id,
                                       Principal principal) {
        if (principal == null) {
            return postService.findPostById(id, "empty");

        } else return postService.findPostById(id, principal.getName());

    }


    @RequestMapping(method = {RequestMethod.OPTIONS, RequestMethod.GET}, value = "/**/{path:[^\\\\.]*}")
    public String redirectToIndex() {
        return "forward:/";
    }
}
