package com.UserAnalytics.BackendAnalytics.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "streetlight")
public class Streetlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    // Coordinates stored as a string (e.g., "latitude,longitude")
    private String coordinates;

    private Boolean status;
    private Integer powerConsumption;
    private Integer brightness;
    private String operationalTime;
    private String lastMaintenance;

    @ElementCollection
    private List<String> issues;
}