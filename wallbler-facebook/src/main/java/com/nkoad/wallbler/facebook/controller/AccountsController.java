package com.nkoad.wallbler.facebook.controller;

import com.nkoad.wallbler.facebook.model.account.FacebookAccount;
import com.nkoad.wallbler.facebook.model.account.FacebookAccountDto;
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
    public List<FacebookAccountDto> getAccounts() {
        return service.getAccounts();
    }

    @GetMapping(path = "/{name}")
    public FacebookAccountDto getAccountById(@PathVariable String name) {
        return service.getAccountByName(name);
    }

    @PostMapping
    public FacebookAccount saveAccount(@RequestBody FacebookAccountDto facebookAccountDto) {
        return service.saveAccount(facebookAccountDto);
    }

    @PutMapping
    public FacebookAccount editAccountById(@RequestBody FacebookAccountDto facebookAccountDto) {
        return service.editAccountByName(facebookAccountDto);
    }

    @DeleteMapping(path = "/{name}")
    public void delAccountById(@PathVariable String name) {
        service.delAccountByName(name);
    }

}
