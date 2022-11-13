package com.nkoad.wallbler.main.service;

import com.nkoad.wallbler.main.mapper.WallblerSchedulerAccountConfigMapper;
import com.nkoad.wallbler.main.model.WallblerAccountScheduler;
import com.nkoad.wallbler.main.model.WallblerRegisterAccount;
import com.nkoad.wallbler.main.model.WallblerSchedulerDto;
import com.nkoad.wallbler.main.repository.WallblerSchedulerAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class WallblerSchedulerAccountService {

    private final WallblerSchedulerAccountRepository accountRepository;
    private final WallblerSchedulerAccountConfigMapper accountConfigMapper;

    public List<WallblerAccountScheduler> getSchedulers() {
        return accountRepository.findAll();
    }

    public List<WallblerAccountScheduler> getSchedulersByType(String type) {
        return accountRepository.findAllByType(type);
    }

    public WallblerAccountScheduler getSchedulerById(String name) {
        return accountRepository
                .findById(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public WallblerAccountScheduler saveScheduler(WallblerSchedulerDto schedulerDto) {
        if (schedulerAlreadyExists(schedulerDto.getSchedulerName())) {
            throw new IllegalArgumentException("The scheduler provided already exists: "
                    + schedulerDto.getSchedulerName());
        }
        return saveOrUpdateScheduler(schedulerDto);
    }

    public void editSchedulerById(String schedulerName, WallblerSchedulerDto schedulerDto) {
        if (schedulerAlreadyExists(schedulerName)) {
            accountRepository.updateScheduler(schedulerDto.isEnabled(), schedulerDto.getPeriod(), schedulerName);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void delSchedulerById(String name) { // TODO : to check is the 'account_names' empty before. delete if yes only
        accountRepository.delete(accountRepository.getOne(name));
    }

    public void registerAccount(WallblerRegisterAccount registerAccount) {
        accountRepository.registerWallblerAccount(registerAccount.getAccountName(),
                registerAccount.getSchedulerName());
    }

    public void unRegisterAccount(WallblerRegisterAccount registerAccount) {
        List<String> schedulerNames = accountRepository
                .findSchedulersByAccountName(registerAccount.getAccountName());
        for (String schedulerName : schedulerNames) {
            accountRepository.unRegisterWallblerAccount(registerAccount.getAccountName(),
                    schedulerName);
        }
    }

    private WallblerAccountScheduler saveOrUpdateScheduler(WallblerSchedulerDto schedulerDto) {
        WallblerAccountScheduler scheduler = accountConfigMapper
                .schedulerDtoToScheduler(schedulerDto);
        return accountRepository.save(scheduler);
    }

    private boolean schedulerAlreadyExists(String schedulerName) {
        return accountRepository.existsById(schedulerName);
    }

}
