package com.nkoad.wallbler.facebook.service.account;

import com.nkoad.wallbler.facebook.mapper.FacebookAccountMapper;
import com.nkoad.wallbler.facebook.model.account.FacebookAccount;
import com.nkoad.wallbler.facebook.model.account.FacebookAccountDto;
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
    private final FacebookAccountMapper facebookAccountMapper;


    public List<FacebookAccountDto> getAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(facebookAccountMapper::accountToAccountDto)
                .collect(Collectors.toList());
    }

    public FacebookAccountDto getAccountById(String name) {
        FacebookAccount facebookAccount = accountRepository.getOne(name);
        return facebookAccountMapper.accountToAccountDto(facebookAccount);
    }

    public FacebookAccount saveAccount(FacebookAccountDto facebookAccountDto) {
        if (accountAlreadyExists(facebookAccountDto)) {
            throw new IllegalArgumentException("The account already exists: " + facebookAccountDto.getAccountName());
        }
        return saveOrUpdateAccount(facebookAccountDto);
    }

    public void delAccountById(String name) {
        FacebookAccount facebookAccount = accountRepository.getOne(name);
        accountRepository.delete(facebookAccount);
    }

    public FacebookAccount editAccountById(FacebookAccountDto facebookAccountDto) {
        if (accountAlreadyExists(facebookAccountDto)) {
            return saveOrUpdateAccount(facebookAccountDto);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    private FacebookAccount saveOrUpdateAccount(FacebookAccountDto facebookAccountDto) {
        FacebookAccount facebookAccount = facebookAccountMapper.accountDtoToAccount(facebookAccountDto);
        return accountRepository.save(facebookAccount);
    }

    private boolean accountAlreadyExists(FacebookAccountDto facebookAccountDto) {
        return accountRepository.existsById(facebookAccountDto.getAccountName());
    }

}
