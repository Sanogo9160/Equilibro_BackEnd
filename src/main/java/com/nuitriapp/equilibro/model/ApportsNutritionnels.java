package com.nuitriapp.equilibro.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class SuiviNutritionnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private int calories;
    private int glucides;
    private int lipides;
    private int proteines;

    @ManyToOne
    private Utilisateur utilisateur;

}