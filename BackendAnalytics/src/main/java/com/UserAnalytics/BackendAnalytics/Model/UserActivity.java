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
    private Users user;
    @Column(nullable = false)
    private String sessionId;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String page;

    @Column
    private Long timeOnPage;

    @Column(nullable = false)
    private Date timestamp;


}
