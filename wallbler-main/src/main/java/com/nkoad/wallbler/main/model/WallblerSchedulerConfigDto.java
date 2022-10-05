package com.nkoad.wallbler.main.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WallblerSchedulerConfigDto {

    private String feedName;

    private String wallblerType;

    private boolean enabled;

    private int period;

}
