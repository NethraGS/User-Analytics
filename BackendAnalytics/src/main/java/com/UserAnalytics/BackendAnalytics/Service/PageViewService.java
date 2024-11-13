package com.UserAnalytics.BackendAnalytics.Service;

import com.UserAnalytics.BackendAnalytics.Dto.PageViewStatsDTO;
import com.UserAnalytics.BackendAnalytics.Dto.PageViewTrendsDTO;
import com.UserAnalytics.BackendAnalytics.Model.PageView;
import com.UserAnalytics.BackendAnalytics.Repository.PageViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PageViewService {



    private final PageViewRepository pageViewRepository;

    @Autowired
    public PageViewService(PageViewRepository pageViewRepository) {
        this.pageViewRepository = pageViewRepository;
    }

    public PageView recordPageView(String url, String sessionId, String userId, String userRole, LocalDateTime timestamp) {
        PageView pageView = new PageView();
        pageView.setUrl(url);
        pageView.setSessionId(sessionId);
        pageView.setUserId(userId);
        pageView.setUserRole(userRole);
        pageView.setTimestamp(timestamp);

        return pageViewRepository.save(pageView);
    }

    public long getTotalPageViews() {
        return pageViewRepository.count();
    }

    public Map<String, Long> getUniquePageViews() {
        List<PageView> pageViews = pageViewRepository.findAll();
        return pageViews.stream()
                .collect(Collectors.groupingBy(PageView::getUrl, Collectors.mapping(PageView::getSessionId, Collectors.toSet())))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (long) entry.getValue().size()));
    }

    public Map<String, Long> getPageViewsByRole() {
        List<PageView> pageViews = pageViewRepository.findAll();
        return pageViews.stream()
                .collect(Collectors.groupingBy(PageView::getUserRole, Collectors.counting()));
    }
    public List<PageViewTrendsDTO> getPageViewTrends(String startDate, String endDate) {
        // Define date format
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

        // Parse start and end date
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        // Retrieve all PageViews within the specified date range
        List<PageView> pageViews = pageViewRepository.findAll();

        // Filter and group the page views by URL and date, then map to PageViewTrendsDTO
        Map<String, Long> groupedData = pageViews.stream()
                .filter(pageView -> {
                    LocalDate pageViewDate = pageView.getTimestamp().toLocalDate();
                    return !pageViewDate.isBefore(start) && !pageViewDate.isAfter(end);
                })
                .collect(Collectors.groupingBy(
                        pageView -> pageView.getUrl() + "|" + pageView.getTimestamp().toLocalDate().toString(),
                        Collectors.counting()
                ));

        // Convert grouped data to List<PageViewTrendsDTO>
        return groupedData.entrySet().stream()
                .map(entry -> {
                    String[] keyParts = entry.getKey().split("\\|");
                    String url = keyParts[0];
                    String date = keyParts[1];
                    Long pageViewsCount = entry.getValue();
                    return new PageViewTrendsDTO(url, date, pageViewsCount);
                })
                .collect(Collectors.toList());
    }


    public List<Map.Entry<String, Long>> getTopPagesByViews(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime start = parseDateTime(startDate, formatter);
        LocalDateTime end = parseDateTime(endDate, formatter);

        List<PageView> pageViews = pageViewRepository.findAll();
        return pageViews.stream()
                .filter(pageView -> !pageView.getTimestamp().isBefore(start) && !pageView.getTimestamp().isAfter(end))
                .collect(Collectors.groupingBy(PageView::getUrl, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    private LocalDate parseDate(String date, DateTimeFormatter formatter) {
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected yyyy-MM-dd.", e);
        }
    }

    private LocalDateTime parseDateTime(String dateTime, DateTimeFormatter formatter) {
        try {
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date-time format. Expected yyyy-MM-dd'T'HH:mm:ss.", e);
        }
    }
    public List<PageViewStatsDTO> getPageViewStatistics() {
        List<Object[]> result = pageViewRepository.getPageViewStatistics();
        List<PageViewStatsDTO> pageViewStats = new ArrayList<>();

        for (Object[] row : result) {
            String pagePath = (String) row[0];
            Long views = (Long) row[1];
            Long users = (Long) row[2];
            Long viewsPerUser = (Long) row[3];
            Long totalTimeSpentMinutes = new BigDecimal(String.valueOf(row[4]))  // Assuming row[4] contains the BigDecimal value as String
                    .setScale(2, RoundingMode.DOWN)  // Truncate to 2 decimal places
                    .divide(new BigDecimal(6000), RoundingMode.DOWN)  // Divide by 6000
                    .longValue();  // Convert to Long



            pageViewStats.add(new PageViewStatsDTO(pagePath, views, users, viewsPerUser,totalTimeSpentMinutes));
        }

        return pageViewStats;
    }
}
