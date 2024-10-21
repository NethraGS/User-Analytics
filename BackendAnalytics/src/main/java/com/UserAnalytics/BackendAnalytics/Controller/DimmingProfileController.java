package com.UserAnalytics.BackendAnalytics.Controller;

import com.UserAnalytics.BackendAnalytics.Model.DimmingProfile;
import com.UserAnalytics.BackendAnalytics.Service.DimmingProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class DimmingProfileController {

    private final DimmingProfileService service;

    @Autowired
    public DimmingProfileController(DimmingProfileService service) {
        this.service = service;
    }

    @GetMapping
    public List<DimmingProfile> getAllProfiles() {
        return service.getAllProfiles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DimmingProfile> getProfileById(@PathVariable Long id) {
        return service.getProfileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DimmingProfile createProfile(@RequestBody DimmingProfile profile) {
        return service.createProfile(profile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DimmingProfile> updateProfile(@PathVariable Long id, @RequestBody DimmingProfile profile) {
        return ResponseEntity.ok(service.updateProfile(id, profile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        service.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}

