package com.nuitriapp.equilibro.repository;

import com.nuitriapp.equilibro.model.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {

    Optional<Administrateur> findByEmail(String email);
}
