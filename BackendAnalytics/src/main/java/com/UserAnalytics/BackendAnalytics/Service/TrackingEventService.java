package com.UserAnalytics.BackendAnalytics.Service;

import com.UserAnalytics.BackendAnalytics.Dto.PageEventDTO;
import com.UserAnalytics.BackendAnalytics.Model.TrackingEvent;
import com.UserAnalytics.BackendAnalytics.Repository.TrackingEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<String> getPageUrls() {
        return trackingEventRepository.getPageUrls();
    }

    // Method to get Event Overview
    public List<Object[]> getEventOverview() {
        return trackingEventRepository.getEventOverview();
    }

    // Method to get Top Events
    public List<Object[]> getTopEvents() {
        return trackingEventRepository.getTopEvents();
    }

    public List<Object[]> getEventStatistics() {
        return trackingEventRepository.getEventStatistics();
    }
    public List<PageEventDTO> getEventsByPage(String pageUrl) {
        List<Object[]> result = trackingEventRepository.getEventsByPage(pageUrl);
        return result.stream().map(obj -> new PageEventDTO((String) obj[0], (String) obj[1], (Long) obj[2])).collect(Collectors.toList());
    }



}
