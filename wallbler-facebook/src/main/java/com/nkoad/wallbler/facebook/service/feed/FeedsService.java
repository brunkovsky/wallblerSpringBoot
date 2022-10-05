package com.nkoad.wallbler.facebook.service.feed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nkoad.wallbler.facebook.mapper.FacebookFeedConfigMapper;
import com.nkoad.wallbler.facebook.model.WallblerSchedulerConfigDto;
import com.nkoad.wallbler.facebook.model.feed.FacebookFeedConfig;
import com.nkoad.wallbler.facebook.model.feed.FacebookFeedConfigDto;
import com.nkoad.wallbler.facebook.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedsService {

    private final FeedRepository feedRepository;
    private final FacebookFeedConfigMapper mapper;
    private final RabbitTemplate registerTemplate;
    private final ObjectMapper objectMapper;


    public List<FacebookFeedConfigDto> getFeeds() {
        return feedRepository.findAll()
                .stream()
                .map(mapper::feedConfigToFeedConfigDto)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public void saveFeed(FacebookFeedConfigDto feedConfigDto) {
        FacebookFeedConfig feedConfig = mapper.feedConfigDtoToFeedConfig(feedConfigDto);
        feedRepository.save(feedConfig);
        WallblerSchedulerConfigDto schedulerConfigDto = mapper.feedConfigDtoToSchedulerConfig(feedConfigDto);
        registerTemplate.convertAndSend(objectMapper.writeValueAsString(schedulerConfigDto));
    }

}
