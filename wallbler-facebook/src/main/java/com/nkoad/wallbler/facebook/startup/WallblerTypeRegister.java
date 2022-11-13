package com.nkoad.wallbler.facebook.startup;

import com.nkoad.wallbler.facebook.client.WallblerFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class WallblerTypeRegister {

//    @Value("${wallbler.type}")
//    String wallblerType;

    private final WallblerFeignClient feignClient;

    @PostConstruct
    public void registerWallblerType() {
        feignClient.registerWallblerType("FACEBOOK");
    }

}
