package com.nkoad.wallbler.facebook.model.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacebookAccountConfig {

    @Id
    @SequenceGenerator(
            name = "facebook_account_id_sequence",
            sequenceName = "facebook_account_id_sequence")
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "facebook_account_id_sequence")
    private Integer id;
    private String accountName;
    private String accessToken;
    private String groupId;
    private boolean valid;

}
