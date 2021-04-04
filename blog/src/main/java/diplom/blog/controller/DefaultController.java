package diplom.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
public class DefaultController {

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("title", "Main page");
        return "index";
    }

    @RequestMapping(value = "/posts/recent", method = RequestMethod.GET)
    public String redirectRecent() {
        return "index";
    }

    @RequestMapping(value = "/posts/popular", method = RequestMethod.GET)
    public String redirectPopular() {
        return "index";
    }

    @RequestMapping(value = "/posts/best", method = RequestMethod.GET)
    public String redirectBest() {
        return "index";
    }

    @RequestMapping(value = "/posts/early", method = RequestMethod.GET)
    public String redirectEarly() {
        return "index";
    }
}
