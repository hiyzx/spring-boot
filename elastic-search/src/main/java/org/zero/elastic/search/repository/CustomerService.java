package org.zero.elastic.search.repository;

import cn.hutool.json.JSONUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.zero.elastic.search.dto.Customer;
import org.zero.elastic.search.dto.EsEntity;
import org.zero.elastic.search.util.EsUtil;
import org.zero.elastic.search.util.PageResultVO;

/**
 * @author yezhaoxing
 * @date 2019/8/8
 */
@Service
public class CustomerService {

    private static final String INDEX = "bank";

    @Autowired
    private EsUtil esUtil;

    public void saveOrUpdate(Customer customer) {
        esUtil.insertOrUpdateOne(INDEX, new EsEntity(customer.getAccountNumber(), JSONUtil.toJsonStr(customer)));
    }

    public PageResultVO<Customer> search(Integer from, Integer size, String gender, String address) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (StringUtils.hasText(gender)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("gender", gender));
        }
        if (StringUtils.hasText(address)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("address", address));
        }
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(from).size(size).query(boolQueryBuilder);
        return esUtil.search(INDEX, builder, Customer.class);
    }
}
