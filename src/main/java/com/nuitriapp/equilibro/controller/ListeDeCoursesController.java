package com.nuitriapp.equilibro.controller;

import com.nuitriapp.equilibro.model.ListeDeCourses;
import com.nuitriapp.equilibro.model.Utilisateur;
import com.nuitriapp.equilibro.service.ListeDeCoursesService;
import com.nuitriapp.equilibro.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/listes-de-courses")
public class ListeDeCoursesController {

    @Autowired
    private ListeDeCoursesService listeDeCoursesService;
    @Autowired
    private UtilisateurService utilisateurService;

    // Créer une liste de Course
    @PostMapping("/generer")
    public ResponseEntity<ListeDeCourses> genererListe(@RequestBody ListeDeCourses listeDeCourses) {
        // Récupérer l'utilisateur authentifié à partir du SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUtilisateur = authentication.getName();  // Récupère l'email de l'utilisateur authentifié

        // Récupérer l'utilisateur à partir de son email
        Optional<Utilisateur> optionalUtilisateur = utilisateurService.obtenirUtilisateurParEmail(emailUtilisateur);

        // Vérifier si l'utilisateur existe
        if (!optionalUtilisateur.isPresent()) {
            throw new RuntimeException("Utilisateur authentifié non trouvé.");
        }

        Utilisateur utilisateur = optionalUtilisateur.get();  // Déballer l'Optional

        // Associer l'utilisateur à la liste de courses
        listeDeCourses.setUtilisateur(utilisateur);

        // Générer et sauvegarder la nouvelle liste de courses
        ListeDeCourses nouvelleListe = listeDeCoursesService.genererListe(listeDeCourses);

        return ResponseEntity.ok(nouvelleListe);
    }

    // Obtenir la liste par ID utilisateur
    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<ListeDeCourses>> obtenirListesParUtilisateur(@PathVariable Long utilisateurId) {
        List<ListeDeCourses> listes = listeDeCoursesService.obtenirListesParUtilisateur(utilisateurId);
        return ResponseEntity.ok(listes);
    }

    // Modifier une liste de courses
    @PutMapping("/modifier/{id}")
    public ResponseEntity<ListeDeCourses> modifierListe(@PathVariable Long id, @RequestBody ListeDeCourses nouvelleListe) {
        ListeDeCourses listeModifiee = listeDeCoursesService.modifierListe(id, nouvelleListe);
        return ResponseEntity.ok(listeModifiee);
    }

    // Supprimer une liste de courses
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> supprimerListe(@PathVariable Long id) {
        listeDeCoursesService.supprimerListe(id);
        return ResponseEntity.noContent().build();
    }

    // Obtenir toutes les listes de courses
    @GetMapping("/liste")
    public ResponseEntity<List<ListeDeCourses>> obtenirToutesListes() {
        List<ListeDeCourses> listes = listeDeCoursesService.obtenirToutesListes();
        return ResponseEntity.ok(listes);
    }


}