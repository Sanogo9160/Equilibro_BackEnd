package com.nuitriapp.equilibro.service;

import com.nuitriapp.equilibro.model.Consultation;
import com.nuitriapp.equilibro.model.Utilisateur;
import com.nuitriapp.equilibro.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private UtilisateurService utilisateurService;

    // Planifier une nouvelle consultation

    public Consultation planifierConsultation(Consultation consultation, Long utilisateurId) {
        // Récupérer l'utilisateur à partir de la base de données
        Utilisateur utilisateur = utilisateurService.obtenirUtilisateurParId(utilisateurId);
        if (utilisateur == null) {
            throw new RuntimeException("Utilisateur non trouvé avec l'ID : " + utilisateurId);
        }
        consultation.setUtilisateur(utilisateur); // Associe l'utilisateur à la consultation
        return consultationRepository.save(consultation);
    }

    public List<Consultation> obtenirToutesLesConsultations() {
        return consultationRepository.findAll();
    }

    // Obtenir consultation d'un utilisateur spécifique
    public List<Consultation> obtenirConsultationsParUtilisateur(Long utilisateurId) {
        return consultationRepository.findByUtilisateurId(utilisateurId);
    }

    // Modifier consultation
    public Consultation modifierConsultation(Long id, Consultation consultationDetails) {
        Optional<Consultation> consultationOptional = consultationRepository.findById(id);

        if (consultationOptional.isPresent()) {
            Consultation consultation = consultationOptional.get();

            // Mettez à jour uniquement les champs nécessaires
            consultation.setDate(consultationDetails.getDate());
            consultation.setCommentaires(consultationDetails.getCommentaires());

            // Conserver l'utilisateur existant
            Utilisateur utilisateur = consultation.getUtilisateur();
            if (utilisateur != null) {
                consultation.setUtilisateur(utilisateur);
            } else {
                throw new RuntimeException("Utilisateur non trouvé pour cette consultation.");
            }

            // Mettre à jour le diététicien si fourni
            if (consultationDetails.getDieteticien() != null) {
                consultation.setDieteticien(consultationDetails.getDieteticien());
            }

            return consultationRepository.save(consultation);
        } else {
            throw new RuntimeException("Consultation non trouvée avec l'ID : " + id);
        }
    }


    // Supprimer une consultation
    public void supprimerConsultation(Long id) {
        consultationRepository.deleteById(id);
    }

    // obtenir une consultation par ID
    public Optional<Consultation> obtenirConsultationParId(Long id) {
        return consultationRepository.findById(id);
    }

    public long nombreConsultationsParUtilisateurId(Long utilisateurId) {
        return consultationRepository.countByUtilisateurId(utilisateurId);
    }


}
