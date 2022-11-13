package com.nkoad.wallbler.main.service;

import com.nkoad.wallbler.main.mapper.WallblerSchedulerFeedConfigMapper;
import com.nkoad.wallbler.main.model.WallblerFeedScheduler;
import com.nkoad.wallbler.main.model.WallblerRegisterFeed;
import com.nkoad.wallbler.main.model.WallblerSchedulerDto;
import com.nkoad.wallbler.main.repository.WallblerSchedulerFeedRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class WallblerSchedulerFeedService {

    private final WallblerSchedulerFeedRepository feedRepository;
    private final WallblerSchedulerFeedConfigMapper feedConfigMapper;

    public List<WallblerFeedScheduler> getSchedulers() {
        return feedRepository.findAll();
    }

    public List<WallblerFeedScheduler> getSchedulersByType(String type) {
        return feedRepository.findAllByType(type);
    }

    public WallblerFeedScheduler getSchedulerById(String name) {
        return feedRepository
                .findById(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public WallblerFeedScheduler saveScheduler(WallblerSchedulerDto schedulerDto) {
        if (schedulerAlreadyExists(schedulerDto)) {
            throw new IllegalArgumentException("The scheduler already exists: "
                    + schedulerDto.getSchedulerName());
        }
        return saveOrUpdateScheduler(schedulerDto);
    }

    public WallblerFeedScheduler editSchedulerById(WallblerSchedulerDto schedulerDto) {
        if (schedulerAlreadyExists(schedulerDto)) {
            return saveOrUpdateScheduler(schedulerDto);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void delSchedulerById(String name) {
        feedRepository.delete(feedRepository.getOne(name));
    }

    public void registerFeed(WallblerRegisterFeed registerFeed) {
        feedRepository.registerWallblerFeed(registerFeed.getFeedName(),
                registerFeed.getSchedulerName(), registerFeed.getWallblerType());
    }

    public void unRegisterFeed(WallblerRegisterFeed registerFeed) {
        List<String> schedulerNames = feedRepository.findSchedulersByFeedName(registerFeed.getFeedName());
        for (String schedulerName : schedulerNames) {
            feedRepository.unRegisterWallblerFeed(registerFeed.getFeedName(),
                    schedulerName, registerFeed.getWallblerType());
        }
    }

    private WallblerFeedScheduler saveOrUpdateScheduler(WallblerSchedulerDto schedulerDto) {
        WallblerFeedScheduler scheduler = feedConfigMapper.schedulerDtoToScheduler(schedulerDto);
        return feedRepository.save(scheduler);
    }

    private boolean schedulerAlreadyExists(WallblerSchedulerDto schedulerDto) {
        return feedRepository.existsById(schedulerDto.getSchedulerName());
    }

}
