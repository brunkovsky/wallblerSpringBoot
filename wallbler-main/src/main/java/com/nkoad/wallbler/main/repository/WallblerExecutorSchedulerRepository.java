package com.nkoad.wallbler.main.repository;

import com.nkoad.wallbler.main.model.WallblerExecutorScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WallblerExecutorSchedulerRepository extends JpaRepository<WallblerExecutorScheduler, String> {

    @Query(value = "UPDATE wallbler_executor_scheduler " +
            "SET last_time_fetched = :lastTimeFetched " +
            "WHERE scheduler_name = :schedulerName", nativeQuery = true)
    void updateLastTimeFetched(@Param("lastTimeFetched") Date lastTimeFetched, @Param("schedulerName") String schedulerName);

    @Query(value = "UPDATE wallbler_executor_scheduler " +
            "SET enable = :enable ," +
            "period = :period " +
            "WHERE scheduler_name = :schedulerName", nativeQuery = true)
    void updateScheduler(@Param("enable") boolean enable, @Param("period") int period, @Param("schedulerName") String schedulerName);

    @Query(value = "SELECT * FROM wallbler_executor_scheduler " +
            "WHERE wallbler_names LIKE CONCAT('%', :type, '%')", nativeQuery = true)
    List<WallblerExecutorScheduler> findAllByType(@Param("type") String type);

    @Query(value = "UPDATE wallbler_executor_scheduler " +
            "SET wallbler_names = IF(wallbler_names IS NULL, :wallblerName, CONCAT(wallbler_names, IF(wallbler_names = '', '', '|'), :wallblerName)) " +
            "WHERE scheduler_name = :schedulerName", nativeQuery = true)
    void registerWallblerExecutor(@Param("wallblerName") String wallblerName, @Param("schedulerName") String schedulerName);

    @Query(value = "UPDATE wallbler_executor_scheduler " +
            "SET wallbler_names = REPLACE(wallbler_names, IF(wallbler_names = :wallblerName, :wallblerName, IF(wallbler_names LIKE CONCAT(:wallblerName,'%'), CONCAT(:wallblerName,'|'), CONCAT('|',:wallblerName))), '') " +
            "WHERE scheduler_name = :schedulerName", nativeQuery = true)
    void unRegisterExecutor(@Param("wallblerName") String wallblerName, @Param("schedulerName") String schedulerName);

    @Query(value = "SELECT scheduler_name FROM wallbler_executor_scheduler " +
            "WHERE wallbler_names LIKE CONCAT('%', :wallblerName, '%')", nativeQuery = true)
    List<String> findSchedulersByWallblerName(@Param("wallblerName") String wallblerName);

//    @Query(value = "select *, CASE\n" +`
//            "           WHEN TIMEDIFF(SUBDATE(NOW(), INTERVAL period MINUTE), last_time_fetched) > 0\n" +
//            "               THEN true\n" +
//            "           ELSE false\n" +
//            "           END AS to_update\n" +
//            "from wallbler_scheduler_config", nativeQuery = true)
//    List<?> toCheckToUpdate();

}
