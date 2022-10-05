package com.nkoad.wallbler.facebook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacebookSchedulerConfigDto {

    private boolean enabled;

    private int period;

}
