package com.nuitriapp.equilibro.controller;

import com.nuitriapp.equilibro.model.Fidelite;
import com.nuitriapp.equilibro.model.Utilisateur;
import com.nuitriapp.equilibro.repository.FideliteRepository;
import com.nuitriapp.equilibro.service.FideliteService;
import com.nuitriapp.equilibro.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/fidelite")
public class FideliteController {

    @Autowired
    private FideliteService fideliteService;

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private FideliteRepository fideliteRepository;

    // Récupérer les points de fidélité pour l'utilisateur authentifié
    @GetMapping("/points")
    public ResponseEntity<Fidelite> obtenirFidelitePourUtilisateurAuthentifie() {
        // Récupérer l'utilisateur authentifié
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUtilisateur = authentication.getName();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.obtenirUtilisateurParEmail(emailUtilisateur);
        if (!optionalUtilisateur.isPresent()) {
            throw new RuntimeException("Utilisateur authentifié non trouvé.");
        }

        Utilisateur utilisateur = optionalUtilisateur.get();
        Fidelite fidelite = fideliteService.obtenirFideliteParUtilisateur(utilisateur);

        return ResponseEntity.ok(fidelite);
    }

    // Ajouter des points pour l'utilisateur authentifié
    @PostMapping("/ajouter-points/{points}")
    public ResponseEntity<Fidelite> ajouterPointsPourUtilisateurAuthentifie(@PathVariable int points) {
        // Récupérer l'utilisateur authentifié
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUtilisateur = authentication.getName();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.obtenirUtilisateurParEmail(emailUtilisateur);
        if (!optionalUtilisateur.isPresent()) {
            throw new RuntimeException("Utilisateur authentifié non trouvé.");
        }

        Utilisateur utilisateur = optionalUtilisateur.get();
        Fidelite fidelite = fideliteService.ajouterPoints(utilisateur, points);

        return ResponseEntity.ok(fidelite);
    }

    // Modifier les points de fidélité pour l'utilisateur authentifié
    @PutMapping("/modifier-points/{nouveauTotal}")
    public ResponseEntity<Fidelite> modifierPointsPourUtilisateurAuthentifie(@PathVariable int nouveauTotal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUtilisateur = authentication.getName();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.obtenirUtilisateurParEmail(emailUtilisateur);
        if (!optionalUtilisateur.isPresent()) {
            throw new RuntimeException("Utilisateur authentifié non trouvé.");
        }

        Utilisateur utilisateur = optionalUtilisateur.get();
        Fidelite fidelite = fideliteService.modifierPoints(utilisateur, nouveauTotal);

        return ResponseEntity.ok(fidelite);
    }

    // Supprimer un certain nombre de points (ou réinitialiser à zéro) pour l'utilisateur authentifié
    @DeleteMapping("/supprimer-points/{points}")
    public ResponseEntity<Fidelite> supprimerPointsPourUtilisateurAuthentifie(@PathVariable int points) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUtilisateur = authentication.getName();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.obtenirUtilisateurParEmail(emailUtilisateur);
        if (!optionalUtilisateur.isPresent()) {
            throw new RuntimeException("Utilisateur authentifié non trouvé.");
        }

        Utilisateur utilisateur = optionalUtilisateur.get();
        Fidelite fidelite = fideliteService.supprimerPoints(utilisateur, points);

        return ResponseEntity.ok(fidelite);
    }


}
