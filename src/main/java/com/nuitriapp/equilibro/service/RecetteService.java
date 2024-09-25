package com.nuitriapp.equilibro.service;

import com.nuitriapp.equilibro.model.Recette;
import com.nuitriapp.equilibro.model.Utilisateur;
import com.nuitriapp.equilibro.repository.RecetteRepository;
import com.nuitriapp.equilibro.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class RecetteService {
    @Autowired
    private RecetteRepository recetteRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Recette ajouterRecette(Recette recette, MultipartFile file, Long utilisateurId) throws IOException {
        // Récupérer l'utilisateur depuis le repository
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Associer l'utilisateur à la recette
        recette.setUtilisateur(utilisateur);

        // Gérer le fichier image
        if (file != null && !file.isEmpty()) {
            // Convertir l'image en tableau de bytes
            recette.setImage(file.getBytes());
        }
        return recetteRepository.save(recette);
    }

    // Obtenir toutes les recettes sans filtrer par utilisateur
    public List<Recette> obtenirToutesLesRecettes() {
        return recetteRepository.findAll();
    }

    public List<Recette> obtenirRecettesParUtilisateur(Long utilisateurId) {
        return recetteRepository.findByUtilisateurId(utilisateurId);
    }

    public void supprimerRecette(Long id) {
        recetteRepository.deleteById(id);
    }

    public Recette modifierRecette(Long id, Recette nouvelleRecette) {
        Optional<Recette> recetteExistante = recetteRepository.findById(id);

        if (recetteExistante.isPresent()) {
            Recette recette = recetteExistante.get();
            recette.setTitre(nouvelleRecette.getTitre());
            recette.setInstructions(nouvelleRecette.getInstructions());
            recette.setIngredients(nouvelleRecette.getIngredients());
            recette.setUtilisateur(nouvelleRecette.getUtilisateur());
            return recetteRepository.save(recette);
        } else {
            throw new RuntimeException("Recette non trouvé avec id " + id);
        }
    }

    // Méthode pour compter le nombre total de recettes
    public long compterRecettes() {
        return recetteRepository.count();
    }

    // Méthode pour compter le nombre de recettes d'un utilisateur donné
    public long compterRecettesParUtilisateur(Long utilisateurId) {
        return recetteRepository.countByUtilisateurId(utilisateurId);
    }

}