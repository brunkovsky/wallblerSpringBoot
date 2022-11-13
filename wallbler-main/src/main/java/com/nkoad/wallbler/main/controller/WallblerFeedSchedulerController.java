package com.nkoad.wallbler.main.controller;

import com.nkoad.wallbler.main.model.WallblerFeedScheduler;
import com.nkoad.wallbler.main.model.WallblerScheduler;
import com.nkoad.wallbler.main.model.WallblerSchedulerDto;
import com.nkoad.wallbler.main.service.WallblerSchedulerFeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallbler/feed/scheduler")
public class WallblerFeedSchedulerController {

    private final WallblerSchedulerFeedService service;

    @GetMapping
    public List<WallblerFeedScheduler> getSchedulers() {
        return service.getSchedulers();
    }

    @GetMapping("/type/{type}")
    public List<WallblerFeedScheduler> getSchedulersByType(@PathVariable String type) {
        return service.getSchedulersByType(type);
    }

    @GetMapping(path = "/{name}")
    public WallblerFeedScheduler getSchedulerById(@PathVariable String name) {
        return service.getSchedulerById(name);
    }

    @PostMapping
    public WallblerFeedScheduler saveScheduler(@RequestBody WallblerSchedulerDto schedulerDto) {
        return service.saveScheduler(schedulerDto);
    }

    @PutMapping
    public WallblerFeedScheduler editSchedulerById(@RequestBody WallblerSchedulerDto schedulerDto) {
        return service.editSchedulerById(schedulerDto);
    }

    @DeleteMapping(path = "/{name}")
    public void delSchedulerById(@PathVariable String name) {
        service.delSchedulerById(name);
    }

}
