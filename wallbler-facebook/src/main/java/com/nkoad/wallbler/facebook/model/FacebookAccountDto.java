package com.nkoad.wallbler.facebook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacebookAccountDto {

    private String accountName;
    private String accessToken;
    private String groupId;
    private String facebookType;
    private boolean valid;
    private String executorScheduler;
    private String accessTokenScheduler;

}
