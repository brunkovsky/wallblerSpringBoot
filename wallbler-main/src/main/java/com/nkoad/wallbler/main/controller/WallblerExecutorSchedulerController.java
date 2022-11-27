package com.nkoad.wallbler.main.controller;

import com.nkoad.wallbler.main.model.WallblerExecutorScheduler;
import com.nkoad.wallbler.main.model.WallblerSchedulerDto;
import com.nkoad.wallbler.main.service.WallblerExecutorSchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallbler/executor/scheduler")
public class WallblerExecutorSchedulerController {

    private final WallblerExecutorSchedulerService service;

    @GetMapping
    public List<WallblerExecutorScheduler> getSchedulers() {
        return service.getSchedulers();
    }

    @GetMapping("/type/{type}")
    public List<WallblerExecutorScheduler> getSchedulersByType(@PathVariable String type) {
        return service.getSchedulersByType(type);
    }

    @GetMapping(path = "/{name}")
    public WallblerExecutorScheduler getSchedulerById(@PathVariable String name) {
        return service.getSchedulerById(name);
    }

    @PostMapping
    public WallblerExecutorScheduler saveScheduler(@RequestBody WallblerSchedulerDto schedulerDto) {
        return service.saveScheduler(schedulerDto);
    }

    @PutMapping(path = "/{schedulerName}")
    public void editSchedulerById(@PathVariable String schedulerName,
                                  @RequestBody WallblerSchedulerDto schedulerDto) {
        service.editSchedulerById(schedulerName, schedulerDto);
    }

    @DeleteMapping(path = "/{name}")
    public void delSchedulerById(@PathVariable String name) {
        service.delSchedulerById(name);
    }

}
