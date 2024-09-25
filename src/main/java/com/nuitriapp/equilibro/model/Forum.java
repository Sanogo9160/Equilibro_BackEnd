package com.nuitriapp.equilibro.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sujet;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Message> messages;

    @ManyToOne
    private Utilisateur utilisateur;

}
