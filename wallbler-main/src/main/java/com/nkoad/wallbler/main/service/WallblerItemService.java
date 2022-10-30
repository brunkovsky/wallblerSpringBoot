package com.nkoad.wallbler.main.service;

import com.nkoad.wallbler.main.model.WallblerItem;
import com.nkoad.wallbler.main.repository.WallblerItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@AllArgsConstructor
public class WallblerItemService {

    private final WallblerItemRepository wallblerItemRepository;


    public void writeWallblerItems(List<WallblerItem> wallblerItems) {
        List<WallblerItem> existingWallblerItems = getExistingWallblerItems(wallblerItems);
        List<WallblerItem> wallblerItemsToStore = defineWallblerItemsToStore(wallblerItems, existingWallblerItems);
        if (!wallblerItemsToStore.isEmpty()) {
            wallblerItemRepository.saveAll(wallblerItemsToStore);
            log.debug("{} new items stored in the repository", wallblerItemsToStore.size());
        }
    }

    private List<WallblerItem> getExistingWallblerItems(List<WallblerItem> wallblerItems) {
        return StreamSupport.stream(wallblerItemRepository
                        .findAllById(wallblerItems.stream()
                                .map(WallblerItem::getId)
                                .collect(Collectors.toList())).spliterator(), false)
                .collect(Collectors.toList());
    }

    private List<WallblerItem> defineWallblerItemsToStore(List<WallblerItem> wallblerItems,
                                                          List<WallblerItem> existingWallblerItems) {
        return wallblerItems.stream()
                .filter(wallblerItem -> !existingWallblerItems.contains(wallblerItem))
                .collect(Collectors.toList());
    }

}
