package com.nuitriapp.equilibro.controller;

import com.nuitriapp.equilibro.model.Forum;
import com.nuitriapp.equilibro.model.Message;
import com.nuitriapp.equilibro.model.Utilisateur;
import com.nuitriapp.equilibro.service.ForumService;
import com.nuitriapp.equilibro.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/forums")
public class ForumController {

    @Autowired
    private ForumService forumService;
    @Autowired
    private UtilisateurService utilisateurService;

    // Ajouter un sujet au forum
    @PostMapping("/ajouter")
    public ResponseEntity<Forum> ajouterSujet(@RequestBody Forum forum) {
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

        // Associer l'utilisateur au sujet du forum
        forum.setUtilisateur(utilisateur);

        // Ajouter le sujet et le sauvegarder
        Forum createdForum = forumService.ajouterSujet(forum);

        return ResponseEntity.ok(createdForum);
    }

    @GetMapping("/liste")
    public ResponseEntity<List<Forum>> obtenirTousLesSujets() {
        List<Forum> forums = forumService.obtenirTousLesSujets();
        return ResponseEntity.ok(forums);
    }

    // pour ajouter un message à un sujet de forum
    @PostMapping("/{forumId}/messages")
    public ResponseEntity<Message> ajouterMessage(@PathVariable Long forumId, @RequestBody Message message) {
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

        // Associer l'utilisateur au message
        message.setUtilisateur(utilisateur);

        // Ajouter le message au forum
        Message createdMessage = forumService.ajouterMessage(forumId, message);

        return ResponseEntity.ok(createdMessage);
    }


    @PutMapping("modifier/{id}")
    public ResponseEntity<Forum> modifierSujet(@PathVariable Long id, @RequestBody Forum forumDetails) {
        Forum updatedForum = forumService.modifierSujet(id, forumDetails);
        return ResponseEntity.ok(updatedForum);
    }

    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<Void> supprimerSujet(@PathVariable Long id) {
        forumService.supprimerSujet(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/messages/modifier/{messageId}")
    public ResponseEntity<Message> modifierMessage(@PathVariable Long messageId, @RequestBody Message messageDetails) {
        Message updatedMessage = forumService.modifierMessage(messageId, messageDetails);
        return ResponseEntity.ok(updatedMessage);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Void> supprimerMessage(@PathVariable Long messageId) {
        forumService.supprimerMessage(messageId);
        return ResponseEntity.noContent().build();
    }
}
