package com.UserAnalytics.BackendAnalytics.Repository;

import com.UserAnalytics.BackendAnalytics.Model.DimmingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DimmingScheduleRepository extends JpaRepository<DimmingSchedule, Long> {
}
