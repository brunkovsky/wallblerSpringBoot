package com.nkoad.wallbler.main.mapper;

import com.nkoad.wallbler.main.model.WallblerExecutorScheduler;
import com.nkoad.wallbler.main.model.WallblerSchedulerDto;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-13T20:03:52+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class WallblerExecutorSchedulerConfigMapperImpl implements WallblerExecutorSchedulerConfigMapper {

    @Override
    public WallblerExecutorScheduler schedulerDtoToScheduler(WallblerSchedulerDto schedulerDto) {
        if ( schedulerDto == null ) {
            return null;
        }

        WallblerExecutorScheduler wallblerExecutorScheduler = new WallblerExecutorScheduler();

        wallblerExecutorScheduler.setSchedulerName( schedulerDto.getSchedulerName() );
        wallblerExecutorScheduler.setEnable( schedulerDto.isEnable() );
        wallblerExecutorScheduler.setPeriod( schedulerDto.getPeriod() );

        return wallblerExecutorScheduler;
    }

    @Override
    public WallblerSchedulerDto schedulerToSchedulerDto(WallblerExecutorScheduler scheduler) {
        if ( scheduler == null ) {
            return null;
        }

        WallblerSchedulerDto wallblerSchedulerDto = new WallblerSchedulerDto();

        wallblerSchedulerDto.setSchedulerName( scheduler.getSchedulerName() );
        wallblerSchedulerDto.setEnable( scheduler.isEnable() );
        wallblerSchedulerDto.setPeriod( scheduler.getPeriod() );

        return wallblerSchedulerDto;
    }
}
