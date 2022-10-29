package com.nkoad.wallbler.main.mapper;

import com.nkoad.wallbler.main.model.WallblerScheduler;
import com.nkoad.wallbler.main.model.WallblerSchedulerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", imports = java.util.Date.class)
public interface WallblerSchedulerConfigMapper {

    WallblerScheduler schedulerDtoToScheduler(WallblerSchedulerDto schedulerDto);

    WallblerSchedulerDto schedulerToSchedulerDto(WallblerScheduler scheduler);

}
