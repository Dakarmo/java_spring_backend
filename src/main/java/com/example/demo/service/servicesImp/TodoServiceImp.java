package com.example.demo.service.servicesImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Todo;
import com.example.demo.repo.TodoRepo;
import com.example.demo.service.TodoService;

@Service
public class TodoServiceImp implements TodoService {

    @Autowired
    private TodoRepo todoRepo;

    @Override
    public Todo addTodo(Todo todo) {
        return todoRepo.save(todo);
    }

    @Override
    public Todo editTodo(Todo todo) {
        boolean exist = todoRepo.existsById(todo.getId());
        if (exist) {
            return todoRepo.save(todo);
        }
        return null;
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepo.deleteById(id);
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepo.findById(id).orElse(null);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepo.findAll();
    }
}
