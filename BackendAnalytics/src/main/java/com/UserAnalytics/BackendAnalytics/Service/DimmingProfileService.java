package com.UserAnalytics.BackendAnalytics.Service;


import com.UserAnalytics.BackendAnalytics.Model.DimmingProfile;
import com.UserAnalytics.BackendAnalytics.Repository.DimmingProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DimmingProfileService {

    private final DimmingProfileRepository repository;

    @Autowired
    public DimmingProfileService(DimmingProfileRepository repository) {
        this.repository = repository;
    }

    public List<DimmingProfile> getAllProfiles() {
        return repository.findAll();
    }

    public Optional<DimmingProfile> getProfileById(Long id) {
        return repository.findById(id);
    }

    public DimmingProfile createProfile(DimmingProfile profile) {
        return repository.save(profile);
    }

    public DimmingProfile updateProfile(Long id, DimmingProfile profileDetails) {
        DimmingProfile profile = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setProfileName(profileDetails.getProfileName());
        profile.setProfileType(profileDetails.getProfileType());
        profile.setStartTimeLampLevel(profileDetails.getStartTimeLampLevel());
        profile.setMotionOrPhotocellStart(profileDetails.getMotionOrPhotocellStart());
        profile.setSunriseLampLevel(profileDetails.getSunriseLampLevel());
        profile.setMotionOrPhotocellSunrise(profileDetails.getMotionOrPhotocellSunrise());
        profile.setSunsetLampLevel(profileDetails.getSunsetLampLevel());
        profile.setMotionOrPhotocellSunset(profileDetails.getMotionOrPhotocellSunset());

        return repository.save(profile);
    }

    public void deleteProfile(Long id) {
        repository.deleteById(id);
    }
}

