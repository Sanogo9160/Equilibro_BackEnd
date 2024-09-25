package com.nuitriapp.equilibro.controller;

import com.nuitriapp.equilibro.model.Rapport;
import com.nuitriapp.equilibro.model.Utilisateur;
import com.nuitriapp.equilibro.service.RapportService;
import com.nuitriapp.equilibro.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rapports")
public class RapportController {
    @Autowired
    private RapportService rapportService;
    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/generer")
    public ResponseEntity<Rapport> genererRapport(@RequestBody Rapport rapport) {
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

        // Associer l'utilisateur au rapport
        rapport.setUtilisateur(utilisateur);

        // Générer le rapport
        Rapport nouveauRapport = rapportService.genererRapport(rapport);

        return ResponseEntity.ok(nouveauRapport);
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<Rapport>> obtenirRapports(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(rapportService.obtenirRapportsParUtilisateur(utilisateurId));
    }



}