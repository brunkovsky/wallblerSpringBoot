package com.nkoad.wallbler.main.controller;

import com.nkoad.wallbler.main.model.WallblerRegisterFeed;
import com.nkoad.wallbler.main.service.WallblerSchedulerFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallbler/register/feed")
public class WallblerFeedRegisterController {

    private final WallblerSchedulerFeedService service;


    @PostMapping
    public void registerFeed(@RequestBody WallblerRegisterFeed registerFeed) {
        service.registerFeed(registerFeed);
    }

    @DeleteMapping
    public void unRegisterFeed(@RequestBody WallblerRegisterFeed registerFeed) {
        service.unRegisterFeed(registerFeed);
    }

}
