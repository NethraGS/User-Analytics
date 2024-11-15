package com.UserAnalytics.BackendAnalytics.Controller;

import com.UserAnalytics.BackendAnalytics.Dto.SessionRequest;
import com.UserAnalytics.BackendAnalytics.Model.Session;
import com.UserAnalytics.BackendAnalytics.Service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;


    @PostMapping("/store")
    public Session storeSession(@RequestBody SessionRequest sessionRequest) {
        return sessionService.storeSessionDetails(sessionRequest);
    }
}
