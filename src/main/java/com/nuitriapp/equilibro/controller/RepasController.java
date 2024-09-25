package com.nuitriapp.equilibro.controller;

import com.nuitriapp.equilibro.model.Repas;
import com.nuitriapp.equilibro.model.Utilisateur;
import com.nuitriapp.equilibro.service.RepasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repas")
public class RepasController {

    @Autowired
    private RepasService repasService;

    // Création de repas
    @PostMapping("/ajouter/{utilisateurId}")
    public ResponseEntity<Repas> ajouterRepas(@PathVariable Long utilisateurId, @RequestBody Repas repas) {
        Repas nouveauRepas = repasService.ajouterRepas(repas, utilisateurId);
        return ResponseEntity.ok(nouveauRepas);
    }


    // Obtenir tous les repas
    @GetMapping("/liste")
    public ResponseEntity<List<Repas>> obtenirTousLesRepas() {
        List<Repas> repas = repasService.obtenirTousLesRepas();
        return ResponseEntity.ok(repas);
    }

    // Obtenir les repas par l'ID de l'utilisateur
    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<Repas>> obtenirRepasParUtilisateurId(@PathVariable Long utilisateurId) {
        List<Repas> repas = repasService.obtenirRepasParUtilisateurId(utilisateurId);
        return ResponseEntity.ok(repas);
    }

    // Modifier un repas
    @PutMapping("/modifier/{id}")
    public ResponseEntity<Repas> modifierRepas(@PathVariable Long id, @RequestBody Repas repasDetails) {
        Repas repasModifie = repasService.modifierRepas(id, repasDetails);
        if (repasModifie != null) {
            return ResponseEntity.ok(repasModifie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un repas
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> supprimerRepas(@PathVariable Long id) {
        repasService.supprimerRepas(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nombre/utilisateur/{utilisateurId}")
    public ResponseEntity<Long> compterRepasParUtilisateur(@PathVariable Long utilisateurId) {
        long nombreRepas = repasService.compterRepasParUtilisateurId(utilisateurId);
        return ResponseEntity.ok(nombreRepas);
    }

}


