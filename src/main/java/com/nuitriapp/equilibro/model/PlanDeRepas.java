package com.nuitriapp.equilibro.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class PlanDeRepas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Repas> repasList;

    @ManyToOne
    private Utilisateur utilisateur;

}
