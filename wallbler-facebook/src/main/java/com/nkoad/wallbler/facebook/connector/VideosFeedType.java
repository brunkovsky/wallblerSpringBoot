package com.nkoad.wallbler.facebook.connector;

import com.nkoad.wallbler.facebook.model.WallblerItem;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("VIDEO")
public class VideosFeedType extends FeedType {

    VideosFeedType() {
        super("videos/uploaded",
                "permalink_url,picture,title,description,created_time,comments.summary(true).limit(0),from");
    }

    @Override
    public List<WallblerItem> parseResult(JSONObject jsonObject) {
        return ((JSONArray) jsonObject.get("data")).stream()
                .map(x -> (Map<String, String>) x)
                .map(x -> new WallblerItem(
                        x.get("permalink_url"),
                        null,
                        x.get("description"),
                        x.get("picture"),
                        x.get("permalink_url")
                ))
                .collect(Collectors.toList());
    }

}
