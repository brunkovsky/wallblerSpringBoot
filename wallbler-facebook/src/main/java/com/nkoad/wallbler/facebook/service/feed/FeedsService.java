package com.nkoad.wallbler.facebook.service.feed;

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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
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

    @SneakyThrows
    public void saveFeed(FacebookFeedDto facebookFeedDto) {
        ifAccountExistsDo(facebookFeedDto, x -> {
            log.warn("...Hence set 'enable' to false");
            x.getScheduler().setEnabled(false);
        });
        FacebookFeed facebookFeed = facebookFeedMapper.feedDtoToFeed(facebookFeedDto);
        feedRepository.save(facebookFeed);
        WallblerSchedulerDto schedulerDto = facebookFeedMapper.feedDtoToScheduler(facebookFeedDto);
        registerTemplate.convertAndSend(objectMapper.writeValueAsString(schedulerDto));
    }

    private void ifAccountExistsDo(FacebookFeedDto facebookFeedDto, Consumer<FacebookFeedDto> consumer) {
        String accountName = facebookFeedDto.getAccountName();
        if (!accountRepository.existsById(accountName)) {
            log.warn("The facebook account:'{}' from the feed provided not found", accountName);
            consumer.accept(facebookFeedDto);
        }
    }



}
