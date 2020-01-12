package org.zero.cloud.client.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yezhaoxing
 * @date 2020/1/4
 */
@FeignClient(name = "cloud-server")
@RequestMapping(value = "/stock")
public interface StockClient {

    @GetMapping("/reduceCount")
    void reduceCount(@RequestParam(value = "stockId") Integer stockId, @RequestParam(value = "count") Integer count);
}
