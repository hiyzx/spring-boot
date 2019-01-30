package org.zero.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zero.enums.CodeEnum;
import org.zero.vo.BaseReturnVo;
import org.zero.vo.ReturnVo;
import org.zero.web.exception.BaseException;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @date 2017/08/18
 */
@RestController
@Api(description = "helloWorld接口")
public class WebController {

    private static final ThreadLocal<DateFormat> DATE_FORMAT = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    @Value("${project.version}")
    private String version;
    @Value("${project.buildTime}")
    private String builtAt;
    @Value("${project.format}")
    private String format;

    @GetMapping(value = "version")
    @ApiOperation(value = "查看版本信息")
    public Map<String, String> version() throws ParseException {
        Map<String, String> map = new HashMap<>();
        map.put("version", version);
        map.put("builtAt", DATE_FORMAT.get().format(new SimpleDateFormat(format).parse(builtAt)));
        return map;
    }

    @PostMapping(value = "/helloWorld")
    @ApiOperation("helloWorld")
    public ReturnVo<String> logout() {
        return ReturnVo.success("helloWorld");
    }

    @GetMapping(value = "/hsj/fetchData.do")
    public Map<String, String> test(HttpServletRequest request, @RequestParam String companyName,
            @RequestParam String type) {
        String localAddr = request.getLocalAddr();
        int localPort = request.getLocalPort();
        String contextPath = request.getContextPath();
        String sessionId = request.getSession().getId();
        Map<String, String> map = new HashMap<>();
        map.put("code", "0");
        return map;
    }

    @GetMapping(value = "/exception")
    public BaseReturnVo exception() throws BaseException {
        if (System.currentTimeMillis() % 2 == 0) {
            throw new BaseException(CodeEnum.TEST_FAIL, "测试异常");
        } else {
            return BaseReturnVo.success();
        }
    }
}
