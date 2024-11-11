package com.UserAnalytics.BackendAnalytics.Service;

import com.UserAnalytics.BackendAnalytics.Model.PageView;
import com.UserAnalytics.BackendAnalytics.Repository.PageViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public PageView recordPageView(String url, String sessionId, String userId, String userRole) {
        // Create a new PageView object and set the details
        PageView pageView = new PageView();
        pageView.setUrl(url);
        pageView.setSessionId(sessionId);
        pageView.setUserId(userId);
        pageView.setUserRole(userRole);
        pageView.setTimestamp(LocalDateTime.now());

        // Save the page view to the repository
        return pageViewRepository.save(pageView);
    }

    // Method to fetch all page views
    public List<PageView> getAllPageViews() {
        return pageViewRepository.findAll();
    }

    // Method to get total page views
    public long getTotalPageViews() {
        return pageViewRepository.count();
    }

    // Method to get unique page views by counting distinct session IDs per URL
    public Map<String, Long> getUniquePageViews() {
        List<PageView> pageViews = pageViewRepository.findAll();
        return pageViews.stream()
                .collect(Collectors.groupingBy(PageView::getUrl, Collectors.mapping(PageView::getSessionId, Collectors.toSet())))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (long) entry.getValue().size()));
    }

    // Method to get top pages by views
    public List<Map.Entry<String, Long>> getTopPagesByViews() {
        List<PageView> pageViews = pageViewRepository.findAll();
        return pageViews.stream()
                .collect(Collectors.groupingBy(PageView::getUrl, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    // Method to get page view trends by date
    public Map<LocalDate, Long> getPageViewTrends() {
        List<PageView> pageViews = pageViewRepository.findAll();
        return pageViews.stream()
                .collect(Collectors.groupingBy(pageView -> pageView.getTimestamp().toLocalDate(), Collectors.counting()));
    }

    // Method to get page views by user role
    public Map<String, Long> getPageViewsByRole() {
        // Assuming you have a repository method to get the page views from the database
        List<PageView> pageViews = pageViewRepository.findAll();

        // Group page views by user role and count occurrences
        return pageViews.stream()
                .collect(Collectors.groupingBy(PageView::getUserRole, Collectors.counting()));
    }

}
