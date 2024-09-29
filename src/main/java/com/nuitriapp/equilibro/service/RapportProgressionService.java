package com.nuitriapp.equilibro.service;

import com.nuitriapp.equilibro.model.RapportProgression;
import com.nuitriapp.equilibro.repository.RapportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RapportService {
    @Autowired
    private RapportRepository rapportRepository;

    public RapportProgression genererRapport(RapportProgression rapport) {
        return rapportRepository.save(rapport);
    }

    public List<RapportProgression> obtenirRapportsParUtilisateur(Long utilisateurId) {
        return rapportRepository.findByUtilisateurId(utilisateurId);
    }

}