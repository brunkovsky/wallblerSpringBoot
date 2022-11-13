package com.nkoad.wallbler.facebook.connector;

import com.nkoad.wallbler.facebook.client.FacebookClient;
import com.nkoad.wallbler.facebook.client.WallblerFeignClient;
import com.nkoad.wallbler.facebook.model.WallblerItem;
import com.nkoad.wallbler.facebook.model.account.FacebookAccount;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class Connector {

    private final FacebookClient facebookClient;
    private final WallblerFeignClient wallblerFeignClient;

    @SneakyThrows
    public void loadData(FacebookAccount facebookAccount, FeedType feedType, String feedName) {
        JSONObject facebookItems = facebookClient.loadData(facebookAccount, feedType);
        List<WallblerItem> wallblerItems = feedType.parseResult(facebookItems, feedName, facebookAccount.isApprovedByDefault());
        log.debug("Got {} items from facebook", wallblerItems.size());
        wallblerFeignClient.writeWallblerItems(wallblerItems);
    }

}
