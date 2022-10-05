package com.nkoad.wallbler.twitter.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class TempService {

    List<String> list;

    @PostConstruct
    public void init() {
        list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
    }

    public List<String> getStrings() {
        return list;
    }


    public void setString(String s) {
        list.add(s);
    }

    public void getException(String exceptionAttribute) throws Exception {
        throw new Exception(exceptionAttribute);
    }
}
