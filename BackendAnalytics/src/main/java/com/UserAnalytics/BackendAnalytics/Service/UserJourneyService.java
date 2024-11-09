package com.UserAnalytics.BackendAnalytics.Service;

import com.UserAnalytics.BackendAnalytics.Dto.UserJourneyCount;
import com.UserAnalytics.BackendAnalytics.Model.UserJourney;
import com.UserAnalytics.BackendAnalytics.Repository.UserJourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserJourneyService {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserJourneyRepository userJourneyRepository;

    @Autowired
    public UserJourneyService(UserJourneyRepository userJourneyRepository) {
        this.userJourneyRepository = userJourneyRepository;
    }

    public void saveUserJourney(UserJourney userJourney) {
        userJourneyRepository.save(userJourney);
    }

    public List<UserJourneyCount> getMostPreferredPaths() {
        List<Object[]> results = userJourneyRepository.findMostPreferredPaths();
        List<UserJourneyCount> userJourneyCounts = new ArrayList<>();

        for (Object[] result : results) {
            String previousPage = (String) result[0];
            String currentPage = (String) result[1];
            long pathCount = ((Number) result[2]).longValue();

            userJourneyCounts.add(new UserJourneyCount(previousPage, currentPage, pathCount));
        }

        return userJourneyCounts;
    }
}
