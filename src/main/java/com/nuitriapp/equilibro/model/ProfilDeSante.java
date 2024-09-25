package com.nuitriapp.equilibro.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ProfilDeSante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String conditionsMedicales;
    private String allergies;
    private String objectifsSante;

    @OneToOne(mappedBy = "profilDeSante")
    private Utilisateur utilisateur;

}


