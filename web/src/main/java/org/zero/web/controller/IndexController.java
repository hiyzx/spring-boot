package org.zero.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yezhaoxing
 * @since 2018/10/09
 */
@Controller
public class IndexController {

    @Controller
    public class HelloController {

        @RequestMapping("/")
        public String index(ModelMap map) {
            map.addAttribute("user", "zero");
            return "index";
        }

    }
}
