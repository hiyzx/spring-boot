package org.zero.swagger.start;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yezhaoxing
 * @date 2019/10/9
 */
@SpringBootApplication
@EnableSwagger2Doc
public class SwaggerStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerStartApplication.class, args);
    }
}
