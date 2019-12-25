package org.zero.snowflake.config;

import org.springframework.beans.factory.annotation.Autowired;

public class Snowflake {
    // ==============================Fields===========================================
    /**
     * 开始时间截 (2015-01-01=1420041600000L) 开始时间截 (2018-01-01=1514736000000L) 开始时间截 (2018-02-01=1517414400000L) 开始时间截
     * (2019-10-01=1569859200000L)
     */
    private final long twepoch = 1569859200000L;

    /**
     * 服务id占用的位数
     */
    private final long serviceIdBits = 8L;
    /**
     * 实例id占用位数
     */
    private final long instanceIdBits = 4L;

    /** 机器id所占的位数 */
    private final long workerIdBits = serviceIdBits + instanceIdBits;

    /** 数据标识id所占的位数 */
    private final long datacenterIdBits = 3L;

    /** 序列在id中占的位数 */
    private final long sequenceBits = 7L;

    /** 支持的最大机器id，结果是4095 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private final long maxWorkerId = ~(-1L << workerIdBits);

    /** 支持的最大数据中心标识id，结果是15 */
    private final long maxDatacenterId = ~(-1L << datacenterIdBits);
    /** 生成序列的掩码，这里为127 (0b111111111111=0xfff=4095) */
    private final long sequenceMask = ~(-1L << sequenceBits);

    /** 机器ID向左移12位 */
    private final long workerIdShift = sequenceBits;

    /** 数据标识id向左移17位(12+5) */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /** 时间截向左移22位(5+5+12) */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /** 工作机器ID(0~4095) */
    private long workerId;

    /** 数据中心ID(0~15) */
    private long datacenterId;

    /** 毫秒内序列(0~127) */
    private long sequence = 0L;

    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;

    private boolean isInit = false;
    @Autowired
    private SnowflakeProperties snowFlakeConfig;

    public void initParam() {
        if (!isInit) {
            Integer serviceId = snowFlakeConfig.getServiceId();
            Integer instanceId = snowFlakeConfig.getInstanceId();
            Integer dataCenterId = snowFlakeConfig.getDataCenter();
            if (workerId > maxWorkerId || workerId < 0) {
                throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
            }
            if (dataCenterId > maxDatacenterId || dataCenterId < 0) {
                throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
            }
            // 由8位服务标识和4位实例标识组成； 8位服务标识往左挪动4位|4位的instanceId
            this.workerId = serviceId << instanceIdBits | instanceId;
            this.datacenterId = dataCenterId;
            isInit = true;
        }
    }

    /**
     * 获得下一个ID
     * 
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        initParam();
        long timestamp = timeGen();

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        // 时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        // 上次生成ID的时间截
        lastTimestamp = timestamp;

        // 移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) //
            | (datacenterId << datacenterIdShift) //
            | (workerId << workerIdShift) //
            | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * 
     * @param lastTimestamp
     *            上次生成ID的时间截
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * 
     * @return 当前时间(毫秒)
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

}
