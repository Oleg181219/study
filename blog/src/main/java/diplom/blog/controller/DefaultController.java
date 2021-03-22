package diplom.blog.controller;

import diplom.blog.api.response.InitResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;


@Controller
public class DefaultController {

    private final InitResponse initResponse;

    public DefaultController(InitResponse initResponse) {
        this.initResponse = initResponse;
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("title", "Main page");
        return "index";
    }
}