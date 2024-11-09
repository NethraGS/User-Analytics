package com.UserAnalytics.BackendAnalytics.Controller;

import com.UserAnalytics.BackendAnalytics.Dto.UserJourneyCount;
import com.UserAnalytics.BackendAnalytics.Model.UserJourney;
import com.UserAnalytics.BackendAnalytics.Service.UserJourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserJourneyController {

    private final UserJourneyService userJourneyService;

    @Autowired
    public UserJourneyController(UserJourneyService userJourneyService) {
        this.userJourneyService = userJourneyService;
    }

    @PostMapping("/track-user-journey")
    public String trackUserJourney(@RequestBody UserJourney userJourney) {
        userJourneyService.saveUserJourney(userJourney);
        return "User Journey tracked successfully!";
    }

    @GetMapping("/preferred-paths")
    public List<UserJourneyCount> getMostPreferredPaths() {
        return userJourneyService.getMostPreferredPaths();
    }
}
