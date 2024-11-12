package com.UserAnalytics.BackendAnalytics.Repository;

import com.UserAnalytics.BackendAnalytics.Dto.PageViewStatsDTO;
import com.UserAnalytics.BackendAnalytics.Model.PageView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageViewRepository extends JpaRepository<PageView, Long> {

    @Query(value="SELECT p.url, COUNT(p.id), COUNT(DISTINCT p.user_id), COUNT(p.id) / NULLIF(COUNT(DISTINCT p.user_id), 0)" +
            "FROM page_view p " +
            "GROUP BY p.url",nativeQuery = true)
    List<Object[]>getPageViewStatistics();

}

