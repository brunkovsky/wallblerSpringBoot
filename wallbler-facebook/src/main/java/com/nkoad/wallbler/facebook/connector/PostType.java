package com.nkoad.wallbler.facebook.connector;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nkoad.wallbler.facebook.model.FacebookAccount;
import com.nkoad.wallbler.facebook.model.WallblerItem;
import lombok.Data;
import lombok.SneakyThrows;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component("POST")
public class PostType extends FacebookType {

    private final ObjectMapper objectMapper;

    PostType(ObjectMapper objectMapper) {
        super("posts",
                "permalink_url,full_picture,message,actions,created_time");
        this.objectMapper = objectMapper;
    }

    @Override
    @SneakyThrows
    public List<WallblerItem> parseResult(JSONObject jsonObject, FacebookAccount facebookAccount) {
        FacebookResponse facebookResponse = objectMapper.readValue(jsonObject.toJSONString(), FacebookResponse.class);
        return facebookResponse.getData()
                .stream()
                .map(data -> new WallblerItem(
                        data.getId(),
                        "FACEBOOK",
                        facebookAccount.getAccountName(),
                        data.getPermalinkUrl(),
                        data.getMessage(),
                        data.getDescription(),
                        null,
                        data.getFullPicture(),
                        null,
                        data.getCreatedTime(),
                        facebookAccount.isApprovedByDefault()))
                .collect(Collectors.toList());
    }

    @Data
    static class FacebookResponse {

        private List<FacebookData> data;

        @Data
        static class FacebookData {
            String id;
            @JsonProperty("permalink_url")
            private String permalinkUrl;
            @JsonProperty("full_picture")
            private String fullPicture;
            private String message;
            private String description;
            @JsonProperty("created_time")
            private Instant createdTime;
        }
    }

}
