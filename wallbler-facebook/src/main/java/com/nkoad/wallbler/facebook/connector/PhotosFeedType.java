package com.nkoad.wallbler.facebook.connector;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nkoad.wallbler.facebook.model.WallblerItem;
import lombok.Data;
import lombok.SneakyThrows;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component("PHOTO")
public class PhotosFeedType extends FeedType {

    private final ObjectMapper objectMapper;

    PhotosFeedType(ObjectMapper objectMapper) {
        super("photos",
                "link,images,name,created_time",
                "uploaded");
        this.objectMapper = objectMapper;
    }

    @Override
    @SneakyThrows
    public List<WallblerItem> parseResult(JSONObject jsonObject) {
        FacebookResponse facebookResponse = objectMapper.readValue(jsonObject.toJSONString(), FacebookResponse.class);
        return facebookResponse.getData()
                .stream()
                .map(data -> new WallblerItem(
                        data.getLink(),
                        data.getName(),
                        null,
                        data.getImages().get(data.getImages().size() - 1).getSource(),
                        data.getImages().get(0).getSource(),
                        null,
                        data.getCreatedTime()))
                .collect(Collectors.toList());
    }

    @Data
    static class FacebookResponse {

        private List<FacebookData> data;

        @Data
        static class FacebookData {
            private String link;
            private List<FacebookDataImage> images;
            private String name;
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
