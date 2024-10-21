package com.UserAnalytics.BackendAnalytics.Repository;



import com.UserAnalytics.BackendAnalytics.Model.DimmingProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DimmingProfileRepository extends JpaRepository<DimmingProfile, Long> {
}

