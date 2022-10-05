package com.nkoad.wallbler.facebook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WallblerSchedulerConfigDto { // TODO : to the parent pom. it should be common for all socials

    private String feedName;

    private String wallblerType;

    private boolean enabled;

    private int period;

}
