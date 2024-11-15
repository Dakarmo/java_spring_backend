package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;


public interface UserRepo extends JpaRepository<User, Integer>{

    // récupération d'un user grâce à son mot passe et son email 
    User findByEmailAndPassword(String email, String password);
} 
