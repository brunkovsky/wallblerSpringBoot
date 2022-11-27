package com.nkoad.wallbler.facebook.model;

import lombok.Data;

@Data
public class RegisterAccount {

    private String name;
    private String schedulerName;

    public RegisterAccount(String name) {
        this.name = name;
    }

    public RegisterAccount(String name, String schedulerName) {
        this.name = name;
        this.schedulerName = schedulerName;
    }

}
