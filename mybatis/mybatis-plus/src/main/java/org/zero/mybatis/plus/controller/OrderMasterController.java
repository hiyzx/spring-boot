package org.zero.mybatis.plus.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zero.mybatis.plus.entity.OrderMaster;
import org.zero.mybatis.plus.service.IOrderMaster2Service;
import org.zero.mybatis.plus.service.IOrderMasterService;

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

    @GetMapping("/buy")
    public String buy(@RequestParam Integer stockId, @RequestParam Integer count) {
        orderService.save(stockId, count);
        return "success";
    }
}

