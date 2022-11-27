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

@Component("VIDEO")
public class VideoType extends FacebookType {

    private final ObjectMapper objectMapper;

    VideoType(ObjectMapper objectMapper) {
        super("videos/uploaded",
                "permalink_url,source,picture,title,description,created_time");
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
                        "https://facebook.com" + data.getPermalinkUrl(),
                        data.getTitle(),
                        data.getDescription(),
                        data.getPicture(),
                        null,
                        data.getSource(),
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
            private String source;
            private String picture;
            private String title;
            private String description;
            @JsonProperty("created_time")
            private Instant createdTime;

            @Data
            static class FacebookDataImage {
                private Integer height;
                private String source;
                private Integer width;
            }
        }

    }

}
