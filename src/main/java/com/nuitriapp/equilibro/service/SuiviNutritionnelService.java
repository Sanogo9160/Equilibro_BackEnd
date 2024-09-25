package com.nuitriapp.equilibro.service;

import com.nuitriapp.equilibro.model.SuiviNutritionnel;
import com.nuitriapp.equilibro.model.Utilisateur;
import com.nuitriapp.equilibro.repository.SuiviNutritionnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuiviNutritionnelService {
    @Autowired
    private SuiviNutritionnelRepository suiviNutritionnelRepository;
    @Autowired
    private UtilisateurService utilisateurService;

    // Récupérer tous les suivis
    public List<SuiviNutritionnel> obtenirTousLesSuivis() {
        return suiviNutritionnelRepository.findAll();
    }

    // Enregistrer un suivi associé à un utilisateur
    public SuiviNutritionnel enregistrerApport(SuiviNutritionnel suiviNutritionnel, Long utilisateurId) {
        Utilisateur utilisateur = utilisateurService.obtenirUtilisateurParId(utilisateurId);
        suiviNutritionnel.setUtilisateur(utilisateur);
        return suiviNutritionnelRepository.save(suiviNutritionnel);
    }

    // Récupérer les suivis d'un utilisateur
    public List<SuiviNutritionnel> obtenirSuiviParUtilisateur(Long utilisateurId) {
        return suiviNutritionnelRepository.findByUtilisateurId(utilisateurId);
    }

    public SuiviNutritionnel modifierSuivi(Long id, SuiviNutritionnel nouveauSuivi) {
        Optional<SuiviNutritionnel> optionalSuivi = suiviNutritionnelRepository.findById(id);
        if (optionalSuivi.isPresent()) {
            SuiviNutritionnel suiviExistant = optionalSuivi.get();
            suiviExistant.setDate(nouveauSuivi.getDate());
            suiviExistant.setCalories(nouveauSuivi.getCalories());
            suiviExistant.setProteines(nouveauSuivi.getProteines());
            suiviExistant.setGlucides(nouveauSuivi.getGlucides());
            suiviExistant.setLipides(nouveauSuivi.getLipides());
            return suiviNutritionnelRepository.save(suiviExistant);
        } else {
            throw new RuntimeException("Suivi nutritionnel non trouvé avec l'id : " + id);
        }
    }

    public void supprimerSuivi(Long id) {
        if (suiviNutritionnelRepository.existsById(id)) {
            suiviNutritionnelRepository.deleteById(id);
        } else {
            throw new RuntimeException("Suivi nutritionnel non trouvé avec l'id : " + id);
        }
    }
}
