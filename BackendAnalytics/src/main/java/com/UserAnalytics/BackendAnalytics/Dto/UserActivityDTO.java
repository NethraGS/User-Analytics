package com.UserAnalytics.BackendAnalytics.Dto;

import lombok.Data;

import java.time.Instant;

@Data
public class UserActivityDTO {
    private String username; // Include username or user identifier
    private String sessionId;
    private String type; // e.g., page_visit, click, etc.
    private String page; // Current page
    private String target; // Element interacted with
    private String key; // Key pressed (if applicable)
    private Long timeOnPage; // Time spent on the page in milliseconds
    private Instant timestamp; // Timestamp of the activity
}

