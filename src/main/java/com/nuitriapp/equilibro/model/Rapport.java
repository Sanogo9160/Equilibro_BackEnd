package com.nuitriapp.equilibro.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Data
public class Rapport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String periode; // Par semaine / Mois
    private LocalDate dateDebut;
    private LocalDate dateFin;

    @ElementCollection
    private Map<String, Integer> apportsNutritionnels; // Exemple: {"calories": 2000, "proteins": 50}

    @ManyToOne
    private Utilisateur utilisateur;


}
