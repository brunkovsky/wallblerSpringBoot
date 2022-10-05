package com.nkoad.wallbler.twitter.controller;

import com.nkoad.wallbler.twitter.config.TempProperties;
import com.nkoad.wallbler.twitter.service.TempService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "api/wallbler/twitter/temp")
public class Controller {

    private final TempService service;

    private final TempProperties tempProperties;

    @GetMapping
    public List<String> getStrings() {
        System.out.println(tempProperties.getAaa());
        System.out.println(tempProperties.getBbb());
        System.out.println(tempProperties.getW());
        System.out.println(tempProperties.getX());
        System.out.println(tempProperties.isY());
        System.out.println(tempProperties.isZ());
        return service.getStrings();
    }

    @GetMapping("/error/{exceptionAttribute}")
    public void getError(@PathVariable String exceptionAttribute) throws Exception {
        service.getException(exceptionAttribute);
    }

    @PostMapping
    public void setString(@RequestBody String s) {
        service.setString(s);
    }

}
