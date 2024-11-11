package com.UserAnalytics.BackendAnalytics.Controller;

import com.UserAnalytics.BackendAnalytics.Service.PageViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class PageViewController {

    private final PageViewService pageViewService;

    @Autowired
    public PageViewController(PageViewService pageViewService) {
        this.pageViewService = pageViewService;
    }

    @PostMapping("/track-page-view")
    @ResponseStatus(HttpStatus.CREATED)
    public void trackPageView(@RequestBody PageViewRequest pageViewRequest) {
        pageViewService.recordPageView(
                pageViewRequest.getUrl(),
                pageViewRequest.getSessionId(),
                pageViewRequest.getUserId(),
                pageViewRequest.getUserRole()
        );
    }

    @GetMapping("/total-page-views")
    public long getTotalPageViews() {
        return pageViewService.getTotalPageViews();
    }

    @GetMapping("/unique-page-views")
    public Map<String, Long> getUniquePageViews() {
        return pageViewService.getUniquePageViews();
    }

    @GetMapping("/top-pages")
    public List<Map.Entry<String, Long>> getTopPagesByViews() {
        return pageViewService.getTopPagesByViews();
    }

    @GetMapping("/page-view-trends")
    public Map<LocalDate, Long> getPageViewTrends() {
        return pageViewService.getPageViewTrends();
    }

    @GetMapping("/page-views-by-role")
    public ResponseEntity<Map<String, Long>> getPageViewsByRole() {
        try {
            Map<String, Long> pageViewsByRole = pageViewService.getPageViewsByRole();
            return ResponseEntity.ok(pageViewsByRole);
        } catch (Exception e) {
            // Log the error and return an internal server error
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // DTO for the page view request payload
    public static class PageViewRequest {
        private String url;
        private String sessionId;
        private String userId;
        private String userRole;
        private String timestamp;

        // Getters and setters
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserRole() {
            return userRole;
        }

        public void setUserRole(String userRole) {
            this.userRole = userRole;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
