package com.nkoad.wallbler.facebook.connector;

import com.nkoad.wallbler.facebook.model.WallblerItem;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("POST")
public class PostsFeedType extends FeedType {

    PostsFeedType() {
        super("posts",
                "permalink_url,full_picture,message,created_time,shares,comments.summary(true).limit(0),from");
    }

    @Override
    public List<WallblerItem> parseResult(JSONObject jsonObject) {
        return ((List<Map<String, String>>) jsonObject.get("data")).stream()
                .map(x -> new WallblerItem(
                        x.get("permalink_url"),
                        null,
                        x.get("message"),
                        null,
                        null
                ))
                .collect(Collectors.toList());
    }
}
