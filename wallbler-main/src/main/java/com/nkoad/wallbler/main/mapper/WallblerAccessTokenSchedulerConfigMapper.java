package com.nkoad.wallbler.main.mapper;

import com.nkoad.wallbler.main.model.WallblerAccessTokenScheduler;
import com.nkoad.wallbler.main.model.WallblerSchedulerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", imports = java.util.Date.class)
public interface WallblerAccessTokenSchedulerConfigMapper {

    WallblerAccessTokenScheduler schedulerDtoToScheduler(WallblerSchedulerDto schedulerDto);

    WallblerSchedulerDto schedulerToSchedulerDto(WallblerAccessTokenScheduler scheduler);

}
