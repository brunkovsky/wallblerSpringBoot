package com.nkoad.wallbler.main.mapper;

import com.nkoad.wallbler.main.model.WallblerAccessTokenScheduler;
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
public class WallblerAccessTokenSchedulerConfigMapperImpl implements WallblerAccessTokenSchedulerConfigMapper {

    @Override
    public WallblerAccessTokenScheduler schedulerDtoToScheduler(WallblerSchedulerDto schedulerDto) {
        if ( schedulerDto == null ) {
            return null;
        }

        WallblerAccessTokenScheduler wallblerAccessTokenScheduler = new WallblerAccessTokenScheduler();

        wallblerAccessTokenScheduler.setSchedulerName( schedulerDto.getSchedulerName() );
        wallblerAccessTokenScheduler.setEnable( schedulerDto.isEnable() );
        wallblerAccessTokenScheduler.setPeriod( schedulerDto.getPeriod() );

        return wallblerAccessTokenScheduler;
    }

    @Override
    public WallblerSchedulerDto schedulerToSchedulerDto(WallblerAccessTokenScheduler scheduler) {
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
