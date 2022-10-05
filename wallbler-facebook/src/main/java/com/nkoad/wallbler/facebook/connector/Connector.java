package com.nkoad.wallbler.facebook.connector;

import com.nkoad.wallbler.facebook.client.FacebookClient;
import com.nkoad.wallbler.facebook.client.WallblerFeignClient;
import com.nkoad.wallbler.facebook.model.account.FacebookAccountConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class Connector {

    private final FacebookClient facebookClient;
    private final WallblerFeignClient wallblerFeignClient;

    public void loadData(FacebookAccountConfig accountConfig, FeedType feedType) {
        wallblerFeignClient.writeWallblerItems(
                feedType.parseResult(
                        facebookClient.loadData(accountConfig, feedType)));
    }

}
