package com.nkoad.wallbler.main.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WallblerSchedulerConfig {

    @Id
    @SequenceGenerator(
            name = "wallbler_scheduler_id_sequence",
            sequenceName = "wallbler_scheduler_id_sequence")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wallbler_scheduler_id_sequence")
    Integer id;

    String feedName;

    String wallblerType;

    private boolean enabled;

    private int period;

    private Date lastTimeFetched;

}
