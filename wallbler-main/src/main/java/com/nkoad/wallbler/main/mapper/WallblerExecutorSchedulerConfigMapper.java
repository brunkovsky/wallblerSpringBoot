package com.nkoad.wallbler.main.mapper;

import com.nkoad.wallbler.main.model.WallblerExecutorScheduler;
import com.nkoad.wallbler.main.model.WallblerSchedulerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", imports = java.util.Date.class)
public interface WallblerExecutorSchedulerConfigMapper {

    WallblerExecutorScheduler schedulerDtoToScheduler(WallblerSchedulerDto schedulerDto);

    WallblerSchedulerDto schedulerToSchedulerDto(WallblerExecutorScheduler scheduler);

}
