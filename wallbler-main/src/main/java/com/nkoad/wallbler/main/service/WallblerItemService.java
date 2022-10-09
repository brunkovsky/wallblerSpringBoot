package com.nkoad.wallbler.main.service;

import com.nkoad.wallbler.main.model.WallblerItem;
import com.nkoad.wallbler.main.repository.WallblerItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WallblerItemService {

    private final WallblerItemRepository wallblerItemRepository;


    public void writeWallblerItems(List<WallblerItem> wallblerItems) {
        Iterable<WallblerItem> allById = wallblerItemRepository.findAllById(wallblerItems.stream().map(WallblerItem::getPostLink).collect(Collectors.toList()));
        wallblerItemRepository.deleteAll(allById);
        wallblerItemRepository.saveAll(wallblerItems);
    }

}
