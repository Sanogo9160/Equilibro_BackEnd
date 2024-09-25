package com.nuitriapp.equilibro.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @Column(unique = true, nullable = false)
    private String email;

    @Column( nullable = false)
    private String motDePasse;

    private String telephone;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role; // Fait référence à l'entité Role

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profil_de_sante_id")
    private ProfilDeSante profilDeSante;

}

