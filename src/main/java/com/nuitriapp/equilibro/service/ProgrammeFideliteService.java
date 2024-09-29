package com.nuitriapp.equilibro.service;

import com.nuitriapp.equilibro.model.ProgrammeFidelite;
import com.nuitriapp.equilibro.model.Utilisateur;
import com.nuitriapp.equilibro.repository.ProgrammeFideliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FideliteService {

    @Autowired
    private ProgrammeFideliteRepository programmeFideliteRepository;

    public ProgrammeFidelite obtenirFideliteParUtilisateur(Utilisateur utilisateur) {
        return programmeFideliteRepository.findByUtilisateur(utilisateur);
    }

    public ProgrammeFidelite ajouterPoints(Utilisateur utilisateur, int points) {
        ProgrammeFidelite programmeFidelite = programmeFideliteRepository.findByUtilisateur(utilisateur);
        if (programmeFidelite == null) {
            programmeFidelite = new ProgrammeFidelite();
            programmeFidelite.setUtilisateur(utilisateur);
            programmeFidelite.setPoints(points); // Initialiser les points si nouvel enregistrement
        } else {
            programmeFidelite.setPoints(programmeFidelite.getPoints() + points);
        }
        return programmeFideliteRepository.save(programmeFidelite);
    }

    public ProgrammeFidelite modifierPoints(Utilisateur utilisateur, int nouveauTotal) {
        ProgrammeFidelite programmeFidelite = programmeFideliteRepository.findByUtilisateur(utilisateur);

        if (programmeFidelite == null) {
            throw new RuntimeException("Fidélité non trouvée pour cet utilisateur");
        }

        // Mettre à jour les points de fidélité
        programmeFidelite.setPoints(nouveauTotal);

        // Sauvegarder les changements
        return programmeFideliteRepository.save(programmeFidelite);
    }

    public ProgrammeFidelite supprimerPoints(Utilisateur utilisateur, int points) {
        ProgrammeFidelite programmeFidelite = programmeFideliteRepository.findByUtilisateur(utilisateur);

        if (programmeFidelite == null) {
            throw new RuntimeException("Fidélité non trouvée pour cet utilisateur");
        }

        int nouveauTotal = programmeFidelite.getPoints() - points;
        programmeFidelite.setPoints(Math.max(nouveauTotal, 0));  // Pour s'assure que les points ne deviennent pas négatifs
        return programmeFideliteRepository.save(programmeFidelite);
    }
}