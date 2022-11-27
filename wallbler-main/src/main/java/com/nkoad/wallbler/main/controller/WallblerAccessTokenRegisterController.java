package com.nkoad.wallbler.main.controller;

import com.nkoad.wallbler.main.model.WallblerRegister;
import com.nkoad.wallbler.main.service.WallblerAccessTokenSchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallbler/accesstoken/register")
public class WallblerAccessTokenRegisterController {

    private final WallblerAccessTokenSchedulerService service;

    @PostMapping
    public void registerAccount(@RequestBody WallblerRegister registerAccount) {
        service.registerAccessToken(registerAccount);
    }

    @DeleteMapping
    public void unRegisterAccount(@RequestBody WallblerRegister registerAccount) {
        service.unRegisterAccessToken(registerAccount);
    }

}
