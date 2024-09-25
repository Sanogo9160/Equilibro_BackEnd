package com.nuitriapp.equilibro.controller;

import com.nuitriapp.equilibro.model.Consultation;
import com.nuitriapp.equilibro.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @PostMapping("/planifier/{utilisateurId}")
    public ResponseEntity<Consultation> planifierConsultation(@PathVariable Long utilisateurId, @RequestBody Consultation consultation) {
        Consultation nouvelleConsultation = consultationService.planifierConsultation(consultation, utilisateurId);
        return ResponseEntity.ok(nouvelleConsultation);
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<Consultation>> obtenirConsultationsParUtilisateur(@PathVariable Long utilisateurId) {
        List<Consultation> consultations = consultationService.obtenirConsultationsParUtilisateur(utilisateurId);
        return ResponseEntity.ok(consultations);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<Consultation> modifierConsultation(@PathVariable Long id, @RequestBody Consultation nouvelleConsultation) {
        Consultation consultationModifiee = consultationService.modifierConsultation(id, nouvelleConsultation);
        return ResponseEntity.ok(consultationModifiee);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> supprimerConsultation(@PathVariable Long id) {
        consultationService.supprimerConsultation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/toutes")
    public ResponseEntity<List<Consultation>> obtenirToutesLesConsultations() {
        List<Consultation> consultations = consultationService.obtenirToutesLesConsultations();
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/nombre/{utilisateurId}")
    public ResponseEntity<Long> nombreConsultationsParUtilisateur(@PathVariable Long utilisateurId) {
        long nombreConsultations = consultationService.nombreConsultationsParUtilisateurId(utilisateurId);
        return ResponseEntity.ok(nombreConsultations);
    }


}
