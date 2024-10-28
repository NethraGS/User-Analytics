package com.UserAnalytics.BackendAnalytics.Service;

import com.UserAnalytics.BackendAnalytics.Model.SessionData;
import com.UserAnalytics.BackendAnalytics.Repository.SessionDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionDataService {

    private final SessionDataRepository sessionDataRepository;

    public SessionData saveSessionData(SessionData sessionData) {
        return sessionDataRepository.save(sessionData);
    }
}
