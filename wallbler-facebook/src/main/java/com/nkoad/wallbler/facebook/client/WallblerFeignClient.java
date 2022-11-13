package com.nkoad.wallbler.facebook.client;

import com.nkoad.wallbler.facebook.model.RegisterAccount;
import com.nkoad.wallbler.facebook.model.RegisterFeed;
import com.nkoad.wallbler.facebook.model.WallblerItem;
import com.nkoad.wallbler.facebook.model.account.AccountScheduler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "wallblerClient", url = "http://localhost:8080/api/wallbler")
public interface WallblerFeignClient {

    @GetMapping("/account/scheduler")
    List<AccountScheduler> getAllSchedulers();

    @GetMapping("/account/scheduler/{schedulerName}")
    AccountScheduler getSchedulerByName(@PathVariable("schedulerName") String wallblerType);

    @PostMapping(value = "/register/type/{wallblerType}")
    void registerWallblerType(@PathVariable("wallblerType") String wallblerType);

    @PostMapping(value = "/register/account")
    void registerAccount(RegisterAccount registerAccount);

    @DeleteMapping(value = "/register/account")
    void unRegisterAccount(RegisterAccount registerAccount);

    @PostMapping(value = "/register/feed")
    void registerFeed(RegisterFeed registerFeed);

    @DeleteMapping(value = "/register/feed")
    void unRegisterFeed(RegisterFeed registerFeed);

    @PostMapping(value = "/write")
    void writeWallblerItems(List<WallblerItem> wallblerItems);

}
