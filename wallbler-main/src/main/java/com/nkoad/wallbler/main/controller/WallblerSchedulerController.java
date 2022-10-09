package com.nkoad.wallbler.main.controller;

import com.nkoad.wallbler.main.model.WallblerScheduler;
import com.nkoad.wallbler.main.model.WallblerSchedulerDto;
import com.nkoad.wallbler.main.service.WallblerSchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallbler/scheduler")
public class WallblerSchedulerController {

    private final WallblerSchedulerService service;

    @GetMapping
    public List<WallblerScheduler> getSchedulers() {
        return service.getSchedulers();
    }

    @GetMapping(path = "/{name}")
    public WallblerScheduler getSchedulerById(@PathVariable String name) {
        return service.getSchedulerById(name);
    }

    @PostMapping
    public WallblerScheduler saveScheduler(@RequestBody WallblerSchedulerDto schedulerDto) {
        return service.saveScheduler(schedulerDto);
    }

    @PutMapping
    public WallblerScheduler editSchedulerById(@RequestBody WallblerSchedulerDto schedulerDto) {
        return service.editSchedulerById(schedulerDto);
    }

    @DeleteMapping(path = "/{name}")
    public void delSchedulerById(@PathVariable String name) {
        service.delSchedulerById(name);
    }

}
