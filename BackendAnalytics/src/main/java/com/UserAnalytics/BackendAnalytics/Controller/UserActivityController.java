package com.UserAnalytics.BackendAnalytics.Controller;

import com.UserAnalytics.BackendAnalytics.Model.UserActivity;
import com.UserAnalytics.BackendAnalytics.Service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/track")
public class UserActivityController {

    private final UserActivityService userActivityService;

    @Autowired
    public UserActivityController(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @PostMapping
    public ResponseEntity<UserActivity> trackUserActivity(@RequestBody UserActivity userActivity) {
        UserActivity savedActivity = userActivityService.saveUserActivity(userActivity);
        return ResponseEntity.ok(savedActivity);
    }
}
