package com.UserAnalytics.BackendAnalytics.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionRequest {

    private String sessionId;
    private String userId;
    private String userRole;
    private String sessionStartTime;
    private String sessionEndTime;
}

