package com.nkoad.wallbler.main.service;

import com.nkoad.wallbler.main.model.WallblerType;
import com.nkoad.wallbler.main.repository.WallblerTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WallblerTypeService {

    private final WallblerTypeRepository typeRepository;


    public void registerWallblerType(String typeName) {
        typeRepository.save(new WallblerType(typeName, true));
    }

    public List<WallblerType> getTypes() {
        return typeRepository.findAll();
    }

}
