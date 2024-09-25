package com.nuitriapp.equilibro.repository;

import com.nuitriapp.equilibro.model.ListeDeCourses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListeDeCoursesRepository extends JpaRepository<ListeDeCourses, Long> {

    List<ListeDeCourses> findByUtilisateurId(Long utilisateurId);
}
