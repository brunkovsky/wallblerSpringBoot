package com.nkoad.wallbler.facebook.controller;

import com.nkoad.wallbler.facebook.model.account.FacebookAccountConfig;
import com.nkoad.wallbler.facebook.model.account.FacebookAccountConfigDto;
import com.nkoad.wallbler.facebook.service.account.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/wallbler/facebook/account")
public class AccountsController {

    private final AccountsService service;

    @GetMapping
    public List<FacebookAccountConfigDto> getAccounts() {
        return service.getAccounts();
    }

    @GetMapping(path = "/{name}")
    public FacebookAccountConfigDto getAccountById(@PathVariable String name) {
        return service.getAccountByName(name);
    }

    @PostMapping
    public FacebookAccountConfig saveAccount(@RequestBody FacebookAccountConfigDto accountConfigDto) {
        return service.saveAccount(accountConfigDto);
    }

    @PutMapping
    public FacebookAccountConfig editAccountById(@RequestBody FacebookAccountConfigDto accountConfigDto) {
        return service.editAccountByName(accountConfigDto);
    }

    @DeleteMapping(path = "/{name}")
    public void delAccountById(@PathVariable String name) {
        service.delAccountByName(name);
    }

}
