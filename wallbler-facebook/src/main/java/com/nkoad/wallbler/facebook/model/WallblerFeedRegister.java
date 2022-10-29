package com.nkoad.wallbler.facebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WallblerFeedRegister {

    private String wallblerType;
    private String feedName;
    private String schedulerName;

}
