package com.nkoad.wallbler.facebook.client;

import com.nkoad.wallbler.facebook.connector.FeedType;
import com.nkoad.wallbler.facebook.model.account.FacebookAccountConfig;
import net.minidev.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "facebookClient", url = "https://graph.facebook.com/v14.0")
public interface FacebookClient {

    @GetMapping(value = "/{groupId}")
    JSONObject getScreenName(@PathVariable("groupId") String groupId,
                             @RequestParam("access_token") String accessToken);

    @GetMapping(value = "/{groupId}/{endpoint}")
    JSONObject loadData(@PathVariable("groupId") String groupId,
                        @PathVariable("endpoint") String endpoint,
                        @RequestParam("access_token") String accessToken,
                        @RequestParam("fields") String fields,
                        @RequestParam("type") String type);

    default JSONObject loadData(FacebookAccountConfig accountConfig, FeedType feedType) {
        return loadData(
                accountConfig.getGroupId(),
                feedType.getEndPoint(),
                accountConfig.getAccessToken(),
                feedType.getFields(),
                feedType.getType());
    }

}
