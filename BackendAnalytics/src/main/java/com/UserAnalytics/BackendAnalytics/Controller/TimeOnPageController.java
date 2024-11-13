package com.UserAnalytics.BackendAnalytics.Controller;


import com.UserAnalytics.BackendAnalytics.Model.TimeOnPage;
import com.UserAnalytics.BackendAnalytics.Service.TimeOnPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class TimeOnPageController {

    private final TimeOnPageService timeOnPageService;

    @Autowired
    public TimeOnPageController(TimeOnPageService timeOnPageService) {
        this.timeOnPageService = timeOnPageService;
    }

    @PostMapping("/time-on-page")
    public ResponseEntity<TimeOnPage> saveTimeOnPage(@RequestBody TimeOnPage timeOnPage) {
        timeOnPage.setTimestamp(Instant.now()); // Setting current timestamp if not provided
        TimeOnPage savedRecord = timeOnPageService.saveTimeOnPage(timeOnPage);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    @GetMapping("/total-time-spent")
    public List<Object[]> getTotalTimeSpent() {
        return timeOnPageService.getTotalTimeSpentByUrl();
    }
}

