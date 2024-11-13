package com.UserAnalytics.BackendAnalytics.Repository;

import com.UserAnalytics.BackendAnalytics.Model.TrackingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingEventRepository extends JpaRepository<TrackingEvent, Long> {

    // Custom SQL query for Event Overview: Grouping by action, elementId, and elementType
    @Query(value = "SELECT action, element_id, element_type, COUNT(*) AS event_count " +
            "FROM tracking_event " +
            "GROUP BY action, element_id, element_type", nativeQuery = true)
    List<Object[]> getEventOverview();

    @Query(value = "SELECT action, url, COUNT(*) AS event_count " +
            "FROM tracking_event " +
            "GROUP BY action, url " +
            "ORDER BY event_count DESC", nativeQuery = true)
    List<Object[]> getTopEvents();


    @Query(value = "SELECT action AS eventName, COUNT(*) AS eventCount, " +
            "COUNT(DISTINCT user_id) AS totalUsers, " +
            "ROUND(COUNT(*) / NULLIF(COUNT(DISTINCT user_id), 0), 2) AS eventCountPerUser " +
            "FROM tracking_event " +
            "GROUP BY action", nativeQuery = true)
    List<Object[]> getEventStatistics();
    @Query("SELECT DISTINCT url FROM TrackingEvent")
    List<String> getPageUrls();

    @Query(value = "SELECT action, element_text, COUNT(*) AS event_count " +
            "FROM tracking_event " +
            "WHERE url = ?1 " +
            "GROUP BY action, element_text", nativeQuery = true)
    List<Object[]> getEventsByPage(String pageUrl);

}
