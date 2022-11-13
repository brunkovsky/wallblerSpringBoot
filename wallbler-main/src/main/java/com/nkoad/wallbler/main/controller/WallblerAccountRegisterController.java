package com.nkoad.wallbler.main.controller;

import com.nkoad.wallbler.main.model.WallblerRegisterAccount;
import com.nkoad.wallbler.main.service.WallblerSchedulerAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallbler/register/account")
public class WallblerAccountRegisterController {

    private final WallblerSchedulerAccountService service;

    @PostMapping
    public void registerAccount(@RequestBody WallblerRegisterAccount registerAccount) {
        service.registerAccount(registerAccount);
    }

    @DeleteMapping
    public void unRegisterAccount(@RequestBody WallblerRegisterAccount registerAccount) {
        service.unRegisterAccount(registerAccount);
    }

}
