package com.nkoad.wallbler.facebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // add Builder
public class WallblerItem {

    private String postLink;
    private String title;
    private String text;
    private String photoLink;
    private String videoLink;

}
