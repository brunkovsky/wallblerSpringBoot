package com.nkoad.wallbler.facebook.model.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountScheduler {

    private String schedulerName;
    private String accountNames;
    private boolean enabled;
    private int period;
    private Date lastTimeFetched;

}
