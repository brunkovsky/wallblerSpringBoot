package com.nkoad.wallbler.facebook.validator;

import com.nkoad.wallbler.facebook.client.FacebookClient;
import com.nkoad.wallbler.facebook.model.FacebookAccountDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class FacebookAccountValidator {

    private final FacebookClient facebookClient;

    public boolean validate(FacebookAccountDto facebookAccountDto) {
        try {
            JSONObject screenName = facebookClient.getScreenName(facebookAccountDto.getGroupId(), facebookAccountDto.getAccessToken());
            log.debug("Passed the validation. set 'valid' to true. Screen name: {}", screenName);
        } catch (Exception e) {
            log.warn("{} did not pass the validation. set 'valid' to false", facebookAccountDto);
            return false;
        }
        return true;
    }

}
