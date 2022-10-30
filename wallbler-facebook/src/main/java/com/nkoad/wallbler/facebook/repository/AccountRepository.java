package com.nkoad.wallbler.facebook.repository;

import com.nkoad.wallbler.facebook.model.account.FacebookAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<FacebookAccount, String> {

    @Query(value = "SELECT * FROM facebook_account WHERE account_name = :accountName", nativeQuery = true)
    Optional<FacebookAccount> findByAccountName(@Param("accountName") String accountName);

}
