package com.nuitriapp.equilibro.service;

import com.nuitriapp.equilibro.model.ProfilDeSante;
import com.nuitriapp.equilibro.repository.ProfilDeSanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfilDeSanteService {

    @Autowired
    private ProfilDeSanteRepository profilDeSanteRepository;

    // Créer un nouveau profil de santé
    public ProfilDeSante creerProfil(ProfilDeSante profilDeSante) {
        return profilDeSanteRepository.save(profilDeSante);
    }

    // Obtenir un profil de santé par ID
    public Optional<ProfilDeSante> obtenirProfilParId(Long id) {
        return profilDeSanteRepository.findById(id);
    }

    // Modifier un profil de santé existant
    public ProfilDeSante modifierProfil(Long id, ProfilDeSante profilDeSanteDetails) {
        Optional<ProfilDeSante> profilOptional = profilDeSanteRepository.findById(id);

        if (profilOptional.isPresent()) {
            ProfilDeSante profil = profilOptional.get();
            profil.setConditionsMedicales(profilDeSanteDetails.getConditionsMedicales());
            profil.setAllergies(profilDeSanteDetails.getAllergies());
            profil.setObjectifsSante(profilDeSanteDetails.getObjectifsSante());
            return profilDeSanteRepository.save(profil);
        } else {
            return null;
        }
    }

    // Supprimer un profil de santé par ID
    public void supprimerProfil(Long id) {
        profilDeSanteRepository.deleteById(id);
    }

}
