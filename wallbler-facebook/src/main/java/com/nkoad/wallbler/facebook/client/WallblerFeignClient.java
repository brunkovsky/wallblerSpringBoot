package com.nkoad.wallbler.facebook.client;

import com.nkoad.wallbler.facebook.model.RegisterAccount;
import com.nkoad.wallbler.facebook.model.Scheduler;
import com.nkoad.wallbler.facebook.model.WallblerItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "wallblerClient", url = "http://localhost:8080/api/wallbler")
public interface WallblerFeignClient {

    @GetMapping("/accesstoken/scheduler")
    List<Scheduler> getAllAccessTokenSchedulers();

    @GetMapping("/accesstoken/scheduler/{schedulerName}")
    Scheduler getAccessTokenSchedulerByName(@PathVariable("schedulerName") String schedulerName);

    @GetMapping("/executor/scheduler/{schedulerName}")
    Scheduler getExecutorSchedulerByName(@PathVariable("schedulerName") String schedulerName);

    @GetMapping("/executor/scheduler")
    List<Scheduler> getAllExecutorSchedulers();

    @PostMapping(value = "/type/register/{wallblerType}")
    void registerWallblerType(@PathVariable("wallblerType") String wallblerType);

    @PostMapping(value = "/accesstoken/register")
    void registerAccount(RegisterAccount registerAccount);

    @DeleteMapping(value = "/accesstoken/register")
    void unRegisterAccount(RegisterAccount registerAccount);

    @PostMapping(value = "/executor/register")
    void registerExecutor(RegisterAccount registerAccount);

    @DeleteMapping(value = "/executor/register")
    void unRegisterExecutor(RegisterAccount registerAccount);

    @PostMapping(value = "/write")
    void writeWallblerItems(List<WallblerItem> wallblerItems);

}
