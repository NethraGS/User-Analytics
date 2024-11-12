package com.UserAnalytics.BackendAnalytics.Dto;


import lombok.Data;

@Data
public class PageViewStatsDTO {
    private String pagePath;
    private Long views;
    private Long users;
    private Long viewsPerUser;

    public PageViewStatsDTO(String pagePath, Long views, Long users, Long viewsPerUser) {
        this.pagePath = pagePath;
        this.views = views;
        this.users = users;
        this.viewsPerUser = viewsPerUser;
    }
}



