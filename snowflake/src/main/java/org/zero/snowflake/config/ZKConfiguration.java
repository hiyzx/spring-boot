package org.zero.snowflake.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Collections;
import java.util.List;

import static org.apache.zookeeper.Watcher.Event.EventType.NodeChildrenChanged;

/**
 * 使用zookeeper配置SnowFlake集群
 */
@Configuration
@Slf4j
public class ZKConfiguration implements ServletContextListener {

    @Autowired
    private SnowflakeProperties snowflakeProperties;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ZooKeeper zk = new ZooKeeper(snowflakeProperties.getZkUrl(), 60000, event -> {});

            // 创建目录
            String firstPath = "/snowflake";
            createPath(zk, firstPath);
            String secondPath = firstPath + "/" + snowflakeProperties.getServiceId();
            createPath(zk, secondPath);
            String nodePath = secondPath + "/" + snowflakeProperties.getDataCenter();
            createPath(zk, nodePath);

            // 创建临时节点
            String instancePath = zk.create(nodePath + "/instance_", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);

            // 获取所有的子节点,并进行排序
            List<String> childPaths = zk.getChildren(nodePath, event -> {
                if (NodeChildrenChanged == event.getType()) {
                    try {
                        List<String> childPaths1 = zk.getChildren(nodePath, false);
                        setInstance(childPaths1, instancePath);
                    } catch (Exception ex) {
                        log.error(ex.getMessage(), ex);
                    }
                }
            });
            setInstance(childPaths, instancePath);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    // 创建存储节点
    private void createPath(ZooKeeper zk, String nodePath) throws KeeperException, InterruptedException {
        if (zk.exists(nodePath, true) == null) {
            zk.create(nodePath, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    // 设置实例顺序
    private void setInstance(List<String> childPaths, String instancePath) {
        instancePath = instancePath.substring(instancePath.lastIndexOf("/") + 1);
        Collections.sort(childPaths);
        Integer instanceId = childPaths.indexOf(instancePath) + 1;
        snowflakeProperties.setInstanceId(instanceId);
    }
}