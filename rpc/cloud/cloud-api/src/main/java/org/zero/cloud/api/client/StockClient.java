package org.zero.cloud.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yezhaoxing
 * @date 2020/1/4
 */
@FeignClient(name = "cloud-server")
@RequestMapping(value = "/stock")
public interface StockClient {

    @GetMapping("/reduceCount")
    void reduceCount(Integer stockId, Integer count);
}
