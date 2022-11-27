package com.nkoad.wallbler.main.repository;

import com.nkoad.wallbler.main.model.WallblerAccessTokenScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WallblerAccessTokenSchedulerRepository extends JpaRepository<WallblerAccessTokenScheduler, String> {

    @Query(value = "UPDATE wallbler_access_token_scheduler " +
            "SET last_time_fetched = :lastTimeFetched " +
            "WHERE scheduler_name = :schedulerName", nativeQuery = true)
    void updateLastTimeFetched(@Param("lastTimeFetched") Date lastTimeFetched, @Param("schedulerName") String schedulerName);

    @Query(value = "UPDATE wallbler_access_token_scheduler " +
            "SET enable = :enable ," +
            "period = :period " +
            "WHERE scheduler_name = :schedulerName", nativeQuery = true)
    void updateScheduler(@Param("enable") boolean enable, @Param("period") int period, @Param("schedulerName") String schedulerName);

    @Query(value = "SELECT * FROM wallbler_access_token_scheduler " +
            "WHERE wallbler_names LIKE CONCAT('%', :type, '%')", nativeQuery = true)
    List<WallblerAccessTokenScheduler> findAllByType(@Param("type") String type);

    @Query(value = "UPDATE wallbler_access_token_scheduler " +
            "SET wallbler_names = IF(wallbler_names IS NULL, :wallblerName, CONCAT(wallbler_names, IF(wallbler_names = '', '', '|'), :wallblerName)) " +
            "WHERE scheduler_name = :schedulerName", nativeQuery = true)
    void registerAccessToken(@Param("wallblerName") String wallblerName, @Param("schedulerName") String schedulerName);

    @Query(value = "UPDATE wallbler_access_token_scheduler " +
            "SET wallbler_names = REPLACE(wallbler_names, IF(wallbler_names = :wallblerName, :wallblerName, IF(wallbler_names LIKE CONCAT(:wallblerName,'%'), CONCAT(:wallblerName,'|'), CONCAT('|',:wallblerName))), '') " +
            "WHERE scheduler_name = :schedulerName", nativeQuery = true)
    void unRegisterAccessToken(@Param("wallblerName") String wallblerName, @Param("schedulerName") String schedulerName);

    @Query(value = "SELECT scheduler_name FROM wallbler_access_token_scheduler " +
            "WHERE wallbler_names LIKE CONCAT('%', :wallblerName, '%')", nativeQuery = true)
    List<String> findSchedulersByWallblerName(@Param("wallblerName") String wallblerName);

}
