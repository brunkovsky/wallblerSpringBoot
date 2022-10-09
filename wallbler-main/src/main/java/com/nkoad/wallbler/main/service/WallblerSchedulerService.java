package com.nkoad.wallbler.main.service;

import com.nkoad.wallbler.main.mapper.WallblerSchedulerConfigMapper;
import com.nkoad.wallbler.main.model.WallblerScheduler;
import com.nkoad.wallbler.main.model.WallblerSchedulerDto;
import com.nkoad.wallbler.main.model.WallblerType;
import com.nkoad.wallbler.main.repository.WallblerSchedulerRepository;
import com.nkoad.wallbler.main.repository.WallblerTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class WallblerSchedulerService {

    private final WallblerSchedulerRepository schedulerRepository;
    private final WallblerTypeRepository typeRepository;
    private final WallblerSchedulerConfigMapper schedulerMapper;


    public List<WallblerScheduler> getSchedulers() {
        return schedulerRepository
                .findAll();
    }

    public WallblerScheduler getSchedulerById(String name) {
        return schedulerRepository
                .findById(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public WallblerScheduler saveScheduler(WallblerSchedulerDto schedulerDto) {
        if (schedulerAlreadyExists(schedulerDto)) {
            throw new IllegalArgumentException("The scheduler already exists: "
                    + schedulerDto.getSchedulerName());
        }
        if (wallblerTypeInvalid(schedulerDto)) {
            throw new IllegalArgumentException("The wallblerType does not exists or inactive: "
                    + schedulerDto.getWallblerType());
        }
        return saveOrUpdateScheduler(schedulerDto);
    }

    public WallblerScheduler editSchedulerById(WallblerSchedulerDto schedulerDto) {
        if (schedulerAlreadyExists(schedulerDto)) {
            return saveOrUpdateScheduler(schedulerDto);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void delSchedulerById(String name) {
        schedulerRepository
                .delete(schedulerRepository.getOne(name));
    }

    private boolean wallblerTypeInvalid(WallblerSchedulerDto schedulerDto) {
        return !wallblerTypeValid(schedulerDto);
    }

    private boolean wallblerTypeValid(WallblerSchedulerDto schedulerDto) {
        return typeRepository
                .findById(schedulerDto.getWallblerType())
                .map(WallblerType::isActive)
                .orElse(false);
    }

    private WallblerScheduler saveOrUpdateScheduler(WallblerSchedulerDto schedulerDto) {
        return schedulerRepository
                .save(schedulerMapper.schedulerDtoToScheduler(schedulerDto));
    }

    private boolean schedulerAlreadyExists(WallblerSchedulerDto schedulerDto) {
        return schedulerRepository
                .existsById(schedulerDto.getSchedulerName());
    }

}
