package com.nkoad.wallbler.facebook.connector;

import com.nkoad.wallbler.facebook.model.WallblerItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.minidev.json.JSONObject;

import java.util.List;

@Data
@AllArgsConstructor
public abstract class FeedType {
    private String endPoint;
    private String fields;
    private String type;

    FeedType(String endPoint, String fields) {
        this.endPoint = endPoint;
        this.fields = fields;
    }

    public abstract List<WallblerItem> parseResult(JSONObject jsonObject);

}
