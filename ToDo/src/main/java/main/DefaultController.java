package main;

import main.model.ToDo;
import main.model.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


@Controller
public class DefaultController {

    @Autowired
    private ToDoRepository toDoRepository;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<ToDo> toDoIterable = toDoRepository.findAll();
        ArrayList<ToDo> toDoArrayList = new ArrayList<>();
        for (ToDo toDo: toDoIterable){
            toDoArrayList.add(toDo) ;
        }
        model.addAttribute("toDoArrayList",toDoArrayList);
        model.addAttribute("todoCount", toDoArrayList.size());
        return "index";
    }
}
