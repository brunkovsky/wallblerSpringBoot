package com.nkoad.wallbler.facebook.model.feed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacebookFeed {

    @Id
    @Column(length = 128)
    private String feedName;

    private String accountName;

    private FacebookFeedType feedType;

}
