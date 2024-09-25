package com.nuitriapp.equilibro.repository;

import com.nuitriapp.equilibro.model.Fidelite;
import com.nuitriapp.equilibro.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FideliteRepository extends JpaRepository<Fidelite, Long> {

    Fidelite findByUtilisateur(Utilisateur utilisateur);


}
