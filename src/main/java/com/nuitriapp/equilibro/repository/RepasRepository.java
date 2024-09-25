package com.nuitriapp.equilibro.repository;

import com.nuitriapp.equilibro.model.Repas;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RepasRepository extends JpaRepository<Repas, Long> {

    List<Repas> findByUtilisateurId(Long utilisateurId);

    long countByUtilisateurId(Long utilisateurId);

}
