package com.UserAnalytics.BackendAnalytics.Controller;

import com.UserAnalytics.BackendAnalytics.Model.DimmingSchedule;
import com.UserAnalytics.BackendAnalytics.Service.DimmingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class DimmingScheduleController {

    @Autowired
    private DimmingScheduleService service;

    @GetMapping
    public List<DimmingSchedule> getAllSchedules() {
        return service.getAllSchedules();
    }

    @PostMapping
    public ResponseEntity<DimmingSchedule> createSchedule(@RequestBody DimmingSchedule schedule) {
        DimmingSchedule newSchedule = service.addSchedule(schedule);
        return ResponseEntity.ok(newSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        service.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

    // Added @PutMapping for updating a schedule
    @PutMapping("/{id}")
    public ResponseEntity<DimmingSchedule> updateSchedule(@PathVariable Long id, @RequestBody DimmingSchedule schedule) {
        DimmingSchedule updatedSchedule = service.updateSchedule(id, schedule);
        if (updatedSchedule != null) {
            return ResponseEntity.ok(updatedSchedule);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
