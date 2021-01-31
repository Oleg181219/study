package main;

import main.model.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.ToDo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    /**
     * @return получает весь список
     */
    @GetMapping("/todo")
    public List<ToDo> list() {
       Iterable<ToDo> toDoIterable = toDoRepository.findAll();
       ArrayList<ToDo>  toDoArrayList = new ArrayList<>();
       for (ToDo toDo: toDoIterable){
           toDoArrayList.add(toDo) ;
       }
        return toDoArrayList;
    }

    /**
     * @return вносит 1 дело
     */
    @PostMapping("/todo")
    public int add(ToDo toDo) {
        ToDo newToDo = toDoRepository.save(toDo);
        return newToDo.getId();
    }

    /**
     * @return получает дело по id
     */
    @GetMapping("/todo/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<ToDo> optionalToDo=toDoRepository.findById(id);
        if (!optionalToDo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalToDo.get(), HttpStatus.OK);
    }

    /**
     * удаление 1 по id
     */
    @DeleteMapping("/todo/{id}")
    public ResponseEntity deleteTodoById(@PathVariable int id) {
        Optional<ToDo> optionalToDo=toDoRepository.findById(id);

        if (!optionalToDo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        toDoRepository.delete(optionalToDo.get());
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * очистить весь список
     */
    @DeleteMapping("/todo")
    public ResponseEntity deleteAllToDo(){
        toDoRepository.deleteAll();
        return new ResponseEntity(HttpStatus.OK);
    }
}
