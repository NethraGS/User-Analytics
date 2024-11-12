package com.UserAnalytics.BackendAnalytics.Controller;

import com.UserAnalytics.BackendAnalytics.Service.PageViewService;
import com.UserAnalytics.BackendAnalytics.Model.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
    public ResponseEntity<?> trackPageView(@RequestBody PageViewRequest pageViewRequest) {
        try {
            LocalDateTime timestamp = LocalDateTime.parse(pageViewRequest.getTimestamp());
            pageViewService.recordPageView(
                    pageViewRequest.getUrl(),
                    pageViewRequest.getSessionId(),
                    pageViewRequest.getUserId(),
                    pageViewRequest.getUserRole(),
                    timestamp
            );
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid timestamp format. Expected format: yyyy-MM-dd'T'HH:mm:ss.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/total-page-views")
    public long getTotalPageViews() {
        return pageViewService.getTotalPageViews();
    }

    @GetMapping("/unique-page-views")
    public Map<String, Long> getUniquePageViews() {
        return pageViewService.getUniquePageViews();
    }

    @GetMapping("/page-views-by-role")
    public ResponseEntity<Map<String, Long>> getPageViewsByRole() {
        try {
            Map<String, Long> pageViewsByRole = pageViewService.getPageViewsByRole();
            return ResponseEntity.ok(pageViewsByRole);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/page-view-trends")
    public ResponseEntity<?> getPageViewTrends(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            Map<String, Long> trends = pageViewService.getPageViewTrends(startDate, endDate);
            return ResponseEntity.ok(trends);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/top-pages")
    public ResponseEntity<?> getTopPagesByViews(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            List<Map.Entry<String, Long>> topPages = pageViewService.getTopPagesByViews(startDate, endDate);
            return ResponseEntity.ok(topPages);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public static class PageViewRequest {
        private String url;
        private String sessionId;
        private String userId;
        private String userRole;
        private String timestamp;

        // Getters and Setters
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
