package com.nkoad.wallbler.facebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scheduler {

    private String schedulerName;

    private String wallblerNames;

    private boolean enable;

    private int period;

    private Date lastTimeFetched;

}
