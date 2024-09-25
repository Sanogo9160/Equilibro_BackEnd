package com.nuitriapp.equilibro.repository;

import com.nuitriapp.equilibro.model.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RapportRepository extends JpaRepository<Rapport, Long> {
    List<Rapport> findByUtilisateurId(Long utilisateurId);
}
