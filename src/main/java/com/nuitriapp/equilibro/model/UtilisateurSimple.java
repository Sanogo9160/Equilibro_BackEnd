package com.nuitriapp.equilibro.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class UtilisateurSimple extends Utilisateur {

}
