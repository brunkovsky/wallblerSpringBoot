package com.nkoad.wallbler.main.service;

import com.nkoad.wallbler.main.mapper.WallblerSchedulerConfigMapper;
import com.nkoad.wallbler.main.model.WallblerItem;
import com.nkoad.wallbler.main.model.WallblerSchedulerConfig;
import com.nkoad.wallbler.main.model.WallblerSchedulerConfigDto;
import com.nkoad.wallbler.main.repository.WallblerItemRepository;
import com.nkoad.wallbler.main.repository.WallblerSchedulerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WallblerService {

    private final WallblerSchedulerRepository schedulerRepository;
    private final WallblerItemRepository wallblerItemRepository;
    private final WallblerSchedulerConfigMapper schedulerConfigMapper;

    public void wallblerFeedRegister(WallblerSchedulerConfigDto schedulerConfigDto) {
        WallblerSchedulerConfig schedulerConfig = schedulerConfigMapper.schedulerConfigDtoToSchedulerConfig(schedulerConfigDto);
        schedulerRepository.save(schedulerConfig);
    }

    public List<WallblerSchedulerConfig> getSchedulers() {
        return schedulerRepository.findAll();
    }

    public void writeWallblerItems(List<WallblerItem> wallblerItems) {
        Iterable<WallblerItem> allById = wallblerItemRepository.findAllById(wallblerItems.stream().map(WallblerItem::getPostLink).collect(Collectors.toList()));
        wallblerItemRepository.deleteAll(allById);
        wallblerItemRepository.saveAll(wallblerItems);
    }
}
