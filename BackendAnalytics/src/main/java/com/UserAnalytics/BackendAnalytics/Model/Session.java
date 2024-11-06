package com.UserAnalytics.BackendAnalytics.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(unique = true, nullable = false)
    private String sessionId;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "duration")
    private Long duration; 

    @Column(name = "user_path")
    private String userPath; 
}
