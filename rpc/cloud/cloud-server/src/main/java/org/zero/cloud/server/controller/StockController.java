package org.zero.cloud.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zero.cloud.server.service.IStockService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hiyzx
 * @since 2020-01-04
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private IStockService stockService;

    @GetMapping("/reduceCount")
    public void reduceCount(@RequestParam Integer stockId, @RequestParam Integer count) {
        stockService.reduceCount(stockId, count);
    }

}

