package com.nkoad.wallbler.facebook.model;

import lombok.Data;

@Data
public class RegisterAccount {

    private String accountName;
    private String schedulerName;

    public RegisterAccount(String accountName) {
        this.accountName = accountName;
    }

    public RegisterAccount(String accountName, String schedulerName) {
        this.accountName = accountName;
        this.schedulerName = schedulerName;
    }

}
