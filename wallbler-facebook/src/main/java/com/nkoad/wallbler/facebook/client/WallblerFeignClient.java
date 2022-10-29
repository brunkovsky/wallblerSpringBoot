package com.nkoad.wallbler.facebook.client;

import com.nkoad.wallbler.facebook.model.WallblerFeedRegister;
import com.nkoad.wallbler.facebook.model.WallblerItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "wallblerClient", url = "http://localhost:8080/api/wallbler")
public interface WallblerFeignClient {

    @PostMapping(value = "/register")
    void registerWallbler(WallblerFeedRegister feedRegister);

    @DeleteMapping(value = "/register")
    void unRegisterWallbler(WallblerFeedRegister feedRegister);

    @PostMapping(value = "/write")
    void writeWallblerItems(List<WallblerItem> wallblerItems);

}
