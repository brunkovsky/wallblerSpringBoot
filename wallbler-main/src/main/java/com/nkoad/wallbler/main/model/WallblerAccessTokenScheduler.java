package com.nkoad.wallbler.main.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WallblerAccessTokenScheduler {

    @Id
    @Column(length = 128)
    private String schedulerName;

    private String wallblerNames;

    private boolean enable;

    private int period;

    private Date lastTimeFetched;

}
