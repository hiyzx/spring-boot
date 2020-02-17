package org.zero.web.controller;

import cn.hutool.core.io.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zero.enums.StringEnum;
import org.zero.starter.service.ExampleService;
import org.zero.vo.BaseReturnVo;
import org.zero.vo.ReturnVo;
import org.zero.vo.TestVo;
import org.zero.web.exception.BaseException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

/**
 * @author yezhaoxing
 * @date 2017/08/18
 */
@RestController
@Api(description = "helloWorld接口")
@Slf4j
public class WebController {

    @Autowired
    private Executor executorService;

    @GetMapping(value = "/helloWorld")
    @ApiOperation("helloWorld")
    public ReturnVo<List<String>> hello() {
        List<String> rtn = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            rtn.add("hello " + i);
        }
        return ReturnVo.success(rtn);
    }

    @GetMapping(value = "/exception")
    public BaseReturnVo exception() throws BaseException {
        if (System.currentTimeMillis() % 2 == 0) {
            throw new BaseException(StringEnum.TEST_FAIL, "测试异常");
        } else {
            return BaseReturnVo.success();
        }
    }

    @Autowired
    private ExampleService exampleService;

    @GetMapping(value = "/starter")
    public ReturnVo<String> starter(@RequestParam String hello) {
        return ReturnVo.success(exampleService.wrap("hello"));
    }

    /*@GetMapping(value = "/updateVersion")
    public ReturnVo<String> update(@RequestParam String version) {
        this.version = "100";
        return ReturnVo.success("success");
    }
    */
    @PostMapping(value = "/return")
    public ReturnVo<String> returnHello(HttpServletRequest request, BaseReturnVo baseReturnVo) {
        String queryString = request.getQueryString();
        Locale locale = LocaleContextHolder.getLocale();
        return ReturnVo.success("success");
    }

    @GetMapping(value = "/returnGet")
    public ReturnVo<String> returnHello(TestVo testVo) {
        log.info("{}", testVo);
        return ReturnVo.success("成功");
    }

    @PostMapping(value = "/interface/orderDetail")
    public String stock(@RequestParam String data) {
        return "{\"retCode\":\"0\",\"retMsg\":\"\",\"order\":{\"orderNo\":\"40598638532678385\",\"total\":\"29.86\",\"createTime\":\"2019-07-22 15:54:32.0\",\"status\":\"已支付\",\"packages\":[{\"packageNo\":\"SXB20116088AU\",\"status\":\"已出库\",\"fastMail\":\"EWE\",\"fastUrl\":\"https://www.ewe.com.au/track\",\"weight\":\"1.28\",\"photo\":\"https://static.aozhouxiaopu.cn/static/shop/images/logo.png\",\"goods\":[{\"id\":\"4330\",\"name\":\"爱他美婴儿奶粉金装1段\",\"number\":\"1\"}]}]}}";
    }

    @GetMapping("async")
    public String async() {
        executorService.execute(() -> {
            try {
                Thread.sleep(10000);
                log.info("thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return "success";
    }

    @GetMapping("/doc")
    @CrossOrigin
    public String doc() {
        return FileUtil.readString("C:\\Users\\GVT\\Desktop\\apidoc.txt", "UTF-8");
    }

    @PostMapping("/upload")
    public String upload(@RequestParam String name, MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        return "success";
    }

    @GetMapping("/long")
    public ReturnVo<Long> longTest() {
        return ReturnVo.success(2173691766230835999L);
    }

    @GetMapping("/enumTest")
    public String enumTest(StringEnum codeEnum){
        return codeEnum.name();
    }

    @GetMapping("/voidTest")
    public void voidTest(){
        System.out.println(123);
    }
}
