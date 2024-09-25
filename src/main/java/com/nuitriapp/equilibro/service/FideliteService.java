package com.nuitriapp.equilibro.service;

import com.nuitriapp.equilibro.model.Fidelite;
import com.nuitriapp.equilibro.model.Utilisateur;
import com.nuitriapp.equilibro.repository.FideliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FideliteService {

    @Autowired
    private FideliteRepository fideliteRepository;

    public Fidelite obtenirFideliteParUtilisateur(Utilisateur utilisateur) {
        return fideliteRepository.findByUtilisateur(utilisateur);
    }

    public Fidelite ajouterPoints(Utilisateur utilisateur, int points) {
        Fidelite fidelite = fideliteRepository.findByUtilisateur(utilisateur);
        if (fidelite == null) {
            fidelite = new Fidelite();
            fidelite.setUtilisateur(utilisateur);
            fidelite.setPoints(points); // Initialiser les points si nouvel enregistrement
        } else {
            fidelite.setPoints(fidelite.getPoints() + points);
        }
        return fideliteRepository.save(fidelite);
    }

    public Fidelite modifierPoints(Utilisateur utilisateur, int nouveauTotal) {
        Fidelite fidelite = fideliteRepository.findByUtilisateur(utilisateur);

        if (fidelite == null) {
            throw new RuntimeException("Fidélité non trouvée pour cet utilisateur");
        }

        // Mettre à jour les points de fidélité
        fidelite.setPoints(nouveauTotal);

        // Sauvegarder les changements
        return fideliteRepository.save(fidelite);
    }

    public Fidelite supprimerPoints(Utilisateur utilisateur, int points) {
        Fidelite fidelite = fideliteRepository.findByUtilisateur(utilisateur);

        if (fidelite == null) {
            throw new RuntimeException("Fidélité non trouvée pour cet utilisateur");
        }

        int nouveauTotal = fidelite.getPoints() - points;
        fidelite.setPoints(Math.max(nouveauTotal, 0));  // Pour s'assure que les points ne deviennent pas négatifs
        return fideliteRepository.save(fidelite);
    }
}