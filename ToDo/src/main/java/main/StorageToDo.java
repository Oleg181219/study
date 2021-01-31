package main;

import main.model.ToDo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class StorageToDo {
    private static HashMap<Integer, ToDo> toDoList = new HashMap();
    private static int toDoId = 1;

    public static HashMap<Integer, ToDo> getToDoList() {
        return toDoList;
    }

    public static void setToDoList(HashMap<Integer, ToDo> toDoList) {
        StorageToDo.toDoList = toDoList;
    }

    public static List<ToDo> getAllToDo() {
        ArrayList<ToDo> listToDo = new ArrayList<>();
        listToDo.addAll(toDoList.values());
        return listToDo;
    }

    public static int addToDo(ToDo todo) {
        int id = toDoId++;
        todo.setId(id);
        toDoList.put(id, todo);
        return id;
    }

    public static ToDo getToDo(int toDoId) {
        if (toDoList.containsKey(toDoId)) {
            return toDoList.get(toDoId);
        }
        return null;
    }

    public static ToDo deleteToDo(int toDoId) {
        if (toDoList.containsKey(toDoId)) {
            return toDoList.remove(toDoId);
        }
        return null;
    }

    public static void deleteAllToDo(){
        StorageToDo.toDoList.clear();
    }

}
