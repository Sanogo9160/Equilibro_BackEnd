package com.nuitriapp.equilibro.service;

import com.nuitriapp.equilibro.model.ApportsNutritionnels;
import com.nuitriapp.equilibro.model.Utilisateur;
import com.nuitriapp.equilibro.repository.ApportsNutritionnelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuiviNutritionnelService {
    @Autowired
    private ApportsNutritionnelsRepository apportsNutritionnelsRepository;
    @Autowired
    private UtilisateurService utilisateurService;

    // Récupérer tous les suivis
    public List<ApportsNutritionnels> obtenirTousLesSuivis() {
        return apportsNutritionnelsRepository.findAll();
    }

    // Enregistrer un suivi associé à un utilisateur
    public ApportsNutritionnels enregistrerApport(ApportsNutritionnels apportsNutritionnels, Long utilisateurId) {
        Utilisateur utilisateur = utilisateurService.obtenirUtilisateurParId(utilisateurId);
        apportsNutritionnels.setUtilisateur(utilisateur);
        return apportsNutritionnelsRepository.save(apportsNutritionnels);
    }

    // Récupérer les suivis d'un utilisateur
    public List<ApportsNutritionnels> obtenirSuiviParUtilisateur(Long utilisateurId) {
        return apportsNutritionnelsRepository.findByUtilisateurId(utilisateurId);
    }

    public ApportsNutritionnels modifierSuivi(Long id, ApportsNutritionnels nouveauSuivi) {
        Optional<ApportsNutritionnels> optionalSuivi = apportsNutritionnelsRepository.findById(id);
        if (optionalSuivi.isPresent()) {
            ApportsNutritionnels suiviExistant = optionalSuivi.get();
            suiviExistant.setDate(nouveauSuivi.getDate());
            suiviExistant.setCalories(nouveauSuivi.getCalories());
            suiviExistant.setProteines(nouveauSuivi.getProteines());
            suiviExistant.setGlucides(nouveauSuivi.getGlucides());
            suiviExistant.setLipides(nouveauSuivi.getLipides());
            return apportsNutritionnelsRepository.save(suiviExistant);
        } else {
            throw new RuntimeException("Suivi nutritionnel non trouvé avec l'id : " + id);
        }
    }

    public void supprimerSuivi(Long id) {
        if (apportsNutritionnelsRepository.existsById(id)) {
            apportsNutritionnelsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Suivi nutritionnel non trouvé avec l'id : " + id);
        }
    }
}
