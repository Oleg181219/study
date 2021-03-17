package diplom.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DefaultController {


    @GetMapping("")
    public String main(Model model) {
        model.addAttribute("title", "Main page");
        return "index";
    }
}
