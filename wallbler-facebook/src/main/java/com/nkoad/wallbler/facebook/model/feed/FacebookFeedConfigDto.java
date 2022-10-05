package com.nkoad.wallbler.facebook.model.feed;

import com.nkoad.wallbler.facebook.model.FacebookSchedulerConfigDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacebookFeedConfigDto {

    private String feedName;

    private String accountName;

    private FacebookFeedType feedType;

    private FacebookSchedulerConfigDto scheduler;

}
