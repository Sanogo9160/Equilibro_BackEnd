package com.nuitriapp.equilibro.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Recette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String instructions;

    @Lob
    private byte[] image; // Colonne pour stocker l'image

    @ElementCollection
    private List<String> ingredients;

    @ManyToOne
    private Utilisateur utilisateur;

}


