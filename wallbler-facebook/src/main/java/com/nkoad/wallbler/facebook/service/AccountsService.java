package com.nkoad.wallbler.facebook.service;

import com.nkoad.wallbler.facebook.client.WallblerFeignClient;
import com.nkoad.wallbler.facebook.mapper.FacebookAccountMapper;
import com.nkoad.wallbler.facebook.model.RegisterAccount;
import com.nkoad.wallbler.facebook.model.account.AccountScheduler;
import com.nkoad.wallbler.facebook.model.account.FacebookAccount;
import com.nkoad.wallbler.facebook.model.account.FacebookAccountDto;
import com.nkoad.wallbler.facebook.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountsService {

    private final AccountRepository accountRepository;
    private final FacebookAccountMapper facebookAccountMapper;
    private final WallblerFeignClient feignClient;

    public List<FacebookAccountDto> getAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(facebookAccountMapper::accountToAccountDto)
                .collect(Collectors.toList());
    }

    public FacebookAccountDto getAccountById(String name) {
        try {
            FacebookAccount facebookAccount = accountRepository.getOne(name);
            return facebookAccountMapper.accountToAccountDto(facebookAccount);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public FacebookAccount saveAccount(FacebookAccountDto facebookAccountDto) {
        if (accountAlreadyExists(facebookAccountDto)) {
            throw new IllegalArgumentException("The account provided already exists: "
                    + facebookAccountDto.getAccountName());
        }
        if (!isSchedulerExists(facebookAccountDto.getSchedulerName())) {
            log.error("Can't create the account because the scheduler provided does not exist. " +
                            "Scheduler: {} Available schedulers: {} ",
                    facebookAccountDto.getSchedulerName(), getSchedulerNames());
            throw new IllegalArgumentException("The scheduler provided does not exist: "
                    + facebookAccountDto.getSchedulerName());
        }
        registerAccount(facebookAccountDto);
        return saveOrUpdateAccount(facebookAccountDto);
    }

    public FacebookAccount editAccountById(FacebookAccountDto facebookAccountDto) {
        if (accountAlreadyExists(facebookAccountDto)) {
            return saveOrUpdateAccount(facebookAccountDto);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void delAccountById(String name) {
        FacebookAccount facebookAccount = accountRepository.getOne(name);
        unRegisterAccount(facebookAccount);
        accountRepository.delete(facebookAccount);
    }

    private FacebookAccount saveOrUpdateAccount(FacebookAccountDto facebookAccountDto) {
        FacebookAccount facebookAccount = facebookAccountMapper.accountDtoToAccount(facebookAccountDto);
        return accountRepository.save(facebookAccount);
    }

    private boolean accountAlreadyExists(FacebookAccountDto facebookAccountDto) {
        return accountRepository.existsById(facebookAccountDto.getAccountName());
    }

    private boolean isSchedulerExists(String schedulerName) {
        try {
            feignClient.getSchedulerByName(schedulerName);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private List<String> getSchedulerNames() {
        return feignClient.getAllSchedulers().stream()
                .map(AccountScheduler::getSchedulerName)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private void registerAccount(FacebookAccountDto facebookAccountDto) {
        feignClient.registerAccount(new RegisterAccount(
                "FACEBOOK" + "::" + facebookAccountDto.getAccountName(),
                facebookAccountDto.getSchedulerName()));
    }

    private void unRegisterAccount(FacebookAccount facebookAccount) {
        feignClient.unRegisterAccount(new RegisterAccount(
                "FACEBOOK" + "::" + facebookAccount.getAccountName()));
    }

}
