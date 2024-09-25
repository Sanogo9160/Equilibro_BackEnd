package com.nuitriapp.equilibro.service;

import com.nuitriapp.equilibro.model.ProgrammeExercice;
import com.nuitriapp.equilibro.repository.ProgrammeExerciceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeExerciceService {
    @Autowired
    private ProgrammeExerciceRepository programmeExerciceRepository;

    public ProgrammeExercice ajouterExercice(ProgrammeExercice programmeExercice) {
        return programmeExerciceRepository.save(programmeExercice);
    }

    public List<ProgrammeExercice> obtenirProgrammeParUtilisateur(Long utilisateurId) {
        return programmeExerciceRepository.findByUtilisateurId(utilisateurId);
    }

    public ProgrammeExercice modifierExercice(Long id, ProgrammeExercice programmeExerciceDetails) {
        Optional<ProgrammeExercice> optionalProgrammeExercice = programmeExerciceRepository.findById(id);
        if (optionalProgrammeExercice.isPresent()) {
            ProgrammeExercice programmeExercice = optionalProgrammeExercice.get();
            programmeExercice.setTypeExercice(programmeExerciceDetails.getTypeExercice());
            programmeExercice.setDuree(programmeExerciceDetails.getDuree());
            programmeExercice.setFrequence(programmeExerciceDetails.getFrequence());

            return programmeExerciceRepository.save(programmeExercice);
        } else {
            throw new RuntimeException("Programme d'exercice non trouvé avec l'id : " + id);
        }
    }

    public void supprimerExercice(Long id) {
        Optional<ProgrammeExercice> optionalProgrammeExercice = programmeExerciceRepository.findById(id);
        if (optionalProgrammeExercice.isPresent()) {
            programmeExerciceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Programme d'exercice non trouvé avec l'id : " + id);
        }
    }
}
