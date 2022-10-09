package com.nkoad.wallbler.main.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WallblerScheduler {

    @Id
    @Column(length = 128)
    private String schedulerName;

    private String wallblerType;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> feedNames;

    private boolean enabled;

    private int period;

    private Date lastTimeFetched;

}
