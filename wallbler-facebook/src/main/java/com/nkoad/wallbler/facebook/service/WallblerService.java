package com.nkoad.wallbler.facebook.service;

import com.nkoad.wallbler.facebook.connector.Connector;
import com.nkoad.wallbler.facebook.connector.FeedType;
import com.nkoad.wallbler.facebook.model.account.FacebookAccountConfig;
import com.nkoad.wallbler.facebook.model.feed.FacebookFeedConfig;
import com.nkoad.wallbler.facebook.repository.AccountRepository;
import com.nkoad.wallbler.facebook.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class WallblerService {

    private final FeedRepository feedRepository;
    private final AccountRepository accountRepository;
    private final Connector connector;
    private final Map<String, FeedType> feedMap;


    public void fetch(String feedName) {
        FacebookFeedConfig feedConfig = feedRepository.findByFeedName(feedName);
        FacebookAccountConfig accountConfig = accountRepository.findByAccountName(feedConfig.getAccountName());
        FeedType feedType = feedMap.get(feedConfig.getFeedType().name());
        connector.loadData(accountConfig, feedType);
    }

}
