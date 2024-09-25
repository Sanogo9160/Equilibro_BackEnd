package com.nuitriapp.equilibro.service;


import com.nuitriapp.equilibro.model.Repas;
import com.nuitriapp.equilibro.model.Utilisateur;
import com.nuitriapp.equilibro.repository.RepasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepasService {

    @Autowired
    private RepasRepository repasRepository;
    @Autowired
    private UtilisateurService utilisateurService;


    public Repas ajouterRepas(Repas repas, Long utilisateurId) {
        // Récupérer l'utilisateur à partir de la base de données
        Utilisateur utilisateur = utilisateurService.obtenirUtilisateurParId(utilisateurId);
        if (utilisateur == null) {
            throw new RuntimeException("Utilisateur non trouvé avec l'ID : " + utilisateurId);
        }
        repas.setUtilisateur(utilisateur);
        return repasRepository.save(repas);
    }

    public List<Repas> obtenirTousLesRepas() {
        return repasRepository.findAll();
    }

    public List<Repas> obtenirRepasParUtilisateurId(Long utilisateurId) {
        return repasRepository.findByUtilisateurId(utilisateurId);
    }


    // Modifier un repas existant
    public Repas modifierRepas(Long id, Repas repasDetails) {
        Optional<Repas> repasOptional = repasRepository.findById(id);

        if (repasOptional.isPresent()) {
            Repas repas = repasOptional.get();
            repas.setTitre(repasDetails.getTitre());
            repas.setDescription(repasDetails.getDescription());
            repas.setIngredients(repasDetails.getIngredients());
            repas.setInformationsNutritionnelles(repasDetails.getInformationsNutritionnelles());

            repas.setUtilisateur(repasDetails.getUtilisateur());

            return repasRepository.save(repas);
        } else {
            return null; // Ou lever une exception si nécessaire
        }
    }
    // Supprimer un repas par son ID
    public void supprimerRepas(Long id) {
        repasRepository.deleteById(id);
    }

    public long compterRepasParUtilisateurId(Long utilisateurId) {
        return repasRepository.countByUtilisateurId(utilisateurId);
    }

}
