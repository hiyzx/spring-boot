package org.zero.mybatis.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DataSourceAOP {

    @Before("(@annotation(org.zero.mybatis.annotation.Master))")
    public void setWriteDataSourceType() {
        DynamicDataSource.master();
        log.info("dataSource切换到：master");
    }

    @Before("(@annotation(org.zero.mybatis.annotation.Slave))")
    public void setReadDataSourceType() {
        DynamicDataSource.slave();
        log.info("dataSource切换到：slave");
    }
}