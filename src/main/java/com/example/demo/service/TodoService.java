package com.example.demo.service;




import java.util.List;

import com.example.demo.entity.Todo;

public interface TodoService {
    
    Todo addTodo(Todo todo);
    Todo editTodo(Todo todo);
    void deleteTodo(Long id);
    Todo getTodoById(Long id);
    List<Todo> getAllTodos();
    

}

