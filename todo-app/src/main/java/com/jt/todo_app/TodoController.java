package com.jt.todo_app;


import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;





@Controller
public class TodoController {
    private final List<Todo> todos;
    public TodoController(List<Todo> todos) {
        // Initialize with some default todos
      this.todos=  todos  ;
    }
    @GetMapping
  
    public String home(Model model) {
        System.out.println("Inside home method");
        //model.addAttribute("todos", List.of("eat","sleep","code","repeat"));
        model.addAttribute("todos", todos);
        return "index";
    }
    @PostMapping("/add")
    public String addTodo(@RequestParam String task) {
        System.out.println("Inside addTodo method");
        if(task != null&&!task.isEmpty()&&!task.isBlank()) {
            //store the task
            //todos.add(task);T
            Todo todo = new Todo(0,task,false);
            todos.add(todo);
        }
     
        
        return "redirect:/";
    }
    
    
}
