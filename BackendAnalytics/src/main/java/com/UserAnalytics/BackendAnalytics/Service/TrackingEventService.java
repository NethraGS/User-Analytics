package com.UserAnalytics.BackendAnalytics.Service;

import com.UserAnalytics.BackendAnalytics.Model.TrackingEvent;
import com.UserAnalytics.BackendAnalytics.Repository.TrackingEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackingEventService {

    @Autowired
    private TrackingEventRepository trackingEventRepository;

    public TrackingEvent saveTrackingEvent(TrackingEvent event) {
        return trackingEventRepository.save(event);
    }

    public List<TrackingEvent> getAllEvents() {
        return trackingEventRepository.findAll();
    }

    // Method to get Event Overview
    public List<Object[]> getEventOverview() {
        return trackingEventRepository.getEventOverview();
    }

    // Method to get Top Events
    public List<Object[]> getTopEvents() {
        return trackingEventRepository.getTopEvents();
    }
}
