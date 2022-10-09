package com.nkoad.wallbler.facebook.service.feed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nkoad.wallbler.facebook.mapper.FacebookFeedMapper;
import com.nkoad.wallbler.facebook.model.WallblerSchedulerDto;
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
    private final RabbitTemplate registerTemplate;
    private final ObjectMapper objectMapper;


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

    public void delFeedById(String name) {
        FacebookFeed facebookFeed = feedRepository.getOne(name);
        feedRepository.delete(facebookFeed);
    }

    public FacebookFeed editFeedById(FacebookFeedDto facebookFeedDto) {
        if (feedExists(facebookFeedDto)) {
            return saveOrUpdateFeed(facebookFeedDto);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    private boolean accountExists(FacebookFeedDto facebookFeedDto) {
        return accountRepository.existsById(facebookFeedDto.getAccountName());
    }

    private boolean feedExists(FacebookFeedDto facebookFeedDto) {
        return feedRepository.existsById(facebookFeedDto.getFeedName());
    }

    private FacebookFeed saveOrUpdateFeed(FacebookFeedDto facebookFeedDto) {
        if (accountExists(facebookFeedDto)) {
            log.warn("The facebook account_name:'{}' from the feed provided not found",
                    facebookFeedDto.getAccountName() + "...hence set 'enable' to false");
            facebookFeedDto.getScheduler().setEnabled(false);
        }
        FacebookFeed facebookFeed = facebookFeedMapper.feedDtoToFeed(facebookFeedDto);
        return feedRepository.save(facebookFeed);
    }

    @SneakyThrows
    private void registerFeed(FacebookFeedDto facebookFeedDto) {
        WallblerSchedulerDto schedulerDto = facebookFeedMapper.feedDtoToScheduler(facebookFeedDto);
        registerTemplate.convertAndSend(objectMapper.writeValueAsString(schedulerDto));
    }

}
