package com.nkoad.wallbler.main.controller;

import com.nkoad.wallbler.main.model.WallblerItem;
import com.nkoad.wallbler.main.service.WallblerItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/wallbler")
public class WallblerItemController {

    private final WallblerItemService service;


    @PostMapping("/write")
    public void writeWallbler(@RequestBody List<WallblerItem> wallblerItems) {
        service.writeWallblerItems(wallblerItems);
    }

}
