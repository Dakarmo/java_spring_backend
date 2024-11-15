package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // pour la methode getter et setter de l'annotation lombok 
@AllArgsConstructor // annotation lombok 
@NoArgsConstructor
@Entity // pour créer la table
@Table(name = "user_tab") // pour le nom de la table
public class User {

    @Id // pour que l'id soit la clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pour l'auto incrémentation de l'id
    private Integer id;
    private String name;
    @Column(unique = true) // Contrainte d'unicité pour l'email
    private String email;
    private String mobile;
    private String password;

}
