package com.nuitriapp.equilibro.controller;
import com.nuitriapp.equilibro.model.PlanDeRepas;
import com.nuitriapp.equilibro.service.PlanDeRepasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planderepas")
public class PlanDeRepasController {
    @Autowired
    private PlanDeRepasService planDeRepasService;

    @GetMapping("/tous")
    public ResponseEntity<List<PlanDeRepas>> obtenirTousLesPlansDeRepas() {
        List<PlanDeRepas> plansDeRepas = planDeRepasService.obtenirTousLesPlans();
        return ResponseEntity.ok(plansDeRepas);
    }

    @PostMapping("/creer/{utilisateurId}")
    public ResponseEntity<PlanDeRepas> creerPlanDeRepas(@PathVariable Long utilisateurId, @RequestBody PlanDeRepas planDeRepas) {
        PlanDeRepas nouveauPlan = planDeRepasService.creerPlanDeRepas(utilisateurId, planDeRepas);
        return ResponseEntity.ok(nouveauPlan);
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<PlanDeRepas>> obtenirPlansDeRepas(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(planDeRepasService.obtenirPlansDeRepasParUtilisateur(utilisateurId));
    }

    @PutMapping("modifier/{id}")
    public ResponseEntity<PlanDeRepas> modifierPlanDeRepas(@PathVariable Long id, @RequestBody PlanDeRepas nouveauPlanDeRepas) {
        PlanDeRepas planDeRepasMisAJour = planDeRepasService.modifierPlanDeRepas(id, nouveauPlanDeRepas);
        return ResponseEntity.ok(planDeRepasMisAJour);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> supprimerPlanDeRepas(@PathVariable Long id) {
        planDeRepasService.supprimerPlanDeRepas(id);
        return ResponseEntity.noContent().build();
    }

}
