package com.nkoad.wallbler.main.service;

import com.nkoad.wallbler.main.model.WallblerType;
import com.nkoad.wallbler.main.repository.WallblerTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WallblerTypeService {

    private final WallblerTypeRepository wallblerTypeRepository;


    public void registerWallblerType(String wabblerTypeName) {
        WallblerType wallblerType = new WallblerType(wabblerTypeName, true);
        wallblerTypeRepository.save(wallblerType);
    }

    public List<WallblerType> getTypes() {
        return wallblerTypeRepository.findAll();
    }
}
