package com.nuitriapp.equilibro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuitriapp.equilibro.model.Recette;
import com.nuitriapp.equilibro.model.Utilisateur;
import com.nuitriapp.equilibro.service.RecetteService;
import com.nuitriapp.equilibro.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recettes")
public class RecetteController {
    @Autowired
    private RecetteService recetteService;
    @Autowired
    private UtilisateurService utilisateurService;

    // Ajouter une recette pour un utilisateur spécifique
    @PostMapping("/ajouter")
    public ResponseEntity<Recette> ajouterRecette(@RequestParam("recette") String recetteJson,
                                                  @RequestParam("image") MultipartFile file) throws IOException {
        // Récupérer l'utilisateur authentifié
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUtilisateur = authentication.getName();  // Récupère l'email de l'utilisateur authentifié

        // Récupérer l'utilisateur à partir de son email
        Optional<Utilisateur> optionalUtilisateur = utilisateurService.obtenirUtilisateurParEmail(emailUtilisateur);

        // Vérifier si l'utilisateur existe
        if (!optionalUtilisateur.isPresent()) {
            throw new RuntimeException("Utilisateur authentifié non trouvé.");
        }

        Utilisateur utilisateur = optionalUtilisateur.get();  // Déballer l'Optional

        ObjectMapper objectMapper = new ObjectMapper();
        Recette recette = objectMapper.readValue(recetteJson, Recette.class);

        // Passer l'ID de l'utilisateur à la méthode de service
        Recette nouvelleRecette = recetteService.ajouterRecette(recette, file, utilisateur.getId());
        return ResponseEntity.ok(nouvelleRecette);
    }


    // Obtenir toutes les recettes
    @GetMapping("/toutes")
    public ResponseEntity<List<Recette>> obtenirToutesLesRecettes() {
        List<Recette> toutesLesRecettes = recetteService.obtenirToutesLesRecettes();
        return ResponseEntity.ok(toutesLesRecettes);
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<Recette>> obtenirRecettes(@PathVariable Long utilisateurId) {
        List<Recette> recettes = recetteService.obtenirRecettesParUtilisateur(utilisateurId);
        return ResponseEntity.ok(recettes);
    }

    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<Void> supprimerRecette(@PathVariable Long id) {
        recetteService.supprimerRecette(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("modifier/{id}")
    public ResponseEntity<Recette> modifierRecette(@PathVariable Long id, @RequestBody Recette nouvelleRecette) {
        Recette recetteModifiee = recetteService.modifierRecette(id, nouvelleRecette);
        return ResponseEntity.ok(recetteModifiee);
    }

    @GetMapping("/nombre")
    public ResponseEntity<Long> obtenirNombreDeRecettes() {
        long nombreRecettes = recetteService.compterRecettes();
        return ResponseEntity.ok(nombreRecettes);
    }

    // Endpoint pour obtenir le nombre de recettes d'un utilisateur spécifique
    @GetMapping("/nombre/utilisateur/{utilisateurId}")
    public ResponseEntity<Long> obtenirNombreDeRecettesPourUtilisateur(@PathVariable Long utilisateurId) {
        long nombreRecettes = recetteService.compterRecettesParUtilisateur(utilisateurId);
        return ResponseEntity.ok(nombreRecettes);
    }
}
