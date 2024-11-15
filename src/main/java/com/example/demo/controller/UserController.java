package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.LoginRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin // à ne pas oublié d'ajouter
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/register")
    private ResponseEntity<String> registerUser(@RequestBody User user){
        //Enregistrer l'utilisateur

        String msg = service.saveUser(user);

        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

    @PostMapping("/login")
    private ResponseEntity<User> getUserByEmailAndPassword(@RequestBody LoginRequest loginRequest) {
        // Appeler le service pour récupérer l'utilisateur
        User user = service.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        System.out.println(user);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Si l'utilisateur n'est pas trouvé
        }

        return new ResponseEntity<>(user, HttpStatus.OK); // Retourner l'utilisateur trouvé
    }
}
