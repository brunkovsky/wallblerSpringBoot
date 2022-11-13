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
public class WallblerAccountScheduler {

    @Id
    @Column(length = 128)
    private String schedulerName;

    private String accountNames;

    private boolean enabled;

    private int period;

    private Date lastTimeFetched;

}
