package com.nkoad.wallbler.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WallblerRegisterFeed {

    private String wallblerType;
    private String feedName;
    private String schedulerName;

}
