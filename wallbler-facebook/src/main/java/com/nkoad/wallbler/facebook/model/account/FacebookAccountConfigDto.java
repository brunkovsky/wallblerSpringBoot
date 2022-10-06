package com.nkoad.wallbler.facebook.model.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacebookAccountConfigDto {

    private String accountName;
    private String accessToken;
    private String groupId;
    private boolean valid;

}
