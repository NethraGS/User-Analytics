package com.UserAnalytics.BackendAnalytics.Controller;

import com.UserAnalytics.BackendAnalytics.Dto.PageEventDTO;
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

    @PostMapping("/track-event")
    public ResponseEntity<String> trackEvent(@RequestBody TrackingEvent event) {
        try {
            trackingEventService.saveTrackingEvent(event);
            return new ResponseEntity<>("Event tracked successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error tracking event", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/events")
    public ResponseEntity<List<TrackingEvent>> getEvents() {
        List<TrackingEvent> events = trackingEventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
    @GetMapping("/page-urls")
    public ResponseEntity<List<String>> getPageUrls() {
        List<String> pageUrls = trackingEventService.getPageUrls();
        return new ResponseEntity<>(pageUrls, HttpStatus.OK);
    }


    @GetMapping("/event-overview")
    public ResponseEntity<List<Object[]>> getEventOverview() {
        List<Object[]> eventOverview = trackingEventService.getEventOverview();
        return new ResponseEntity<>(eventOverview, HttpStatus.OK);
    }



    @GetMapping("/event-statistics")
    public ResponseEntity<List<Object[]>> getEventStatistics() {
        List<Object[]> eventStatistics = trackingEventService.getEventStatistics();
        return new ResponseEntity<>(eventStatistics, HttpStatus.OK);
    }


    @GetMapping("/top-events")
    public ResponseEntity<List<Object[]>> getTopEvents() {
        List<Object[]> topEvents = trackingEventService.getTopEvents();
        return new ResponseEntity<>(topEvents, HttpStatus.OK);
    }

}
