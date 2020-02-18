package org.zero.cloud.client.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zero.cloud.api.client.StockClient;
import org.zero.cloud.client.entity.OrderMaster;
import org.zero.cloud.client.service.IOrderMasterService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hiyzx
 * @since 2020-01-04
 */
@RestController
@RequestMapping("/order")
public class OrderMasterController {

    @Autowired
    private IOrderMasterService orderService;
    @Autowired
    private StockClient stockClient;

    @GetMapping("/buy")
    public String buy(@RequestParam Integer stockId, @RequestParam Integer count) {
        OrderMaster order = new OrderMaster();
        order.setStockId(stockId);
        order.setBuyCount(count);
        orderService.save(order);
        stockClient.reduceCount(stockId, count);
        return "success";
    }
}

