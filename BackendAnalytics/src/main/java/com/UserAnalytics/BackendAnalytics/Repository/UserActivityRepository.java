package com.UserAnalytics.BackendAnalytics.Repository;

import com.UserAnalytics.BackendAnalytics.Model.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

}
