package com.nkoad.wallbler.facebook.controller;

import com.nkoad.wallbler.facebook.model.account.FacebookAccountConfigDto;
import com.nkoad.wallbler.facebook.service.account.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/wallbler/facebook/accounts")
public class AccountsController {

    private final AccountsService service;

    @GetMapping
    public List<FacebookAccountConfigDto> getAccounts() {
        return service.getAccounts();
    }

    @PostMapping
    public void setAccount(@RequestBody FacebookAccountConfigDto accountConfigDto) {
        service.saveAccount(accountConfigDto);
    }

}
