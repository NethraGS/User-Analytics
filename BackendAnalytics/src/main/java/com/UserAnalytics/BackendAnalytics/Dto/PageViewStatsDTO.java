package com.UserAnalytics.BackendAnalytics.Dto;


import lombok.Data;

@Data
public class PageViewStatsDTO {
    private String pagePath;
    private Long views;
    private Long users;
    private Long viewsPerUser;
    private Long totalTimeSpentMinutes;

    public PageViewStatsDTO(String pagePath, Long views, Long users, Long viewsPerUser, Long totalTimeSpentMinutes) {
        this.pagePath = pagePath;
        this.views = views;
        this.users = users;
        this.viewsPerUser = viewsPerUser;
        this.totalTimeSpentMinutes = totalTimeSpentMinutes;
    }
}



