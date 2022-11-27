package com.nkoad.wallbler.facebook.service;

import com.nkoad.wallbler.facebook.client.WallblerFeignClient;
import com.nkoad.wallbler.facebook.mapper.FacebookAccountMapper;
import com.nkoad.wallbler.facebook.model.FacebookAccount;
import com.nkoad.wallbler.facebook.model.FacebookAccountDto;
import com.nkoad.wallbler.facebook.model.RegisterAccount;
import com.nkoad.wallbler.facebook.model.Scheduler;
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
        if (accountExists(facebookAccountDto)) {
            throw new IllegalArgumentException("The account provided already exists: "
                    + facebookAccountDto.getAccountName());
        }
        if (facebookAccountDto.getAccessTokenScheduler() != null) {
            if (!accessTokenSchedulerExists(facebookAccountDto)) {
                log.error("Can't create the account because the scheduler provided does not exist. " +
                                "Scheduler: {} Available schedulers: {}",
                        facebookAccountDto.getAccessTokenScheduler(), getAccessTokenSchedulerNames());
                throw new IllegalArgumentException("The scheduler provided does not exist: "
                        + facebookAccountDto.getAccessTokenScheduler());
            }
            registerAccessToken(facebookAccountDto);
        }
        if (!executorSchedulerExists(facebookAccountDto)) {
            log.error("Can't create the account because the scheduler provided does not exist. " +
                            "Scheduler: {} Available schedulers: {}",
                    facebookAccountDto.getExecutorScheduler(), getExecutorSchedulerNames());
            throw new IllegalArgumentException("The scheduler provided does not exist: "
                    + facebookAccountDto.getExecutorScheduler());
        }
        registerExecutor(facebookAccountDto);
        return accountRepository.save(facebookAccountMapper.accountDtoToAccount(facebookAccountDto));
    }

    public void editAccountById(String accountName, FacebookAccountDto facebookAccountDto) {
        if (accountExists(accountName)) {
            updateAccount(accountName, facebookAccountDto);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void delAccountById(String name) {
        FacebookAccount facebookAccount = accountRepository.getOne(name);
        unRegisterAccount(facebookAccount);
        unRegisterExecutor(facebookAccount);
        accountRepository.delete(facebookAccount);
    }

    private void updateAccount(String accountName, FacebookAccountDto facebookAccountDto) {
        FacebookAccount facebookAccount = facebookAccountMapper.accountDtoToAccount(facebookAccountDto);
        accountRepository.updateAccount(accountName, facebookAccount.getGroupId(),
                facebookAccount.getAccessToken(), facebookAccount.getFacebookType());
    }

    private boolean accountExists(FacebookAccountDto facebookAccountDto) {
        return accountExists(facebookAccountDto.getAccountName());
    }

    private boolean accountExists(String accountName) {
        return accountRepository.existsById(accountName);
    }

    private boolean executorSchedulerExists(FacebookAccountDto facebookAccountDto) {
        return executorSchedulerExists(facebookAccountDto.getExecutorScheduler());
    }

    private boolean executorSchedulerExists(String schedulerName) {
        try {
            feignClient.getExecutorSchedulerByName(schedulerName);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean accessTokenSchedulerExists(FacebookAccountDto facebookAccountDto) {
        return accessTokenSchedulerExists(facebookAccountDto.getAccessTokenScheduler());
    }

    private boolean accessTokenSchedulerExists(String schedulerName) {
        try {
            feignClient.getAccessTokenSchedulerByName(schedulerName);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private List<String> getExecutorSchedulerNames() {
        return feignClient.getAllExecutorSchedulers().stream()
                .map(Scheduler::getSchedulerName)
                .collect(Collectors.toList());
    }

    private List<String> getAccessTokenSchedulerNames() {
        return feignClient.getAllAccessTokenSchedulers().stream()
                .map(Scheduler::getSchedulerName)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private void registerAccessToken(FacebookAccountDto facebookAccountDto) {
        feignClient.registerAccount(new RegisterAccount(
                "FACEBOOK" + "::" + facebookAccountDto.getAccountName(),
                facebookAccountDto.getAccessTokenScheduler()));
    }

    private void unRegisterAccount(FacebookAccount facebookAccount) {
        feignClient.unRegisterAccount(new RegisterAccount(
                "FACEBOOK" + "::" + facebookAccount.getAccountName()));
    }

    @SneakyThrows
    private void registerExecutor(FacebookAccountDto facebookAccountDto) {
        feignClient.registerExecutor(new RegisterAccount(
                "FACEBOOK" + "::" + facebookAccountDto.getAccountName(),
                facebookAccountDto.getExecutorScheduler()));
    }

    private void unRegisterExecutor(FacebookAccount facebookAccount) {
        feignClient.unRegisterExecutor(new RegisterAccount(
                "FACEBOOK" + "::" + facebookAccount.getAccountName()));
    }

}
