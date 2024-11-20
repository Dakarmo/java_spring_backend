package com.example.demo.controller;

import java.util.List;

import org.hibernate.grammars.hql.HqlParser.EntityNaturalIdReferenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Todo;
import com.example.demo.handlers.ResponseHandler;
import com.example.demo.service.TodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/add")
    public ResponseEntity<Object> addTodo(@RequestBody @Valid Todo todo){
       
        try {
            Todo addedTodo = todoService.addTodo(todo);
            return ResponseHandler.handleResponse("Successfully add Todo", HttpStatus.OK, addedTodo);
        
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @PutMapping("/edit")
    public ResponseEntity<Object> editProduct(@RequestBody @Valid Todo todo){
        try {
            Todo editTodo = todoService.editTodo(todo);
            if(editTodo!=null){

                return ResponseHandler.handleResponse("Successfully edit Todo", HttpStatus.OK, editTodo);
            }else{
                return ResponseHandler.handleResponse("Product Id not exist", HttpStatus.BAD_REQUEST, null);

            }

        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id){
        
        try{
            todoService.deleteTodo(id);
            return ResponseHandler.handleResponse("Successfully delete Todo", HttpStatus.OK, null);
        }catch(Exception e){
            
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllTodos() {
        
        try {
            List<Todo> todos = todoService.getAllTodos();
            return ResponseHandler.handleResponse("Successfully get Todos", HttpStatus.OK, todos);
        } catch (Exception e) {
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST, e.getMessage());
        }
      
    }
                                                
                                              
}
