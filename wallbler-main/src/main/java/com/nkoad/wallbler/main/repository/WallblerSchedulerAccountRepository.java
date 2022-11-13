package com.nkoad.wallbler.main.repository;

import com.nkoad.wallbler.main.model.WallblerAccountScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WallblerSchedulerAccountRepository extends JpaRepository<WallblerAccountScheduler, String> {

    @Query(value = "UPDATE wallbler_account_scheduler " +
            "SET last_time_fetched = :lastTimeFetched " +
            "WHERE scheduler_name = :schedulerName", nativeQuery = true)
    void updateLastTimeFetched(@Param("lastTimeFetched") Date lastTimeFetched, @Param("schedulerName") String schedulerName);

    @Query(value = "UPDATE wallbler_account_scheduler " +
            "SET enabled = :enabled ," +
            "period = :period " +
            "WHERE scheduler_name = :schedulerName", nativeQuery = true)
    void updateScheduler(@Param("enabled") boolean enabled, @Param("period") int period, @Param("schedulerName") String schedulerName);

    @Query(value = "SELECT * FROM wallbler_account_scheduler " +
            "WHERE account_names LIKE CONCAT('%', :type, '%')", nativeQuery = true)
    List<WallblerAccountScheduler> findAllByType(@Param("type") String type);

    @Query(value = "UPDATE wallbler_account_scheduler " +
            "SET account_names = IF(account_names IS NULL, :accountName, CONCAT(account_names, IF(account_names = '', '', '|'), :accountName)) " +
            "WHERE scheduler_name = :schedulerName", nativeQuery = true)
    void registerWallblerAccount(@Param("accountName") String accountName, @Param("schedulerName") String schedulerName);

    @Query(value = "UPDATE wallbler_account_scheduler " +
            "SET account_names = REPLACE(account_names, IF(account_names = :accountName, :accountName, IF(account_names LIKE CONCAT(:accountName,'%'), CONCAT(:accountName,'|'), CONCAT('|',:accountName))), '') " +
            "WHERE scheduler_name = :schedulerName", nativeQuery = true)
    void unRegisterWallblerAccount(@Param("accountName") String accountName, @Param("schedulerName") String schedulerName);

    @Query(value = "SELECT scheduler_name FROM wallbler_account_scheduler " +
            "WHERE account_names LIKE CONCAT('%', :accountName, '%')", nativeQuery = true)
    List<String> findSchedulersByAccountName(@Param("accountName") String accountName);

}
