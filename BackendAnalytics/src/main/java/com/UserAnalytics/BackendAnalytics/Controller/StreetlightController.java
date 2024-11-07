package com.UserAnalytics.BackendAnalytics.Controller;

import com.UserAnalytics.BackendAnalytics.Model.Streetlight;
import com.UserAnalytics.BackendAnalytics.Service.StreetlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/streetlights")
public class StreetlightController {
    @Autowired
    private StreetlightService streetlightService;

    @GetMapping
    public List<Streetlight> getAllStreetlights() {
        return streetlightService.getAllStreetlights();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Streetlight> getStreetlightById(@PathVariable Long id) {
        Streetlight streetlight = streetlightService.getStreetlightById(id);
        return streetlight != null ? ResponseEntity.ok(streetlight) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Streetlight createStreetlight(@RequestBody Streetlight streetlight) {
        return streetlightService.saveStreetlight(streetlight);
    }

    @PostMapping("/with-coordinates")
    public Streetlight createStreetlightWithCoordinates(@RequestParam String name,
                                                        @RequestParam String type,
                                                        @RequestParam String coordinates,
                                                        @RequestParam Boolean status,
                                                        @RequestParam Integer powerConsumption,
                                                        @RequestParam Integer brightness,
                                                        @RequestParam String operationalTime,
                                                        @RequestParam String lastMaintenance,
                                                        @RequestParam List<String> issues,
                                                        @RequestParam String location) { // Add location parameter
        return streetlightService.createStreetlight(name, type, coordinates, status, powerConsumption, brightness, operationalTime, lastMaintenance, issues, location);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Streetlight> updateStreetlight(@PathVariable Long id, @RequestBody Streetlight streetlight) {
        streetlight.setId(id);
        Streetlight updatedStreetlight = streetlightService.saveStreetlight(streetlight);
        return ResponseEntity.ok(updatedStreetlight);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStreetlight(@PathVariable Long id) {
        streetlightService.deleteStreetlight(id);
        return ResponseEntity.noContent().build();
    }
}
