package org.zero.zookeeper;

import org.apache.zookeeper.*;

import java.util.List;

/**
 * @author yezhaoxing
 * @date 2019/5/12
 */
public class ZookeeperDemo {

	public static void main(String[] args) throws Exception {
		ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 2000, new Watcher() {
			@Override
			public void process(WatchedEvent watchedEvent) {
				System.out.println(watchedEvent.getPath());
				System.out.println(watchedEvent.getState());
				System.out.println("已经触发了" + watchedEvent.getType() + "事件！");
			}
		});

		// 创建一个目录节点
		String zNode = zk
				.create("/hello", "hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
		System.out.println(zNode);
		zNode = zk
				.create("/hello", "hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
		System.out.println(zNode);

		zk.create("/hello/hello1", "hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		List<String> children = zk.getChildren("/hello", new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				if(event.getType() == Event.EventType.NodeDataChanged){
					System.out.println("数据发生改变");
				}
			}
		});
		for (String child : children) {
			System.out.println(child);
		}
		System.in.read();
		/*// 创建一个子目录节点
		zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(),
				ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
		System.out.println(new String(zk.getData("/testRootPath",false,null)));
		// 取出子目录节点列表
		System.out.println(zk.getChildren("/testRootPath",true));
		// 修改子目录节点数据
		zk.setData("/testRootPath/testChildPathOne","modifyChildDataOne".getBytes(),-1);
		System.out.println("目录节点状态：["+zk.exists("/testRootPath",true)+"]");
		// 创建另外一个子目录节点
		zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(),
				ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
		System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo",true,null)));
		// 删除子目录节点
		zk.delete("/testRootPath/testChildPathTwo",-1);
		zk.delete("/testRootPath/testChildPathOne",-1);
		// 删除父目录节点
		zk.delete("/testRootPath",-1);*/
		// 关闭连接
		zk.close();
	}
}
