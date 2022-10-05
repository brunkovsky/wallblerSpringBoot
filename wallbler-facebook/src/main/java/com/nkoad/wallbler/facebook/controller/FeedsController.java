package com.nkoad.wallbler.facebook.controller;

import com.nkoad.wallbler.facebook.model.feed.FacebookFeedConfigDto;
import com.nkoad.wallbler.facebook.service.feed.FeedsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "api/wallbler/facebook/feeds")
public class FeedsController {

    private final FeedsService service;

    @GetMapping
    public List<FacebookFeedConfigDto> getFeeds() {
        return service.getFeeds();
    }

    @PostMapping
    public void setFeed(@RequestBody FacebookFeedConfigDto feedConfigDto) {
        service.saveFeed(feedConfigDto);
    }
}
