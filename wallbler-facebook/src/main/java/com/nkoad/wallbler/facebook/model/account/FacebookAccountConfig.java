package com.nkoad.wallbler.facebook.model.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacebookAccountConfig {

    @Id
    @Column(length = 128)
    private String accountName;
    private String accessToken;
    private String groupId;
    private boolean valid;

}
