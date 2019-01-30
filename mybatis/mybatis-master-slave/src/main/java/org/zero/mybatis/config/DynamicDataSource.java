package org.zero.mybatis.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author yezhaoxing
 * @since 2018/11/7
 * @description 动态数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        return contextHolder.get();
    }

    public enum DatabaseType {
        Master, Slave
    }

    public static void master() {
        contextHolder.set(DatabaseType.Master);
    }


    public static void slave() {
        contextHolder.set(DatabaseType.Slave);
    }

    public static void clear() {
        contextHolder.remove();
    }
}