package com.nkoad.wallbler.facebook.mapper;

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

}
