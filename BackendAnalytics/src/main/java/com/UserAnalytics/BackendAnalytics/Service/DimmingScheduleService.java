package com.UserAnalytics.BackendAnalytics.Service;

import com.UserAnalytics.BackendAnalytics.Model.DimmingSchedule;
import com.UserAnalytics.BackendAnalytics.Repository.DimmingScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DimmingScheduleService {

    @Autowired
    private DimmingScheduleRepository repository;

    public List<DimmingSchedule> getAllSchedules() {
        return repository.findAll();
    }

    public DimmingSchedule addSchedule(DimmingSchedule schedule) {
        return repository.save(schedule);
    }

    public void deleteSchedule(Long id) {
        repository.deleteById(id);
    }


    public DimmingSchedule updateSchedule(Long id, DimmingSchedule schedule) {
        Optional<DimmingSchedule> existingSchedule = repository.findById(id);
        if (existingSchedule.isPresent()) {
            DimmingSchedule scheduleToUpdate = existingSchedule.get();

            scheduleToUpdate.setStartDate(schedule.getStartDate());
            scheduleToUpdate.setEndDate(schedule.getEndDate());
            scheduleToUpdate.setFrequency(schedule.getFrequency());

            return repository.save(scheduleToUpdate);
        } else {
            return null;
        }
    }
}
