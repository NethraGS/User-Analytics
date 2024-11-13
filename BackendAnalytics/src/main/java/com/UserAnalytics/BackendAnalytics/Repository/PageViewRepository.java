package com.UserAnalytics.BackendAnalytics.Repository;

import com.UserAnalytics.BackendAnalytics.Dto.PageViewStatsDTO;
import com.UserAnalytics.BackendAnalytics.Model.PageView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageViewRepository extends JpaRepository<PageView, Long> {

    @Query(value = "WITH ordered_views AS ( " +
            "    SELECT " +
            "        user_id, " +
            "        url, " +
            "        timestamp, " +
            "        LEAD(timestamp) OVER (PARTITION BY user_id ORDER BY timestamp) AS next_timestamp " +
            "    FROM " +
            "        page_view " +
            ") " +
            "SELECT " +
            "    p.url, " +
            "    COUNT(p.id) AS views, " +
            "    COUNT(DISTINCT p.user_id) AS users, " +
            "    COUNT(p.id) / NULLIF(COUNT(DISTINCT p.user_id), 0) AS views_per_user, " +
            "    COALESCE(TRUNC(SUM(EXTRACT(EPOCH FROM (o.next_timestamp - o.timestamp))), 2), 0) AS total_time_spent_minutes " +  // Converted to minutes and truncated
            "FROM " +
            "    page_view p " +
            "LEFT JOIN " +
            "    ordered_views o ON p.url = o.url AND p.timestamp = o.timestamp " +
            "GROUP BY " +
            "    p.url", nativeQuery = true)
    List<Object[]> getPageViewStatistics();



    @Query(value = "SELECT p.url, DATE(p.timestamp) as date, COUNT(p.id) as pageViews " +
            "FROM page_view p " +
            "WHERE p.timestamp BETWEEN CAST(:startDate AS TIMESTAMP) AND CAST(:endDate AS TIMESTAMP) " +
            "GROUP BY p.url, DATE(p.timestamp) " +
            "ORDER BY date", nativeQuery = true)
    List<Object[]> getPageViewTrends(@Param("startDate") String startDate, @Param("endDate") String endDate);

}

