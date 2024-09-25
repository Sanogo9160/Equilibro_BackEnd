package com.nuitriapp.equilibro.service;

import com.nuitriapp.equilibro.exception.UserAlreadyExistsException;
import com.nuitriapp.equilibro.model.*;
import com.nuitriapp.equilibro.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DieteticienRepository dieteticienRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdministrateurRepository administrateurRepository;

    // Créer un administrateur
    public Administrateur creerAdministrateur(Administrateur administrateur) {
        if (utilisateurRepository.findByEmail(administrateur.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email déjà utilisé");
        }
        // Encoder le mot de passe
        administrateur.setMotDePasse(passwordEncoder.encode(administrateur.getMotDePasse()));

        // Vérification et assignation du rôle
        Role role = obtenirRoleParId(administrateur.getRole().getId());
        administrateur.setRole(role);

        return utilisateurRepository.save(administrateur);
    }

    // Créer un diététicien
    public Dieteticien creerDieteticien(Dieteticien diete) {
        if (utilisateurRepository.findByEmail(diete.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email déjà utilisé");
        }
        // Encoder le mot de passe
        diete.setMotDePasse(passwordEncoder.encode(diete.getMotDePasse()));

        return dieteticienRepository.save(diete);
    }

    // Créer un utilisateur simple
    public UtilisateurSimple creerUtilisateurSimple(UtilisateurSimple utilisateurSimple) {
        if (utilisateurRepository.findByEmail(utilisateurSimple.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email déjà utilisé");
        }

        // Encoder le mot de passe
        utilisateurSimple.setMotDePasse(passwordEncoder.encode(utilisateurSimple.getMotDePasse()));

        // Vérification et assignation du rôle
        if (utilisateurSimple.getRole() != null && utilisateurSimple.getRole().getId() != null) {
            Role role = obtenirRoleParId(utilisateurSimple.getRole().getId());
            utilisateurSimple.setRole(role);
        }

        return utilisateurRepository.save(utilisateurSimple);
    }

    // Modifier un utilisateur simple
    public Utilisateur modifierUtilisateurSimple(Long id, Utilisateur utilisateurDetails) {
        return utilisateurRepository.findById(id).map(utilisateur -> {
            utilisateur.setNom(utilisateurDetails.getNom());
            utilisateur.setEmail(utilisateurDetails.getEmail());
            utilisateur.setTelephone(utilisateurDetails.getTelephone());

            // Ne pas écraser le mot de passe si aucun nouveau mot de passe n'est fourni
            if (utilisateurDetails.getMotDePasse() != null && !utilisateurDetails.getMotDePasse().isEmpty()) {
                utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDetails.getMotDePasse()));
            }

            // Modifier le rôle s'il a été fourni
            if (utilisateurDetails.getRole() != null) {
                Role role = obtenirRoleParId(utilisateurDetails.getRole().getId());
                utilisateur.setRole(role);
            }

            return utilisateurRepository.save(utilisateur);
        }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    // Obtenir un utilisateur par email
    public Optional<Utilisateur> obtenirUtilisateurParEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    // Obtenir un utilisateur par son ID
    public Utilisateur obtenirUtilisateurParId(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    // Supprimer un utilisateur par son ID
    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    // Obtenir la liste de tous les utilisateurs
    public List<Utilisateur> obtenirTousLesUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // Méthode pour obtenir un rôle par ID avec gestion d'erreur
    private Role obtenirRoleParId(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));
    }
}
