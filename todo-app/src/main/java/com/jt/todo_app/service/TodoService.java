package com.jt.todo_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jt.todo_app.model.Todo;

import lombok.RequiredArgsConstructor;

@Service

@RequiredArgsConstructor

public class TodoService {
    private final List<Todo> todos;
    private static int idCounter = 0;

    public List<Todo> geTodos() {
        return todos;
    }

    public void createTodo(String task) {

        if (task != null && !task.isEmpty() && !task.isBlank()) {
            Todo todo = new Todo(++idCounter, task, false);
            todos.add(todo);
        }

    }

    private Optional<Todo> getTodoById(int id) {
        // for (Todo todo : todos) {
        // if (todo.getId() == id) {
        // return Optional.of(todo);
        // }
        // }
        // return Optional.empty();

        return todos.stream().filter(todo -> todo.getId() == id).findFirst();
    }

    public void toggleTodoById(int id) {
        Optional<Todo> optTodo = getTodoById(id);
        // if(optTodo.isPresent()) {
        // Todo todo = optTodo.get();
        // todo.setCompleted(!todo.isCompleted());
        // }
        optTodo.ifPresent((todo) -> todo.setCompleted(!todo.isCompleted()));

    }

    public void deleteTodoById(int id) {
        getTodoById(id).ifPresent((todo) -> todos.remove(todo));
    }

}
