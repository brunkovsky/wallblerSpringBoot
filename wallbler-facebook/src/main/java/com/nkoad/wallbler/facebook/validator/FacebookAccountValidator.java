package com.nkoad.wallbler.facebook.validator;

import com.nkoad.wallbler.facebook.client.FacebookClient;
import com.nkoad.wallbler.facebook.model.account.FacebookAccountConfigDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class FacebookAccountValidator {

    private final FacebookClient facebookClient;


    public boolean validate(FacebookAccountConfigDto accountConfigDTO) {
        try {
            JSONObject screenName = facebookClient.getScreenName(accountConfigDTO.getGroupId(), accountConfigDTO.getAccessToken());
            System.out.println(screenName);
        } catch (Exception e) {
            log.warn("{} did not pass the validation. set 'valid' to false", accountConfigDTO);
            return false;
        }
        log.debug("{} passed the validation. set 'valid' to true", accountConfigDTO);
        return true;
    }

}
