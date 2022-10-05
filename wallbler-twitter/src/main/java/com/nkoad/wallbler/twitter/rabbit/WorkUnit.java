package com.nkoad.wallbler.twitter.rabbit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkUnit {
    private String id;
    private String definition;
}
