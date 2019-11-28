package org.zero.elastic.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zero.elastic.search.dto.Customer;
import org.zero.elastic.search.repository.CustomerService;
import org.zero.elastic.search.util.PageResultVO;

/**
 * @author yezhaoxing
 * @since 2018/11/05
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("save")
    public String save(@RequestBody Customer customer) {
        customerService.saveOrUpdate(customer);
        return "success";
    }

    @PostMapping("delete")
    public String delete(@RequestParam long id) {
        return "success";
    }

    @PostMapping("update")
    public String update(@RequestParam long id, @RequestParam String name, @RequestParam String description) {
        return "success";
    }

    @GetMapping("getGoodsList")
    public PageResultVO<Customer> getList(@RequestParam Integer current, @RequestParam Integer pageSize,
        @RequestParam(required = false) String gender, @RequestParam(required = false) String address) {
        return customerService.search(current, pageSize, gender, address);
    }
}
