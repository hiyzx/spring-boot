package com.zero.service;

import com.zaxxer.hikari.HikariDataSource;
import com.zero.util.DateHelper;
import com.zero.util.RedisHelper;
import com.zero.vo.HealthCheckVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/7/18
 */
@Service
@Slf4j
public class HealthCheckService {

    @Resource
    private HikariDataSource masterDataSource;
    @Resource
    private RedisHelper<String, String> redisHelper;

    /**
     * 健康检查
     */
    public List<HealthCheckVo> healthCheck() {
        List<HealthCheckVo> healthCheckVos = new ArrayList<>();
        healthCheckVos.add(checkDBConnection());
        healthCheckVos.add(checkRedisConnection());
        return healthCheckVos;
    }

    /**
     * 检查数据库连接是否正常
     */
    private HealthCheckVo checkDBConnection() {
        String url = masterDataSource.getJdbcUrl();
        String driver = masterDataSource.getDriverClassName();
        String user = masterDataSource.getUsername();
        String password = masterDataSource.getPassword();
        Connection conn = null;
        Statement statement = null;
        HealthCheckVo model = new HealthCheckVo();
        model.setServiceName(url);
        try {
            long e = System.currentTimeMillis();
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();
            statement.executeQuery("select 1");
            model.setCostTime(String.valueOf(System.currentTimeMillis() - e));
            model.setNormal(true);
        } catch (Exception e) {
            log.error("[checkDB]发生异常", e);
            model.setNormal(false);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                log.error("[checkDB]关闭资源发生异常", e);
            }
        }
        return model;
    }

    public String checkRedis(String key){
        return redisHelper.get(key);
    }

    private HealthCheckVo checkRedisConnection() {
        HealthCheckVo healthCheckVo = new HealthCheckVo();
        healthCheckVo.setServiceName("redis");
        try {
            long startTimeMillis = System.currentTimeMillis();
            redisHelper.set(String.format("%scheckRedisConnection", ""),
                    DateHelper.format(new Date(startTimeMillis), "yyyy-MM-dd HH:mm:ss"));
            healthCheckVo.setNormal(true);
            healthCheckVo.setCostTime(String.format("%sms", System.currentTimeMillis() - startTimeMillis));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            healthCheckVo.setNormal(false);
        }
        return healthCheckVo;
    }
}
