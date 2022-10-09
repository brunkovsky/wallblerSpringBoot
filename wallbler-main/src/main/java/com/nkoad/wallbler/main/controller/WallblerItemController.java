package com.nkoad.wallbler.main.controller;

import com.nkoad.wallbler.main.model.WallblerItem;
import com.nkoad.wallbler.main.service.WallblerItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallbler")
public class WallblerItemController {

    private final WallblerItemService service;
    private final RabbitTemplate commonTemplate;

//    @GetMapping
//    public List<WallblerSchedulerConfig> getSchedulers() {
//        return wallblerService.getSchedulers();
//    }

//    @PostMapping("/register")
//    public void registerWallbler(@RequestBody WallblerSchedulerConfigDTO schedulerConfigDTO) {
//        wallblerService.registerWallbler(schedulerConfigDTO);
//    }

    @PostMapping("/write")
    public void writeWallbler(@RequestBody List<WallblerItem> wallblerItems) {
        service.writeWallblerItems(wallblerItems);
    }

//    @PostMapping("/temp")
//    public void temp(@RequestBody Map<String, String> map) {
//        commonTemplate.convertAndSend(map.get("key"), map.get("message"));
//    }

//    private void repeatNTimes(Consumer<String> consumer, String s, int n) {
//        while (n-- > 0) {
//            consumer.accept(s);
//        }
//    }

}
