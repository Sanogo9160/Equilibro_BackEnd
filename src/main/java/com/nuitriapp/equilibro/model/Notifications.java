package com.nuitriapp.equilibro.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private String type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date heure;

}
