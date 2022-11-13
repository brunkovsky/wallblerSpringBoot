package com.nkoad.wallbler.main.controller;

import com.nkoad.wallbler.main.model.WallblerAccountScheduler;
import com.nkoad.wallbler.main.model.WallblerSchedulerDto;
import com.nkoad.wallbler.main.service.WallblerSchedulerAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallbler/account/scheduler")
public class WallblerAccountSchedulerController {

    private final WallblerSchedulerAccountService service;

    @GetMapping
    public List<WallblerAccountScheduler> getSchedulers() {
        return service.getSchedulers();
    }

    @GetMapping("/type/{type}")
    public List<WallblerAccountScheduler> getSchedulersByType(@PathVariable String type) {
        return service.getSchedulersByType(type);
    }

    @GetMapping(path = "/{schedulerName}")
    public WallblerAccountScheduler getSchedulerById(@PathVariable String schedulerName) {
        return service.getSchedulerById(schedulerName);
    }

    @PostMapping
    public WallblerAccountScheduler saveScheduler(@RequestBody WallblerSchedulerDto schedulerDto) {
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
