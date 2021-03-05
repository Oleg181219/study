package studySpring.SpringMVC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import studySpring.SpringMVC.models.Post;
import studySpring.SpringMVC.repo.PostRepository;

@Controller
public class BlogController {


    private PostRepository postRepository;

    public BlogController(PostRepository postRepository){
        this.postRepository = postRepository;

    }

    public BlogController(){
    }

    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }
}
