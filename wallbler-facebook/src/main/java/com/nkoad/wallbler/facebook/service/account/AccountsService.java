package com.nkoad.wallbler.facebook.service.account;

import com.nkoad.wallbler.facebook.mapper.FacebookAccountConfigMapper;
import com.nkoad.wallbler.facebook.model.account.FacebookAccountConfig;
import com.nkoad.wallbler.facebook.model.account.FacebookAccountConfigDto;
import com.nkoad.wallbler.facebook.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountsService {

    private final AccountRepository accountRepository;
    private final FacebookAccountConfigMapper accountConfigMapper;


    public List<FacebookAccountConfigDto> getAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(accountConfigMapper::accountConfigToAccountConfigDto)
                .collect(Collectors.toList());
    }

    public FacebookAccountConfigDto getAccountByName(String name) {
        FacebookAccountConfig accountConfig = accountRepository.getOne(name);
        return accountConfigMapper.accountConfigToAccountConfigDto(accountConfig);
    }

    public FacebookAccountConfig saveAccount(FacebookAccountConfigDto accountConfigDto) {
        if (accountAlreadyExists(accountConfigDto)) {
            throw new IllegalArgumentException("The account already exists: " + accountConfigDto.getAccountName());
        }
        return saveOrUpdateAccount(accountConfigDto);
    }

    public void delAccountByName(String name) {
        FacebookAccountConfig accountConfig = accountRepository.getOne(name);
        accountRepository.delete(accountConfig);
    }

    public FacebookAccountConfig editAccountByName(FacebookAccountConfigDto accountConfigDto) {
        if (accountAlreadyExists(accountConfigDto)) {
            return saveOrUpdateAccount(accountConfigDto);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    private FacebookAccountConfig saveOrUpdateAccount(FacebookAccountConfigDto accountConfigDto) {
        FacebookAccountConfig accountConfig = accountConfigMapper.accountConfigDtoToAccountConfig(accountConfigDto);
        return accountRepository.save(accountConfig);
    }

    private boolean accountAlreadyExists(FacebookAccountConfigDto accountConfigDto) {
        return accountRepository.existsById(accountConfigDto.getAccountName());
    }
}
