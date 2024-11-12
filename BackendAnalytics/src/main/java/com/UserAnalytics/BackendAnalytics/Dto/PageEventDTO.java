package com.UserAnalytics.BackendAnalytics.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PageEventDTO {
    private String action;
    private String elementText;
    private Long eventCount;


}
