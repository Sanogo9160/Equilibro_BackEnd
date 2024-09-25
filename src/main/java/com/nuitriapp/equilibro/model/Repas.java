package com.nuitriapp.equilibro.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Repas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private String ingredients;
    private String informationsNutritionnelles;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur; // Fait référence à l'entité Utilisateur

}
