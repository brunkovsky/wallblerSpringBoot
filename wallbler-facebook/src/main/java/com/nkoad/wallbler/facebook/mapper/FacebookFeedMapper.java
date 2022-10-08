package com.nkoad.wallbler.facebook.mapper;

import com.nkoad.wallbler.facebook.model.WallblerSchedulerDto;
import com.nkoad.wallbler.facebook.model.feed.FacebookFeed;
import com.nkoad.wallbler.facebook.model.feed.FacebookFeedDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Value;

@Mapper(componentModel = "spring")
public abstract class FacebookFeedMapper {

    @Value("${wallbler.type}")
    String wallblerType;

    public abstract FacebookFeed feedDtoToFeed(FacebookFeedDto facebookFeedDto);

    public abstract FacebookFeedDto feedToFeedDto(FacebookFeed facebookFeed);

    @Mapping(target = "feedName", source = "facebookFeedDto.feedName")
    @Mapping(target = "wallblerType", expression = "java(wallblerType)")
    @Mapping(target = "enabled", source = "scheduler.enabled")
    @Mapping(target = "period", source = "scheduler.period")
    public abstract WallblerSchedulerDto feedDtoToScheduler(FacebookFeedDto facebookFeedDto);

}
