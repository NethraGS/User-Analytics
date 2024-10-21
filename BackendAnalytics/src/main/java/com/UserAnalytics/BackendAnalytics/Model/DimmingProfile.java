package com.UserAnalytics.BackendAnalytics.Model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dimming_profiles")
public class DimmingProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String profileName;

    @Column(nullable = false)
    private String profileType;

    @Column(name = "start_time_lamp_level", nullable = false)
    private String startTimeLampLevel;

    @Column(name = "motion_photocell_start", nullable = false)
    private String motionOrPhotocellStart;

    @Column(name = "sunrise_lamp_level", nullable = false)
    private String sunriseLampLevel;

    @Column(name = "motion_photocell_sunrise", nullable = false)
    private String motionOrPhotocellSunrise;

    @Column(name = "sunset_lamp_level", nullable = false)
    private String sunsetLampLevel;

    @Column(name = "motion_photocell_sunset", nullable = false)
    private String motionOrPhotocellSunset;
}

