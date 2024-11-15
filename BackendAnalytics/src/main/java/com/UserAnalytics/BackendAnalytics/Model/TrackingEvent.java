package com.UserAnalytics.BackendAnalytics.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class TrackingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;
    private String elementId;
    private String elementClass;
    private String elementText;
    private String elementType;

    private String pageName;
    private String url;
    private String userId;
    private String userRole;
    private String depth;
    private LocalDateTime timestamp;

    @PrePersist
    public void setTimestamp() {
        this.timestamp = LocalDateTime.now();
    }
}
