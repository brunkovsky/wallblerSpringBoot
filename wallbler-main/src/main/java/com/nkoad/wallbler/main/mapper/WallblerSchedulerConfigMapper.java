package com.nkoad.wallbler.main.mapper;

import com.nkoad.wallbler.main.model.WallblerSchedulerConfig;
import com.nkoad.wallbler.main.model.WallblerSchedulerConfigDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = java.util.Date.class)
public interface WallblerSchedulerConfigMapper {

    @Mapping(target = "lastTimeFetched", expression = "java(new Date())")
    WallblerSchedulerConfig schedulerConfigDtoToSchedulerConfig(WallblerSchedulerConfigDto schedulerConfigDto);

    WallblerSchedulerConfigDto schedulerConfigToSchedulerConfigDto(WallblerSchedulerConfig schedulerConfig);

}
