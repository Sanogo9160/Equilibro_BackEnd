package com.nuitriapp.equilibro.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class ProgrammeExercice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeExercice;
    private int duree; // Duration in minutes
    private int frequence; // Frequence de temps par semaine

    @ManyToOne
    private Utilisateur utilisateur;
}
