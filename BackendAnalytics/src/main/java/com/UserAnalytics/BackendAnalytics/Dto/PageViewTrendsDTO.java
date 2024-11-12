package com.UserAnalytics.BackendAnalytics.Dto;



import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageViewTrendsDTO {
    private String url;
    private String date;
    private Long pageViews;
}

