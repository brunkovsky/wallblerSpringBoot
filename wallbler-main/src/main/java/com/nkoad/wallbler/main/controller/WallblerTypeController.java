package com.nkoad.wallbler.main.controller;

import com.nkoad.wallbler.main.model.WallblerType;
import com.nkoad.wallbler.main.service.WallblerTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallbler/type")
public class WallblerTypeController {

    private final WallblerTypeService service;

    @GetMapping
    public List<WallblerType> getTypes() {
        return service.getTypes();
    }

}
