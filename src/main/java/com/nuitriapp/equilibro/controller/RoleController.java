package com.nuitriapp.equilibro.controller;

import com.nuitriapp.equilibro.model.Role;
import com.nuitriapp.equilibro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/liste")
    public List<Role> listerRoles() {
        return roleService.listerRoles();
    }

    @PostMapping("/creer")
    public ResponseEntity<Role> creerRole(@RequestBody Role role) {
        Role roleCree = roleService.creerRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleCree);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> obtenirRole(@PathVariable Long id) {
        return roleService.obtenirRole(id)
                .map(role -> ResponseEntity.ok(role))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("modifier/{id}")
    public ResponseEntity<Role> mettreAJourRole(@PathVariable Long id, @RequestBody Role role) {
        Role roleMisAJour = roleService.mettreAJourRole(id, role);
        return ResponseEntity.ok(roleMisAJour);
    }

    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<Void> supprimerRole(@PathVariable Long id) {
        roleService.supprimerRole(id);
        return ResponseEntity.noContent().build();
    }
}
