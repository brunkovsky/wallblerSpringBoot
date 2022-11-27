package com.nkoad.wallbler.main.service;

import com.nkoad.wallbler.main.mapper.WallblerAccessTokenSchedulerConfigMapper;
import com.nkoad.wallbler.main.model.WallblerAccessTokenScheduler;
import com.nkoad.wallbler.main.model.WallblerRegister;
import com.nkoad.wallbler.main.model.WallblerSchedulerDto;
import com.nkoad.wallbler.main.repository.WallblerAccessTokenSchedulerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class WallblerAccessTokenSchedulerService {

    private final WallblerAccessTokenSchedulerRepository schedulerRepository;
    private final WallblerAccessTokenSchedulerConfigMapper schedulerConfigMapper;

    public List<WallblerAccessTokenScheduler> getSchedulers() {
        return schedulerRepository.findAll();
    }

    public List<WallblerAccessTokenScheduler> getSchedulersByType(String type) {
        return schedulerRepository.findAllByType(type);
    }

    public WallblerAccessTokenScheduler getSchedulerById(String name) {
        return schedulerRepository
                .findById(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public WallblerAccessTokenScheduler saveScheduler(WallblerSchedulerDto schedulerDto) {
        if (schedulerExists(schedulerDto)) {
            throw new IllegalArgumentException("The scheduler provided already exists: "
                    + schedulerDto.getSchedulerName());
        }
        WallblerAccessTokenScheduler scheduler = schedulerConfigMapper
                .schedulerDtoToScheduler(schedulerDto);
        return schedulerRepository.save(scheduler);
    }

    public void editSchedulerById(String schedulerName, WallblerSchedulerDto schedulerDto) {
        if (schedulerExists(schedulerName)) {
            schedulerRepository.updateScheduler(schedulerDto.isEnable(),
                    schedulerDto.getPeriod(), schedulerName);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void delSchedulerById(String name) { // TODO : to check is the 'account_names' empty before. delete if yes only
        schedulerRepository.delete(schedulerRepository.getOne(name));
    }

    public void registerAccessToken(WallblerRegister wallblerRegister) {
        schedulerRepository.registerAccessToken(wallblerRegister.getName(),
                wallblerRegister.getSchedulerName());
    }

    public void unRegisterAccessToken(WallblerRegister wallblerRegister) {
        List<String> schedulerNames = schedulerRepository
                .findSchedulersByWallblerName(wallblerRegister.getName());
        for (String schedulerName : schedulerNames) {
            schedulerRepository.unRegisterAccessToken(wallblerRegister.getName(),
                    schedulerName);
        }
    }

    private boolean schedulerExists(WallblerSchedulerDto schedulerDto) {
        return schedulerExists(schedulerDto.getSchedulerName());
    }

    private boolean schedulerExists(String schedulerName) {
        return schedulerRepository.existsById(schedulerName);
    }

}
