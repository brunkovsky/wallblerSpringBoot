package com.nkoad.wallbler.facebook.controller;

import com.nkoad.wallbler.facebook.model.account.FacebookAccount;
import com.nkoad.wallbler.facebook.model.account.FacebookAccountDto;
import com.nkoad.wallbler.facebook.model.feed.FacebookFeed;
import com.nkoad.wallbler.facebook.model.feed.FacebookFeedDto;
import com.nkoad.wallbler.facebook.service.feed.FeedsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "api/wallbler/facebook/feed")
public class FeedsController {

    private final FeedsService service;

    @GetMapping
    public List<FacebookFeedDto> getFeeds() {
        return service.getFeeds();
    }

    @GetMapping(path = "/{name}")
    public FacebookFeedDto getFeedById(@PathVariable String name) {
        return service.getFeedById(name);
    }

    @PostMapping
    public void setFeed(@RequestBody FacebookFeedDto facebookFeedDto) {
        service.saveFeed(facebookFeedDto);
    }

    @PutMapping
    public FacebookFeed editAccountById(@RequestBody FacebookFeedDto facebookFeedDto) {
        return service.editFeedById(facebookFeedDto);
    }

    @DeleteMapping(path = "/{name}")
    public void delFeedById(@PathVariable String name) {
        service.delFeedById(name);
    }
}
