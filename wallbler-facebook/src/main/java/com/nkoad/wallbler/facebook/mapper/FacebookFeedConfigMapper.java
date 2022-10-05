package com.nkoad.wallbler.facebook.mapper;

import com.nkoad.wallbler.facebook.model.WallblerSchedulerConfigDto;
import com.nkoad.wallbler.facebook.model.feed.FacebookFeedConfig;
import com.nkoad.wallbler.facebook.model.feed.FacebookFeedConfigDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Value;

@Mapper(componentModel = "spring")
public abstract class FacebookFeedConfigMapper {

    @Value("${wallbler.type}")
    String wallblerType;

    public abstract FacebookFeedConfig feedConfigDtoToFeedConfig(FacebookFeedConfigDto feedConfigDto);

    public abstract FacebookFeedConfigDto feedConfigToFeedConfigDto(FacebookFeedConfig feedConfig);

    @Mapping(target = "feedName", source = "feedConfigDTO.feedName")
    @Mapping(target = "wallblerType", expression = "java(wallblerType)")
    @Mapping(target = "enabled", source = "scheduler.enabled")
    @Mapping(target = "period", source = "scheduler.period")
    public abstract WallblerSchedulerConfigDto feedConfigDtoToSchedulerConfig(FacebookFeedConfigDto feedConfigDTO);

}
