package com.UserAnalytics.BackendAnalytics.Service;

import com.UserAnalytics.BackendAnalytics.Dto.SessionRequest;
import com.UserAnalytics.BackendAnalytics.Model.Session;
import com.UserAnalytics.BackendAnalytics.Repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public Session storeSessionDetails(SessionRequest sessionRequest) {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime sessionStartTime = LocalDateTime.parse(sessionRequest.getSessionStartTime(), formatter);
        LocalDateTime sessionEndTime = LocalDateTime.parse(sessionRequest.getSessionEndTime(), formatter);

        long sessionDuration = java.time.Duration.between(sessionStartTime, sessionEndTime).toMillis();


        Session session = Session.builder()
                .sessionId(sessionRequest.getSessionId())
                .userId(sessionRequest.getUserId())
                .userRole(sessionRequest.getUserRole())
                .sessionStartTime(sessionStartTime)
                .sessionEndTime(sessionEndTime)
                .sessionDuration(sessionDuration)
                .build();

        return sessionRepository.save(session);
    }
}
