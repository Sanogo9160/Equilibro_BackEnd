package com.nuitriapp.equilibro.controller;

import com.nuitriapp.equilibro.model.Administrateur;
import com.nuitriapp.equilibro.model.Dieteticien;
import com.nuitriapp.equilibro.model.Utilisateur;
import com.nuitriapp.equilibro.model.UtilisateurSimple;
import com.nuitriapp.equilibro.service.AdministrateurService;
import com.nuitriapp.equilibro.service.DieteticienService;
import com.nuitriapp.equilibro.service.UtilisateurService;
import com.nuitriapp.equilibro.service.UtilisateurSimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private AdministrateurService administrateurService;
    @Autowired
    private DieteticienService dieteticienService;
    @Autowired
    private UtilisateurSimpleService utilisateurSimpleService;

    // Créer un administrateur
    @PostMapping("/creer/administrateur")
    public ResponseEntity<Administrateur> creerAdministrateur(@RequestBody Administrateur administrateur) {
        Administrateur administrateurCree = utilisateurService.creerAdministrateur(administrateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(administrateurCree);
    }

    // Créer un diététicien
    @PostMapping("/creer/dieteticien")
    public ResponseEntity<Dieteticien> creerDieteticien(@RequestBody Dieteticien dieteticien) {
        Dieteticien dieteticienCree = utilisateurService.creerDieteticien(dieteticien);
        return ResponseEntity.status(HttpStatus.CREATED).body(dieteticienCree);
    }

    // Créer un utilisateur simple
    @PostMapping("/creer/utilisateur")
    public ResponseEntity<UtilisateurSimple> creerUtilisateurSimple(@RequestBody UtilisateurSimple utilisateurSimple) {
        UtilisateurSimple utilisateurCree = utilisateurService.creerUtilisateurSimple(utilisateurSimple);
        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateurCree);
    }

    // Modifier un administrateur
    @PutMapping("/modifier/administrateur/{id}")
    public ResponseEntity<Administrateur> modifierAdministrateur(@PathVariable Long id, @RequestBody Administrateur administrateurDetails) {
        Administrateur administrateurModifie = administrateurService.modifierAdministrateur(id, administrateurDetails);
        if (administrateurModifie != null) {
            return ResponseEntity.ok(administrateurModifie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Modifier un diététicien
    @PutMapping("/modifier/dieteticien/{id}")
    public ResponseEntity<Dieteticien> modifierDieteticien(@PathVariable Long id, @RequestBody Dieteticien dieteticienDetails) {
        Dieteticien dieteticienModifie = dieteticienService.modifierDieteticien(id, dieteticienDetails);
        if (dieteticienModifie != null) {
            return ResponseEntity.ok(dieteticienModifie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Modifier un utilisateur simple
    @PutMapping("/modifier/utilisateur/{id}")
    public ResponseEntity<UtilisateurSimple> modifierUtilisateurSimple(@PathVariable Long id, @RequestBody UtilisateurSimple utilisateurSimpleDetails) {
        UtilisateurSimple utilisateurModifie = utilisateurSimpleService.modifierUtilisateurSimple(id, utilisateurSimpleDetails);
        if (utilisateurModifie != null) {
            return ResponseEntity.ok(utilisateurModifie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Supprimer un utilisateur
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
        return ResponseEntity.noContent().build();
    }

    //   Obtenir un utilisateur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> obtenirUtilisateurParId(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.obtenirUtilisateurParId(id);
        return ResponseEntity.ok(utilisateur);
    }

    //  Obtenir un utilisateur par son email
    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<Utilisateur>> obtenirUtilisateurParEmail(@PathVariable String email) {
        Optional<Utilisateur> utilisateur = utilisateurService.obtenirUtilisateurParEmail(email);
        return ResponseEntity.ok(utilisateur);
    }

    // Obtenir la liste de tous les utilisateurs
    @GetMapping("/liste")
    public ResponseEntity<List<Utilisateur>> obtenirTousLesUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurService.obtenirTousLesUtilisateurs();
        return ResponseEntity.ok(utilisateurs);
    }


}

