package com.nkoad.wallbler.facebook.service;

import com.nkoad.wallbler.facebook.connector.Connector;
import com.nkoad.wallbler.facebook.connector.FeedType;
import com.nkoad.wallbler.facebook.repository.AccountRepository;
import com.nkoad.wallbler.facebook.repository.FeedRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class WallblerService {

    private final FeedRepository feedRepository;
    private final AccountRepository accountRepository;
    private final Map<String, FeedType> feedMap;
    private final Connector connector;


    public void fetch(String feedName) {
        feedRepository.findByFeedName(feedName).ifPresentOrElse(facebookFeed -> {
            accountRepository.findByAccountName(facebookFeed.getAccountName()).ifPresentOrElse(facebookAccount -> {
                Optional.ofNullable(feedMap.get(facebookFeed.getFeedType().name())).ifPresentOrElse(feedType -> {
                    connector.loadData(facebookAccount, feedType, feedName);
                }, () -> log.error("Can't find feedType by key: '{}' from the map: {}", facebookFeed.getFeedType().name(), feedMap));
            }, () -> log.error("Can't find account by accountName: '{}' from accountRepository", facebookFeed.getAccountName()));
        }, () -> log.error("Can't find feed by feedName: '{}' from feedRepository", feedName));
    }
}
