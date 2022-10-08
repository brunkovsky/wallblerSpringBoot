package com.nkoad.wallbler.facebook.model.feed;

import com.nkoad.wallbler.facebook.model.FacebookSchedulerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacebookFeedDto {

    private String feedName;

    private String accountName;

    private FacebookFeedType feedType;

    private FacebookSchedulerDto scheduler;

}