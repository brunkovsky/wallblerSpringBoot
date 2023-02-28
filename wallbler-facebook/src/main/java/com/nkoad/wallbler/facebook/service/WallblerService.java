package com.nkoad.wallbler.facebook.service;

import com.nkoad.wallbler.facebook.connector.Connector;
import com.nkoad.wallbler.facebook.connector.FacebookType;
import com.nkoad.wallbler.facebook.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class WallblerService {

    private final AccountRepository accountRepository;
    private final Map<String, FacebookType> typeMap;
    private final Connector connector;

    public void fetch(String name) {
        accountRepository.findByAccountName(name).ifPresentOrElse(account -> {
            Optional.ofNullable(typeMap.get(account.getFacebookType()))
                    .ifPresentOrElse(feedType -> connector.loadData(account, feedType), () ->
                            log.error("Can't find feedType by key: '{}' from the map: {}",
                                    account.getAccessToken(), typeMap.keySet()));
        }, () -> log.error("Can't find feed by feedName: '{}' from feedRepository", name));
    }

    public void refreshAccessToken(String accountName) {
        log.info("refreshing access token : " + accountName);
    }
}
