package com.UserAnalytics.BackendAnalytics.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "session_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "page_url", nullable = false)
    private String pageUrl;

    @Column(name = "session_start_time", nullable = false)
    private LocalDateTime sessionStartTime;

    @Column(name = "session_end_time", nullable = false)
    private LocalDateTime sessionEndTime;

    @Column(name = "duration_seconds", nullable = false)
    private Long durationSeconds;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
}
