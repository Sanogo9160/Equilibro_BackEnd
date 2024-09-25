package com.nuitriapp.equilibro.service;

import com.nuitriapp.equilibro.model.PlanDeRepas;
//import com.nuitriapp.equilibro.repository.PlanDeRepasRepository;
import com.nuitriapp.equilibro.model.Utilisateur;
import com.nuitriapp.equilibro.repository.PlanDeRepasRepository;
import com.nuitriapp.equilibro.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanDeRepasService {
    @Autowired
    private PlanDeRepasRepository planDeRepasRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<PlanDeRepas> obtenirTousLesPlans() {
        return planDeRepasRepository.findAll();
    }

    public PlanDeRepas creerPlanDeRepas(Long utilisateurId, PlanDeRepas planDeRepas) {
        // Récupérer l'utilisateur par son ID
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Associer l'utilisateur au plan de repas
        planDeRepas.setUtilisateur(utilisateur);

        // Sauvegarder le plan de repas
        return planDeRepasRepository.save(planDeRepas);
    }
    public List<PlanDeRepas> obtenirPlansDeRepasParUtilisateur(Long utilisateurId) {
        return planDeRepasRepository.findByUtilisateurId(utilisateurId);
    }

    public PlanDeRepas modifierPlanDeRepas(Long id, PlanDeRepas nouveauPlanDeRepas) {
        Optional<PlanDeRepas> optionalPlanDeRepas = planDeRepasRepository.findById(id);
        if (optionalPlanDeRepas.isPresent()) {
            PlanDeRepas planDeRepasExistant = optionalPlanDeRepas.get();
            planDeRepasExistant.setDate(nouveauPlanDeRepas.getDate());

            planDeRepasExistant.setRepasList(nouveauPlanDeRepas.getRepasList());

            return planDeRepasRepository.save(planDeRepasExistant);
        } else {
            throw new RuntimeException("Plan de repas non trouvé avec l'id : " + id);
        }
    }

    public void supprimerPlanDeRepas(Long id) {
        planDeRepasRepository.deleteById(id);
    }

}
