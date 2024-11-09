package com.UserAnalytics.BackendAnalytics.Dto;

public class UserJourneyCount {
    private String previousPage;
    private String currentPage;
    private long pathCount;

    public UserJourneyCount(String previousPage, String currentPage, long pathCount) {
        this.previousPage = previousPage;
        this.currentPage = currentPage;
        this.pathCount = pathCount;
    }

    public String getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(String previousPage) {
        this.previousPage = previousPage;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public long getPathCount() {
        return pathCount;
    }

    public void setPathCount(long pathCount) {
        this.pathCount = pathCount;
    }
}
