package com.UserAnalytics.BackendAnalytics.Controller;

import com.UserAnalytics.BackendAnalytics.Model.SessionData;
import com.UserAnalytics.BackendAnalytics.Service.SessionDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SessionDataController {

    private final SessionDataService sessionDataService;

    @PostMapping("/trackEvent")
    public ResponseEntity<String> trackEvent(@RequestBody SessionData sessionData) {
        // Set the current timestamp for when the data is received
        sessionData.setTimestamp(LocalDateTime.now());

        // Validate and populate sessionStartTime and sessionEndTime if they are not set
        if (sessionData.getSessionStartTime() == null) {
            // Handle cases where session start time is not provided
            return new ResponseEntity<>("Session start time is required.", HttpStatus.BAD_REQUEST);
        }

        if (sessionData.getSessionEndTime() == null) {
            // Handle cases where session end time is not provided
            return new ResponseEntity<>("Session end time is required.", HttpStatus.BAD_REQUEST);
        }

        // Save the session data to the database
        sessionDataService.saveSessionData(sessionData);
        return new ResponseEntity<>("Session data stored successfully.", HttpStatus.OK);
    }
}
