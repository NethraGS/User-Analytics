package com.UserAnalytics.BackendAnalytics.Dto;

import lombok.Data;

import java.time.Instant;

@Data
public class UserActivityDTO {
    private String username;
    private String sessionId;
    private String type;
    private String page;
    private String target;
    private String key;
    private Long timeOnPage;
    private Instant timestamp;
}

