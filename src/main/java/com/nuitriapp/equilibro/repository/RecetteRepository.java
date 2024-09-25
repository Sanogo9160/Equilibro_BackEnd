package com.nuitriapp.equilibro.repository;

import com.nuitriapp.equilibro.model.Recette;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecetteRepository extends JpaRepository<Recette, Long> {

    long countByUtilisateurId(Long utilisateurId);

    List<Recette> findByUtilisateurId(Long utilisateurId);

}
