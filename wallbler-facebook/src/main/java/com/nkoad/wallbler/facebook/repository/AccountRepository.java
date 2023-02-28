package com.nkoad.wallbler.facebook.repository;

import com.nkoad.wallbler.facebook.model.FacebookAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<FacebookAccount, String> {

    @Query(value = "SELECT * FROM facebook_account " +
            "WHERE account_name = :accountName", nativeQuery = true)
    Optional<FacebookAccount> findByAccountName(@Param("accountName") String accountName);

    @Query(value = "UPDATE facebook_account " +
            "SET group_id = :groupId, access_token = :accessToken, facebook_type = :type " +
            "WHERE account_name = :accountName", nativeQuery = true)
    void updateAccount(@Param("accountName") String accountName, @Param("groupId") String groupId, @Param("accessToken") String accessToken, @Param("type") String type);

}
