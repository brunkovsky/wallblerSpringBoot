package com.nkoad.wallbler.main.repository;

import com.nkoad.wallbler.main.model.WallblerScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WallblerSchedulerRepository extends JpaRepository<WallblerScheduler, String> {

    @Query(value = "UPDATE wallbler_scheduler SET last_time_fetched = :lastTimeFetched WHERE scheduler_name = :schedulerName", nativeQuery = true)
    void updateLastTimeFetched(@Param("lastTimeFetched") Date lastTimeFetched, @Param("schedulerName") String schedulerName);

    @Query(value = "SELECT * FROM wallbler_scheduler WHERE wallbler_type = :type", nativeQuery = true)
    List<WallblerScheduler> findAllByType(@Param("type") String type);

//    @Query(value = "select *, CASE\n" +
//            "           WHEN TIMEDIFF(SUBDATE(NOW(), INTERVAL period MINUTE), last_time_fetched) > 0\n" +
//            "               THEN true\n" +
//            "           ELSE false\n" +
//            "           END AS to_update\n" +
//            "from wallbler_scheduler_config", nativeQuery = true)
//    List<?> toCheckToUpdate();

}
