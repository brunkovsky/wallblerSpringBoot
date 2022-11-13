package com.nkoad.wallbler.main.mapper;

import com.nkoad.wallbler.main.model.WallblerFeedScheduler;
import com.nkoad.wallbler.main.model.WallblerSchedulerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", imports = java.util.Date.class)
public interface WallblerSchedulerFeedConfigMapper {

    WallblerFeedScheduler schedulerDtoToScheduler(WallblerSchedulerDto schedulerDto);

    WallblerSchedulerDto schedulerToSchedulerDto(WallblerFeedScheduler scheduler);

}
