package com.nkoad.wallbler.main.mapper;

import com.nkoad.wallbler.main.model.WallblerScheduler;
import com.nkoad.wallbler.main.model.WallblerSchedulerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = java.util.Date.class)
public interface WallblerSchedulerConfigMapper {

    @Mapping(target = "lastTimeFetched", expression = "java(new Date())") //TODO: only if null
    WallblerScheduler schedulerDtoToScheduler(WallblerSchedulerDto schedulerDto);

    WallblerSchedulerDto schedulerToSchedulerDto(WallblerScheduler scheduler);

}
