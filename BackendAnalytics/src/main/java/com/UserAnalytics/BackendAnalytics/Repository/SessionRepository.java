package com.UserAnalytics.BackendAnalytics.Repository;

import com.UserAnalytics.BackendAnalytics.Model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {  // Use Long as the ID type

    @Query("SELECT COUNT(s) FROM Session s WHERE s.sessionStartTime BETWEEN :startDate AND :endDate")
    long countSessions(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT AVG(EXTRACT(EPOCH FROM (s.session_end_time - s.session_start_time))) " +
            "FROM session s WHERE s.session_start_time BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    Double averageSessionDuration(@Param("startDate") LocalDateTime startDate,
                                  @Param("endDate") LocalDateTime endDate);


    @Query("SELECT COUNT(s) / COUNT(DISTINCT s.userId) FROM Session s WHERE s.sessionStartTime BETWEEN :startDate AND :endDate")
    Double averageSessionsPerUser(@Param("startDate") LocalDateTime startDate,
                                  @Param("endDate") LocalDateTime endDate);
}
