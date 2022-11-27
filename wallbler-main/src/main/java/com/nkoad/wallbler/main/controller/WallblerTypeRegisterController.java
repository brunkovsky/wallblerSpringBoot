package com.nkoad.wallbler.main.controller;

import com.nkoad.wallbler.main.model.WallblerType;
import com.nkoad.wallbler.main.service.WallblerTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallbler/type/register")
public class WallblerTypeRegisterController {

    private final WallblerTypeService service;

    @GetMapping
    public List<WallblerType> getTypes() {
        return service.getTypes();
    }

    @PostMapping("/{wallblerType}")
    public void registerWallblerType(@PathVariable String wallblerType) {
        service.registerWallblerType(wallblerType);
    }

}
