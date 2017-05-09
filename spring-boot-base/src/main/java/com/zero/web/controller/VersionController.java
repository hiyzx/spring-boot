package com.zero.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class VersionController {
    private static final ThreadLocal<DateFormat> DATE_FORMAT = new ThreadLocal<DateFormat>() {
        public DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        };
    };
    @Value("${project.version}")
    private String version;
    @Value("${project.buildTime}")
    private String builtAt;
    @Value("${project.format}")
    private String format;

    @ResponseBody
    @RequestMapping(value = "/version", method = GET)
    @ApiOperation(value = "查看版本信息")
    public Map<String, String> version() throws ParseException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("version", version);
        map.put("builtAt", DATE_FORMAT.get().format(new SimpleDateFormat(format).parse(builtAt)));
        return map;
    }
}
