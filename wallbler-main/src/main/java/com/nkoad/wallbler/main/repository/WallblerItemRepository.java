package com.nkoad.wallbler.main.repository;

import com.nkoad.wallbler.main.model.WallblerItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface WallblerItemRepository extends ElasticsearchRepository<WallblerItem, String> {
}
