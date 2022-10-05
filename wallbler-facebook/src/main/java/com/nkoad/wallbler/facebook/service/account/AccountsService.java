package com.nkoad.wallbler.facebook.service.account;

import com.nkoad.wallbler.facebook.mapper.FacebookAccountConfigMapper;
import com.nkoad.wallbler.facebook.model.account.FacebookAccountConfig;
import com.nkoad.wallbler.facebook.model.account.FacebookAccountConfigDto;
import com.nkoad.wallbler.facebook.repository.AccountRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
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

    public void saveAccount(FacebookAccountConfigDto accountConfigDto) {
        FacebookAccountConfig accountConfig = accountConfigMapper.accountConfigDtoToAccountConfig(accountConfigDto);
        accountRepository.save(accountConfig);
    }

}
