package org.zero.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zero.mybatis.properties.MasterProperties;
import org.zero.mybatis.properties.SlaveProperties;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @since 2018/11/7
 * @description datasource配置类
 */
@Configuration
public class DataBaseConfiguration {

    @Resource
    private MasterProperties masterProperties;
    @Resource
    private SlaveProperties slaveProperties;

    @Bean
    public DynamicDataSource dynamicDataSource() {
        DataSource master = master();
        DataSource slave = slave();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicDataSource.DatabaseType.Master, master);
        targetDataSources.put(DynamicDataSource.DatabaseType.Slave, slave);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(master);
        return dataSource;
    }

    private DataSource master() {
        System.out.println("注入Master druid！！！");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(masterProperties.getUrl());
        datasource.setDriverClassName(masterProperties.getDriverClassName());
        datasource.setUsername(masterProperties.getUsername());
        datasource.setPassword(masterProperties.getPassword());
       /* datasource.setInitialSize(Integer.valueOf(propertyResolver1.getProperty("initial-size")));
        datasource.setMinIdle(Integer.valueOf(propertyResolver1.getProperty("min-idle")));
        datasource.setMaxWait(Long.valueOf(propertyResolver1.getProperty("max-wait")));
        datasource.setMaxActive(Integer.valueOf(propertyResolver1.getProperty("max-active")));
        datasource.setMinEvictableIdleTimeMillis(
                Long.valueOf(propertyResolver1.getProperty("min-evictable-idle-time-millis")));*/
        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }

    private DataSource slave() {
        System.out.println("Slave druid！！！");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(slaveProperties.getUrl());
        datasource.setDriverClassName(slaveProperties.getDriverClassName());
        datasource.setUsername(slaveProperties.getUsername());
        datasource.setPassword(slaveProperties.getPassword());
        /*datasource.setInitialSize(Integer.valueOf(propertyResolver2.getProperty("initial-size")));
        datasource.setMinIdle(Integer.valueOf(propertyResolver2.getProperty("min-idle")));
        datasource.setMaxWait(Long.valueOf(propertyResolver2.getProperty("max-wait")));
        datasource.setMaxActive(Integer.valueOf(propertyResolver2.getProperty("max-active")));
        datasource.setMinEvictableIdleTimeMillis(
                Long.valueOf(propertyResolver2.getProperty("min-evictable-idle-time-millis")));*/
        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }
}