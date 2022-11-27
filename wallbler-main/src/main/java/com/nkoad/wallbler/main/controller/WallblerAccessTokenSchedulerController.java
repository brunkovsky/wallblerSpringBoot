package com.nkoad.wallbler.main.controller;

import com.nkoad.wallbler.main.model.WallblerAccessTokenScheduler;
import com.nkoad.wallbler.main.model.WallblerSchedulerDto;
import com.nkoad.wallbler.main.service.WallblerAccessTokenSchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallbler/accesstoken/scheduler")
public class WallblerAccessTokenSchedulerController {

    private final WallblerAccessTokenSchedulerService service;

    @GetMapping
    public List<WallblerAccessTokenScheduler> getSchedulers() {
        return service.getSchedulers();
    }

    @GetMapping("/type/{type}")
    public List<WallblerAccessTokenScheduler> getSchedulersByType(@PathVariable String type) {
        return service.getSchedulersByType(type);
    }

    @GetMapping(path = "/{schedulerName}")
    public WallblerAccessTokenScheduler getSchedulerById(@PathVariable String schedulerName) {
        return service.getSchedulerById(schedulerName);
    }

    @PostMapping
    public WallblerAccessTokenScheduler saveScheduler(@RequestBody WallblerSchedulerDto schedulerDto) {
        return service.saveScheduler(schedulerDto);
    }

    @PutMapping(path = "/{schedulerName}")
    public void editSchedulerById(@PathVariable String schedulerName,
                                  @RequestBody WallblerSchedulerDto schedulerDto) {
        service.editSchedulerById(schedulerName, schedulerDto);
    }

    @DeleteMapping(path = "/{schedulerName}")
    public void delSchedulerById(@PathVariable String schedulerName) {
        service.delSchedulerById(schedulerName);
    }

}
