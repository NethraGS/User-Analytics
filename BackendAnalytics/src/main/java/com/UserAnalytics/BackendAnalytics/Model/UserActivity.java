package com.UserAnalytics.BackendAnalytics.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_activity")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user; // The user who generated the activity

    @Column(nullable = false)
    private String sessionId; // Unique session identifier

    @Column(nullable = false)
    private String type; // Type of event (e.g., click, page_visit, session_start, etc.)

    @Column(nullable = false)
    private String page; // The page where the activity occurred

    @Column
    private Long timeOnPage; // Time spent on the page

    @Column(nullable = false)
    private Date timestamp; // Timestamp of the event

    // Additional fields for custom actions and demographics can be added here
}
