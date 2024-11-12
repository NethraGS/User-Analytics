package com.UserAnalytics.BackendAnalytics.Service;

import com.UserAnalytics.BackendAnalytics.Model.PageView;
import com.UserAnalytics.BackendAnalytics.Repository.PageViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    public Map<String, Long> getPageViewTrends(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        LocalDate start = parseDate(startDate, formatter);
        LocalDate end = parseDate(endDate, formatter);

        List<PageView> pageViews = pageViewRepository.findAll();
        return pageViews.stream()
                .filter(pageView -> !pageView.getTimestamp().toLocalDate().isBefore(start) && !pageView.getTimestamp().toLocalDate().isAfter(end))
                .collect(Collectors.groupingBy(pageView -> pageView.getTimestamp().toLocalDate().toString(), Collectors.counting()));
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
}
