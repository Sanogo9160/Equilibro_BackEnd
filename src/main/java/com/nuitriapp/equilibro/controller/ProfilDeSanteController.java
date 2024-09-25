package com.nuitriapp.equilibro.controller;

import com.nuitriapp.equilibro.model.ProfilDeSante;
import com.nuitriapp.equilibro.service.ProfilDeSanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profils")
public class ProfilDeSanteController {

    @Autowired
    private ProfilDeSanteService profilDeSanteService;

    // Créer un nouveau profil de santé
    @PostMapping("/creer")
    public ResponseEntity<ProfilDeSante> creerProfil(@RequestBody ProfilDeSante profilDeSante) {
        ProfilDeSante nouveauProfil = profilDeSanteService.creerProfil(profilDeSante);
        return ResponseEntity.ok(nouveauProfil);
    }

    // Obtenir un profil de santé par ID
    @GetMapping("/{id}")
    public ResponseEntity<ProfilDeSante> obtenirProfilParId(@PathVariable Long id) {
        Optional<ProfilDeSante> profil = profilDeSanteService.obtenirProfilParId(id);
        return profil.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Modifier un profil de santé
    @PutMapping("/modifier/{id}")
    public ResponseEntity<ProfilDeSante> modifierProfil(@PathVariable Long id, @RequestBody ProfilDeSante profilDeSanteDetails) {
        ProfilDeSante profilModifie = profilDeSanteService.modifierProfil(id, profilDeSanteDetails);
        if (profilModifie != null) {
            return ResponseEntity.ok(profilModifie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un profil de santé par ID
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> supprimerProfil(@PathVariable Long id) {
         profilDeSanteService.supprimerProfil(id);
         return ResponseEntity.noContent().build();
    }
}
