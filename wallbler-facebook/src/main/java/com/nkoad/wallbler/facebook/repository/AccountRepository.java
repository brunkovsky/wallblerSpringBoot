package com.nkoad.wallbler.facebook.repository;

import com.nkoad.wallbler.facebook.model.account.FacebookAccountConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<FacebookAccountConfig, Integer> {

    @Query(value = "SELECT * FROM facebook_wallbler_account_config WHERE account_name = :accountName", nativeQuery = true)
    FacebookAccountConfig findByAccountName(@Param("accountName") String accountName);

}
