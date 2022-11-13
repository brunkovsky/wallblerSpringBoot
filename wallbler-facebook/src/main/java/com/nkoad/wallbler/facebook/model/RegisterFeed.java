package com.nkoad.wallbler.facebook.model;

import lombok.Data;

@Data
public class RegisterFeed {

    private String feedName;
    private String schedulerName;

    public RegisterFeed(String feedName, String schedulerName) {
        this.feedName = feedName;
        this.schedulerName = schedulerName;
    }

    public RegisterFeed(String feedName) {
        this.feedName = feedName;
    }

}
