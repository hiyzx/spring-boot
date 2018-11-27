package org.zero.elastic.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.zero.elastic.search.dto.GoodsInfo;

@Component
public interface GoodsRepository extends ElasticsearchRepository<GoodsInfo, Long> {
}