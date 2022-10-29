package com.nkoad.wallbler.facebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor // add Builder
public class WallblerItem {

    private String link;
    private String title;
    private String message;
    private String thumbnail;
    private String photoLink;
    private String videoLink;
    private Instant createdAt;

}
