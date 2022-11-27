package com.nkoad.wallbler.main.controller;

import com.nkoad.wallbler.main.model.WallblerRegister;
import com.nkoad.wallbler.main.service.WallblerExecutorSchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallbler/executor/register")
public class WallblerExecutorRegisterController {

    private final WallblerExecutorSchedulerService service;

    @PostMapping
    public void registerExecutor(@RequestBody WallblerRegister wallblerRegister) {
        service.registerExecutor(wallblerRegister);
    }

    @DeleteMapping
    public void unRegisterExecutor(@RequestBody WallblerRegister wallblerRegister) {
        service.unRegisterExecutor(wallblerRegister);
    }

}
