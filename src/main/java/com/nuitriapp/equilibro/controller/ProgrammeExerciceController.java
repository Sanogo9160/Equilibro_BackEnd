package com.nuitriapp.equilibro.controller;

import com.nuitriapp.equilibro.model.ProgrammeExercice;
import com.nuitriapp.equilibro.model.Utilisateur;
import com.nuitriapp.equilibro.service.ProgrammeExerciceService;
import com.nuitriapp.equilibro.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/programmes-exercice")
public class ProgrammeExerciceController {

    @Autowired
    private ProgrammeExerciceService programmeExerciceService;
    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/ajouter")
    public ResponseEntity<ProgrammeExercice> ajouterExercice(@RequestBody ProgrammeExercice programmeExercice) {
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

        // Associer l'utilisateur à l'exercice
        programmeExercice.setUtilisateur(utilisateur);

        // Ajouter l'exercice et le sauvegarder
        ProgrammeExercice nouvelExercice = programmeExerciceService.ajouterExercice(programmeExercice);

        return ResponseEntity.ok(nouvelExercice);
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public List<ProgrammeExercice> obtenirProgrammeParUtilisateur(@PathVariable Long utilisateurId) {
        return programmeExerciceService.obtenirProgrammeParUtilisateur(utilisateurId);
    }

    @PutMapping("modifier/{id}")
    public ProgrammeExercice modifierExercice(@PathVariable Long id, @RequestBody ProgrammeExercice programmeExerciceDetails) {
        return programmeExerciceService.modifierExercice(id, programmeExerciceDetails);
    }


    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<Void> supprimerExercice(@PathVariable Long id) {
        programmeExerciceService.supprimerExercice(id);
        return ResponseEntity.noContent().build();
    }
}
