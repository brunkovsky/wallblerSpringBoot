package com.nkoad.wallbler.main.mapper;

import com.nkoad.wallbler.main.model.WallblerAccountScheduler;
import com.nkoad.wallbler.main.model.WallblerSchedulerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", imports = java.util.Date.class)
public interface WallblerSchedulerAccountConfigMapper {

    WallblerAccountScheduler schedulerDtoToScheduler(WallblerSchedulerDto schedulerDto);

    WallblerSchedulerDto schedulerToSchedulerDto(WallblerAccountScheduler scheduler);

}
