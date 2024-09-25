package com.nuitriapp.equilibro.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contenu;


    @ManyToOne // Association avec le Forum
    @JoinColumn(name = "forum_id") // Nom de la colonne dans la base de données
    private Forum forum;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;


}

