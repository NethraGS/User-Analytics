package com.UserAnalytics.BackendAnalytics.Service;

import com.UserAnalytics.BackendAnalytics.Repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnalyticsService {

    @Autowired
    private SessionRepository sessionRepository;

    public long getTotalNumberOfSessions(LocalDateTime startDate, LocalDateTime endDate) {
        return sessionRepository.countSessions(startDate, endDate);
    }

    public Double getAverageSessionDuration(LocalDateTime startDate, LocalDateTime endDate) {
        return sessionRepository.averageSessionDuration(startDate, endDate);
    }

    public Double getAverageSessionsPerUser(LocalDateTime startDate, LocalDateTime endDate) {
        return sessionRepository.averageSessionsPerUser(startDate, endDate);
    }
}
