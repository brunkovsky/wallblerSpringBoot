package com.nkoad.wallbler.facebook.repository;

import com.nkoad.wallbler.facebook.model.account.FacebookAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<FacebookAccount, String> {

//    @Query(value = "SELECT * FROM facebook_wallbler_account_config WHERE account_name = :accountName", nativeQuery = true)
//    FacebookAccountConfig findByAccountName(@Param("accountName") String accountName);

}
