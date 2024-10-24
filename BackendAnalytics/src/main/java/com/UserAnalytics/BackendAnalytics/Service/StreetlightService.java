package com.UserAnalytics.BackendAnalytics.Service;

import com.UserAnalytics.BackendAnalytics.Model.Streetlight;
import com.UserAnalytics.BackendAnalytics.Repository.StreetlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetlightService {
    @Autowired
    private StreetlightRepository streetlightRepository;

    public List<Streetlight> getAllStreetlights() {
        return streetlightRepository.findAll();
    }

    public Streetlight getStreetlightById(Long id) {
        return streetlightRepository.findById(id).orElse(null);
    }

    public Streetlight saveStreetlight(Streetlight streetlight) {
        return streetlightRepository.save(streetlight);
    }

    public void deleteStreetlight(Long id) {
        streetlightRepository.deleteById(id);
    }

    // Additional method to create a Streetlight with coordinates as a string
    public Streetlight createStreetlight(String name, String type, String coordinates, Boolean status, Integer powerConsumption, Integer brightness, String operationalTime, String lastMaintenance, List<String> issues) {
        Streetlight streetlight = new Streetlight();
        streetlight.setName(name);
        streetlight.setType(type);
        streetlight.setCoordinates(coordinates);
        streetlight.setStatus(status);
        streetlight.setPowerConsumption(powerConsumption);
        streetlight.setBrightness(brightness);
        streetlight.setOperationalTime(operationalTime);
        streetlight.setLastMaintenance(lastMaintenance);
        streetlight.setIssues(issues);

        return streetlightRepository.save(streetlight);
    }
}
