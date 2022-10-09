package com.nkoad.wallbler.main.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WallblerSchedulerDto {

    private String schedulerName;

    private String wallblerType;

    private List<String> feedNames;

    private boolean enabled;

    private int period;

}
