package com.nkoad.wallbler.facebook.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nkoad.wallbler.facebook.client.WallblerFeignClient;
import com.nkoad.wallbler.facebook.mapper.FacebookFeedMapper;
import com.nkoad.wallbler.facebook.model.WallblerFeedRegister;
import com.nkoad.wallbler.facebook.model.feed.FacebookFeed;
import com.nkoad.wallbler.facebook.model.feed.FacebookFeedDto;
import com.nkoad.wallbler.facebook.repository.AccountRepository;
import com.nkoad.wallbler.facebook.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedsService {

    private final FeedRepository feedRepository;
    private final AccountRepository accountRepository;
    private final FacebookFeedMapper facebookFeedMapper;
    private final WallblerFeignClient feignClient;


    public List<FacebookFeedDto> getFeeds() {
        return feedRepository.findAll()
                .stream()
                .map(facebookFeedMapper::feedToFeedDto)
                .collect(Collectors.toList());
    }

    public FacebookFeedDto getFeedById(String name) {
        FacebookFeed facebookFeed = feedRepository.getOne(name);
        return facebookFeedMapper.feedToFeedDto(facebookFeed);
    }

    public void saveFeed(FacebookFeedDto facebookFeedDto) {
        if (feedExists(facebookFeedDto)) {
            throw new IllegalArgumentException("The feed already exists: " + facebookFeedDto.getFeedName());
        }
        saveOrUpdateFeed(facebookFeedDto);
        registerFeed(facebookFeedDto);
    }

    public void delFeedById(String feedName) {
        FacebookFeed facebookFeed = feedRepository.getOne(feedName);
        feedRepository.delete(facebookFeed);
        unRegisterFeed(facebookFeed);
    }

    public FacebookFeed editFeedById(FacebookFeedDto facebookFeedDto) {
        if (feedExists(facebookFeedDto)) {
            return saveOrUpdateFeed(facebookFeedDto);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    private FacebookFeed saveOrUpdateFeed(FacebookFeedDto facebookFeedDto) {
        if (accountNotExists(facebookFeedDto)) {
            log.warn("The facebook account_name:'{}' from the feed provided not found",
                    facebookFeedDto.getAccountName());
        }
        FacebookFeed facebookFeed = facebookFeedMapper.feedDtoToFeed(facebookFeedDto);
        return feedRepository.save(facebookFeed);
    }

    private boolean feedExists(FacebookFeedDto facebookFeedDto) {
        return feedRepository.existsById(facebookFeedDto.getFeedName());
    }

    private boolean accountNotExists(FacebookFeedDto facebookFeedDto) {
        return !accountExists(facebookFeedDto);
    }

    private boolean accountExists(FacebookFeedDto facebookFeedDto) {
        return accountRepository.existsById(facebookFeedDto.getAccountName());
    }

    @SneakyThrows
    private void registerFeed(FacebookFeedDto facebookFeedDto) {
        WallblerFeedRegister feedRegister = new WallblerFeedRegister("FACEBOOK",
                facebookFeedDto.getFeedName(), facebookFeedDto.getSchedulerName());
        feignClient.registerWallbler(feedRegister);
    }

    private void unRegisterFeed(FacebookFeed facebookFeed) {
        WallblerFeedRegister feedRegister = new WallblerFeedRegister("FACEBOOK",
                facebookFeed.getFeedName(), null);
        feignClient.unRegisterWallbler(feedRegister);
    }
}
