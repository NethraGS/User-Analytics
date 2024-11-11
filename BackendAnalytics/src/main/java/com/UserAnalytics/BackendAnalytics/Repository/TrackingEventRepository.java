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

    // Custom SQL query for Top Events: Grouping by action
    @Query(value = "SELECT action, COUNT(*) AS event_count " +
            "FROM tracking_event " +
            "GROUP BY action " +
            "ORDER BY event_count DESC", nativeQuery = true)
    List<Object[]> getTopEvents();
}
