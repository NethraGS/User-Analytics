package com.UserAnalytics.BackendAnalytics.Repository;

import com.UserAnalytics.BackendAnalytics.Model.UserJourney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserJourneyRepository extends JpaRepository<UserJourney, Long> {

    @Query("SELECT u.previousPage, u.currentPage, COUNT(u) AS pathCount " +
            "FROM UserJourney u GROUP BY u.previousPage, u.currentPage ORDER BY pathCount DESC")
    List<Object[]> findMostPreferredPaths();
}
