package com.nuitriapp.equilibro.controller;

import com.nuitriapp.equilibro.model.SuiviNutritionnel;
import com.nuitriapp.equilibro.repository.SuiviNutritionnelRepository;
import com.nuitriapp.equilibro.service.SuiviNutritionnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suivi-nutritionnel")
public class SuiviNutritionnelController {

    @Autowired
    private SuiviNutritionnelService suiviNutritionnelService;
    @Autowired
    private SuiviNutritionnelRepository suiviNutritionnelRepository;

    // Obtenir tous les suivis nutritionnels
    @GetMapping("/tous")
    public List<SuiviNutritionnel> obtenirTousLesSuivis() {
        return suiviNutritionnelService.obtenirTousLesSuivis();
    }
    // Enregistrer un suivi nutritionnel associé à un utilisateur
    @PostMapping("/enregistrer/{utilisateurId}")
    public SuiviNutritionnel enregistrerApport(@RequestBody SuiviNutritionnel suiviNutritionnel, @PathVariable Long utilisateurId) {
        return suiviNutritionnelService.enregistrerApport(suiviNutritionnel, utilisateurId);
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public List<SuiviNutritionnel> obtenirSuiviParUtilisateur(@PathVariable Long utilisateurId) {
        return suiviNutritionnelService.obtenirSuiviParUtilisateur(utilisateurId);
    }

    @PutMapping("modifier/{id}")
    public ResponseEntity<SuiviNutritionnel> modifierSuivi(@PathVariable Long id, @RequestBody SuiviNutritionnel nouveauSuivi) {
        SuiviNutritionnel suiviMisAJour = suiviNutritionnelService.modifierSuivi(id, nouveauSuivi);
        return ResponseEntity.ok(suiviMisAJour);
    }

    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<Void> supprimerSuivi(@PathVariable Long id) {
        suiviNutritionnelService.supprimerSuivi(id);
        return ResponseEntity.noContent().build();
    }
}