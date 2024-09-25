package com.nuitriapp.equilibro.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data

public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private String commentaires;

    @ManyToOne
    private Dieteticien dieteticien; // Fait reference au user Dieteticien

    @ManyToOne
    private Utilisateur utilisateur;


}
