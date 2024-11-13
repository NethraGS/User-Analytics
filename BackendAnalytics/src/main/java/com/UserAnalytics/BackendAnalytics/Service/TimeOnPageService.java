package com.UserAnalytics.BackendAnalytics.Service;




import com.UserAnalytics.BackendAnalytics.Model.TimeOnPage;
import com.UserAnalytics.BackendAnalytics.Repository.TimeOnPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeOnPageService {

    private final TimeOnPageRepository timeOnPageRepository;

    @Autowired
    public TimeOnPageService(TimeOnPageRepository timeOnPageRepository) {
        this.timeOnPageRepository = timeOnPageRepository;
    }

    public TimeOnPage saveTimeOnPage(TimeOnPage timeOnPage) {
        return timeOnPageRepository.save(timeOnPage);
    }
    public List<Object[]> getTotalTimeSpentByUrl() {
        return timeOnPageRepository.findTotalTimeSpentByUrl();
    }
}
