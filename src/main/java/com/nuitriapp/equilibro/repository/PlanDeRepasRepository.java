package com.nuitriapp.equilibro.repository;

import com.nuitriapp.equilibro.model.PlanDeRepas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanDeRepasRepository extends JpaRepository<PlanDeRepas, Long> {
        List<PlanDeRepas> findByUtilisateurId(Long utilisateurId);

}
