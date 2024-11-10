package com.UserAnalytics.BackendAnalytics.Controller;

import com.UserAnalytics.BackendAnalytics.Service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    // Endpoint to get total number of sessions
    @GetMapping("/sessions/total")
    public ResponseEntity<Long> getTotalSessions(@RequestParam("startDate") LocalDateTime startDate,
                                                 @RequestParam("endDate") LocalDateTime endDate) {
        long totalSessions = analyticsService.getTotalNumberOfSessions(startDate, endDate);
        return ResponseEntity.ok(totalSessions);
    }

    @GetMapping("/sessions/average-duration")
    public ResponseEntity<Object> getAverageSessionDuration(@RequestParam("startDate") LocalDateTime startDate,
                                                            @RequestParam("endDate") LocalDateTime endDate) {
        try {
            Double avgDuration = analyticsService.getAverageSessionDuration(startDate, endDate);
            if (avgDuration == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data available for the given date range.");
            }
            return ResponseEntity.ok(avgDuration);
        } catch (Exception e) {
            // Log the error and return a meaningful message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


    @GetMapping("/sessions/average-sessions-per-user")
    public Double getAverageSessionsPerUser(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        return analyticsService.getAverageSessionsPerUser(
                LocalDateTime.parse(startDate),
                LocalDateTime.parse(endDate));
    }

}
