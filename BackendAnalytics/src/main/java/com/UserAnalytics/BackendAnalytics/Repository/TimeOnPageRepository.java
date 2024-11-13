package com.UserAnalytics.BackendAnalytics.Repository;




import com.UserAnalytics.BackendAnalytics.Model.TimeOnPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeOnPageRepository extends CrudRepository<TimeOnPage, Long> {

    @Query(value = "SELECT url, SUM(time_spent) AS total_time_spent " +
            "FROM time_on_page " +
            "GROUP BY url", nativeQuery = true)
    List<Object[]> findTotalTimeSpentByUrl();
}

