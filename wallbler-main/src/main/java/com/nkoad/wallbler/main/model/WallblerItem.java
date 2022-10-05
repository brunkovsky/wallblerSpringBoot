package com.nkoad.wallbler.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "wallbler")
public class WallblerItem {

    @Id
    private String postLink;
    private String title;
    private String text;
    private String photoLink;
    private String videoLink;

}
