package com.nkoad.wallbler.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "wallbler", createIndex = false)
public class WallblerItem {

    @Id
    private String id;
    private String type;
    private String feedName;
    private String link;
    private String title;
    private String message;
    private String thumbnail;
    private String photoLink;
    private String videoLink;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSZZ")
    private Instant createdAt;
    private boolean approved;

}
