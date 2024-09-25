package com.nuitriapp.equilibro.service;

import com.nuitriapp.equilibro.model.Dieteticien;
import com.nuitriapp.equilibro.repository.DieteticienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DieteticienService {

    @Autowired
    private DieteticienRepository dieteticienRepository;

    public Dieteticien modifierDieteticien(Long id, Dieteticien dieteticienDetails) {
        return dieteticienRepository.findById(id).map(dieteticien -> {
            dieteticien.setNom(dieteticienDetails.getNom());
            dieteticien.setEmail(dieteticienDetails.getEmail());
            dieteticien.setSpecialite(dieteticienDetails.getSpecialite());
            // Autres modifications spécifiques au diététicien
            return dieteticienRepository.save(dieteticien);
        }).orElse(null);
    }
}
