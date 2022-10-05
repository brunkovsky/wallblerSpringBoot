package com.nkoad.wallbler.facebook.model.feed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacebookFeedConfig {

    @Id
    @SequenceGenerator(
            name = "facebook_feed_id_sequence",
            sequenceName = "facebook_feed_id_sequence")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "facebook_feed_id_sequence")
    private Integer id;

    private String feedName;

    private String accountName;

    private FacebookFeedType feedType;

}
