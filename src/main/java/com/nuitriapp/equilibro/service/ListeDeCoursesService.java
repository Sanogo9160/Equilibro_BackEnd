package com.nuitriapp.equilibro.service;

import com.nuitriapp.equilibro.model.ListeDeCourses;
import com.nuitriapp.equilibro.repository.ListeDeCoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListeDeCoursesService {

    @Autowired
    private ListeDeCoursesRepository listeDeCoursesRepository;

    // Créer une liste de courses
    public ListeDeCourses genererListe(ListeDeCourses listeDeCourses) {
        return listeDeCoursesRepository.save(listeDeCourses);
    }


    // Obtenir la liste de courses pour utilisateur spécifique
    public List<ListeDeCourses> obtenirListesParUtilisateur(Long utilisateurId) {
        return listeDeCoursesRepository.findByUtilisateurId(utilisateurId);
    }

    // Modifier la liste de Courses
    public ListeDeCourses modifierListe(Long id, ListeDeCourses nouvelleListe) {
        Optional<ListeDeCourses> listeExistante = listeDeCoursesRepository.findById(id);

        if (listeExistante.isPresent()) {
            ListeDeCourses listeDeCourses = listeExistante.get();
            listeDeCourses.setElements(nouvelleListe.getElements());
            listeDeCourses.setUtilisateur(nouvelleListe.getUtilisateur());
            return listeDeCoursesRepository.save(listeDeCourses);
        } else {
            throw new RuntimeException("Liste de courses not found with id " + id);
        }
    }

    // Pour supprimer la liste de Courses
    public void supprimerListe(Long id) {
        listeDeCoursesRepository.deleteById(id);
    }

    // Obtenir une liste de courses par son ID
    public Optional<ListeDeCourses> obtenirListeParId(Long id) {
        return listeDeCoursesRepository.findById(id);
    }

    public List<ListeDeCourses> obtenirToutesListes() {
        return listeDeCoursesRepository.findAll();

    }
}