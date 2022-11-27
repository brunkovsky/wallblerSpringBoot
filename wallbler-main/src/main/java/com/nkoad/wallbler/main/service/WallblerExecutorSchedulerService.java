package com.nkoad.wallbler.main.service;

import com.nkoad.wallbler.main.mapper.WallblerExecutorSchedulerConfigMapper;
import com.nkoad.wallbler.main.model.WallblerExecutorScheduler;
import com.nkoad.wallbler.main.model.WallblerRegister;
import com.nkoad.wallbler.main.model.WallblerSchedulerDto;
import com.nkoad.wallbler.main.repository.WallblerExecutorSchedulerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class WallblerExecutorSchedulerService {

    private final WallblerExecutorSchedulerRepository schedulerRepository;
    private final WallblerExecutorSchedulerConfigMapper schedulerConfigMapper;

    public List<WallblerExecutorScheduler> getSchedulers() {
        return schedulerRepository.findAll();
    }

    public List<WallblerExecutorScheduler> getSchedulersByType(String type) {
        return schedulerRepository.findAllByType(type);
    }

    public WallblerExecutorScheduler getSchedulerById(String name) {
        return schedulerRepository
                .findById(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public WallblerExecutorScheduler saveScheduler(WallblerSchedulerDto schedulerDto) {
        if (schedulerExists(schedulerDto)) {
            throw new IllegalArgumentException("The scheduler already exists: "
                    + schedulerDto.getSchedulerName());
        }
        return schedulerRepository.save(schedulerConfigMapper.schedulerDtoToScheduler(schedulerDto));
    }

    public void editSchedulerById(String schedulerName, WallblerSchedulerDto schedulerDto) {
        if (schedulerExists(schedulerName)) {
            schedulerRepository.updateScheduler(schedulerDto.isEnable(),
                    schedulerDto.getPeriod(), schedulerName);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void delSchedulerById(String name) {
        schedulerRepository.delete(schedulerRepository.getOne(name));
    }

    public void registerExecutor(WallblerRegister wallblerRegister) {
        schedulerRepository.registerWallblerExecutor(wallblerRegister.getName(),
                wallblerRegister.getSchedulerName());
    }

    public void unRegisterExecutor(WallblerRegister wallblerRegister) {
        List<String> schedulerNames = schedulerRepository.findSchedulersByWallblerName(wallblerRegister.getName());
        for (String schedulerName : schedulerNames) {
            schedulerRepository.unRegisterExecutor(wallblerRegister.getName(), schedulerName);
        }
    }

    private boolean schedulerExists(WallblerSchedulerDto schedulerDto) {
        return schedulerExists(schedulerDto.getSchedulerName());
    }

    private boolean schedulerExists(String schedulerName) {
        return schedulerRepository.existsById(schedulerName);
    }

}
