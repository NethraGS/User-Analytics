package com.UserAnalytics.BackendAnalytics.Controller;

import com.UserAnalytics.BackendAnalytics.Model.TrackingEvent;
import com.UserAnalytics.BackendAnalytics.Service.TrackingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrackingEventController {

    @Autowired
    private TrackingEventService trackingEventService;

    // Endpoint to save tracking event data
    @PostMapping("/track-event")
    public ResponseEntity<String> trackEvent(@RequestBody TrackingEvent event) {
        try {
            trackingEventService.saveTrackingEvent(event);
            return new ResponseEntity<>("Event tracked successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error tracking event", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get all tracking events (for debugging or analysis)
    @GetMapping("/events")
    public ResponseEntity<List<TrackingEvent>> getEvents() {
        List<TrackingEvent> events = trackingEventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}