package com.nuitriapp.equilibro.service;

import com.nuitriapp.equilibro.model.Role;
import com.nuitriapp.equilibro.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> listerRoles() {
        return roleRepository.findAll();
    }

    public Role creerRole(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> obtenirRole(Long id) {
        return roleRepository.findById(id);
    }

    public Role mettreAJourRole(Long id, Role role) {
        role.setId(id);
        return roleRepository.save(role);
    }

    public void supprimerRole(Long id) {
        roleRepository.deleteById(id);
    }
}
