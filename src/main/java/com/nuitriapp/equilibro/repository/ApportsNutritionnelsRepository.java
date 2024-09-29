package com.nuitriapp.equilibro.repository;
import com.nuitriapp.equilibro.model.ApportsNutritionnels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuiviNutritionnelRepository extends JpaRepository<ApportsNutritionnels, Long> {
    List<ApportsNutritionnels> findByUtilisateurId(Long utilisateurId);
}