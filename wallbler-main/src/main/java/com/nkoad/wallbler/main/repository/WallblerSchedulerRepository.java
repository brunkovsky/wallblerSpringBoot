package com.nkoad.wallbler.main.repository;

import com.nkoad.wallbler.main.model.WallblerSchedulerConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WallblerSchedulerRepository extends JpaRepository<WallblerSchedulerConfig, Integer> {

    @Query(value = "UPDATE wallbler_scheduler_config SET last_time_fetched = :date WHERE feed_name = :feedName", nativeQuery = true)
    void updateLastTimeFetched(@Param("date") Date date, @Param("feedName") String feedName);

    @Query(value = "select *, CASE\n" +
            "           WHEN TIMEDIFF(SUBDATE(NOW(), INTERVAL period MINUTE), last_time_fetched) > 0\n" +
            "               THEN true\n" +
            "           ELSE false\n" +
            "           END AS to_update\n" +
            "from wallbler_scheduler_config", nativeQuery = true)
    List<?> toCheckToUpdate();

}
