package com.zero.web.controller;

import com.zero.service.HealthCheckService;
import com.zero.vo.HealthCheckVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 健康检查的控制器
 *
 * @author yezhaoxing
 * @date : 2017/5/17
 */
@RestController
public class HealthCheckController {

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

    @Resource
    private HealthCheckService healthCheckService;

    @GetMapping(value = "version")
    @ApiOperation(value = "查看版本信息")
    public Map<String, String> version() throws ParseException {
        Map<String, String> map = new HashMap<>();
        map.put("version", version);
        map.put("builtAt", DATE_FORMAT.get().format(new SimpleDateFormat(format).parse(builtAt)));
        return map;
    }

    @GetMapping(value = "/healthCheck")
    @ApiOperation(value = "检查DB,第三方服务等是否能正常连接")
    public List<HealthCheckVo> healthCheck() {
        return healthCheckService.healthCheck();
    }

}
