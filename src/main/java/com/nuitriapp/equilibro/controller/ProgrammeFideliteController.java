package com.nuitriapp.equilibro.controller;

import com.nuitriapp.equilibro.model.ProgrammeFidelite;
import com.nuitriapp.equilibro.model.Utilisateur;
import com.nuitriapp.equilibro.repository.ProgrammeFideliteRepository;
import com.nuitriapp.equilibro.service.ProgrammeFideliteService;
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
    private ProgrammeFideliteService programmeFideliteService;

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private ProgrammeFideliteRepository programmeFideliteRepository;

    // Récupérer les points de fidélité pour l'utilisateur authentifié
    @GetMapping("/points")
    public ResponseEntity<ProgrammeFidelite> obtenirFidelitePourUtilisateurAuthentifie() {
        // Récupérer l'utilisateur authentifié
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUtilisateur = authentication.getName();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.obtenirUtilisateurParEmail(emailUtilisateur);
        if (!optionalUtilisateur.isPresent()) {
            throw new RuntimeException("Utilisateur authentifié non trouvé.");
        }

        Utilisateur utilisateur = optionalUtilisateur.get();
        ProgrammeFidelite programmeFidelite = programmeFideliteService.obtenirFideliteParUtilisateur(utilisateur);

        return ResponseEntity.ok(programmeFidelite);
    }

    // Ajouter des points pour l'utilisateur authentifié
    @PostMapping("/ajouter-points/{points}")
    public ResponseEntity<ProgrammeFidelite> ajouterPointsPourUtilisateurAuthentifie(@PathVariable int points) {
        // Récupérer l'utilisateur authentifié
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUtilisateur = authentication.getName();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.obtenirUtilisateurParEmail(emailUtilisateur);
        if (!optionalUtilisateur.isPresent()) {
            throw new RuntimeException("Utilisateur authentifié non trouvé.");
        }

        Utilisateur utilisateur = optionalUtilisateur.get();
        ProgrammeFidelite programmeFidelite = programmeFideliteService.ajouterPoints(utilisateur, points);

        return ResponseEntity.ok(programmeFidelite);
    }

    // Modifier les points de fidélité pour l'utilisateur authentifié
    @PutMapping("/modifier-points/{nouveauTotal}")
    public ResponseEntity<ProgrammeFidelite> modifierPointsPourUtilisateurAuthentifie(@PathVariable int nouveauTotal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUtilisateur = authentication.getName();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.obtenirUtilisateurParEmail(emailUtilisateur);
        if (!optionalUtilisateur.isPresent()) {
            throw new RuntimeException("Utilisateur authentifié non trouvé.");
        }

        Utilisateur utilisateur = optionalUtilisateur.get();
        ProgrammeFidelite programmeFidelite = programmeFideliteService.modifierPoints(utilisateur, nouveauTotal);

        return ResponseEntity.ok(programmeFidelite);
    }

    // Supprimer un certain nombre de points (ou réinitialiser à zéro) pour l'utilisateur authentifié
    @DeleteMapping("/supprimer-points/{points}")
    public ResponseEntity<ProgrammeFidelite> supprimerPointsPourUtilisateurAuthentifie(@PathVariable int points) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUtilisateur = authentication.getName();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.obtenirUtilisateurParEmail(emailUtilisateur);
        if (!optionalUtilisateur.isPresent()) {
            throw new RuntimeException("Utilisateur authentifié non trouvé.");
        }

        Utilisateur utilisateur = optionalUtilisateur.get();
        ProgrammeFidelite programmeFidelite = programmeFideliteService.supprimerPoints(utilisateur, points);

        return ResponseEntity.ok(programmeFidelite);
    }


}
