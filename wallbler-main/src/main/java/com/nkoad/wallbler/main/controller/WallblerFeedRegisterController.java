package com.nkoad.wallbler.main.controller;

import com.nkoad.wallbler.main.model.WallblerFeedRegister;
import com.nkoad.wallbler.main.service.WallblerSchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallbler/register")
public class WallblerFeedRegisterController {

    private final WallblerSchedulerService service;


    @PostMapping
    public void registerWallbler(@RequestBody WallblerFeedRegister feedRegister) {
        service.registerWallbler(feedRegister);
    }

    @DeleteMapping
    public void unRegisterWallbler(@RequestBody WallblerFeedRegister feedRegister) {
        service.unRegisterWallbler(feedRegister);
    }

}
