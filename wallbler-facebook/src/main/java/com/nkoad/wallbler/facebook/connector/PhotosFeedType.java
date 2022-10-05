package com.nkoad.wallbler.facebook.connector;

import com.nkoad.wallbler.facebook.model.WallblerItem;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("PHOTO")
public class PhotosFeedType extends FeedType {

    PhotosFeedType() {
        super("photos",
                "link,images,name,created_time,comments.summary(true).limit(0),from,album",
                "uploaded");
    }

    @Override
    public List<WallblerItem> parseResult(JSONObject jsonObject) {
        return ((List<Map<String, Object>>) jsonObject.get("data")).stream()
                .map(x -> new WallblerItem(
                        (String) x.get("link"),
                        null,
                        null,
                        ((Map<String, String>) ((List<?>) x.get("images")).get(0)).get("source"),
                        null
                ))
                .collect(Collectors.toList());
    }

}
