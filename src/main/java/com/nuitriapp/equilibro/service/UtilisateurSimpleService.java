package com.nuitriapp.equilibro.service;

import com.nuitriapp.equilibro.model.UtilisateurSimple;
import com.nuitriapp.equilibro.repository.UtilisateurSimpleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurSimpleService {

    @Autowired
    private UtilisateurSimpleRepository utilisateurSimpleRepository;

    public UtilisateurSimple modifierUtilisateurSimple(Long id, UtilisateurSimple utilisateurSimpleDetails) {
        return utilisateurSimpleRepository.findById(id).map(utilisateurSimple -> {
            utilisateurSimple.setNom(utilisateurSimpleDetails.getNom());
            utilisateurSimple.setEmail(utilisateurSimpleDetails.getEmail());
            utilisateurSimple.setTelephone(utilisateurSimpleDetails.getTelephone());
            utilisateurSimple.setMotDePasse(utilisateurSimpleDetails.getMotDePasse());
            utilisateurSimple.setRole(utilisateurSimpleDetails.getRole());
            // Ajouter toute autre logique spécifique
            return utilisateurSimpleRepository.save(utilisateurSimple);
        }).orElse(null);
    }
}

