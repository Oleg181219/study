package diplom.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiPostController {
    //    GET /api/post
    @GetMapping("/posts")
    public String post(Model model) {
        model.addAttribute("post", "Post page");
        return "index";
    }

//    GET /api/post/my
//    GET /api/post/search
//    GET /api/post/byDate
//    GET /api/post/byTag
//    GET /api/post/{ID}
//    GET /api/post/moderation
//    GET /api/post/my
//    POST /api/post
//    PUT /api/post/{ID}
//    POST /api/post/like
//    POST /api/post/dislike


}
