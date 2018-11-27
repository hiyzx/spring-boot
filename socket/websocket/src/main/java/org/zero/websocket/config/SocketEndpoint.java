package org.zero.websocket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.zero.websocket.dto.SessionPair;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * WebSocket模拟聊天室
 * 1.http服务请求使用ws协议，https服务使用wss协议
 * 2.WebSocket连接默认一分钟断连，需要有效时间内发送心跳保持活跃
 * 3.ios息屏时连接会断开，需要主动重连
 */
@ServerEndpoint(value = "/ws/{taskId}/{type}/{name}")
@Component
public class SocketEndpoint {

    private static Logger logger = LoggerFactory.getLogger(SocketEndpoint.class);

    private static int onlineCount = 0;
    private Session session;
    private static CopyOnWriteArrayList<SessionPair> sessionPairs = new CopyOnWriteArrayList<>();
    private static Map<String, String> SESSION_USER_MAP = new HashMap<>();

    /**
     * 连接打开时调用
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "taskId") String taskId,
            @PathParam(value = "name") String name) {
        this.session = session;
        String uuid = getUUID32();
        logger.info("taskId: {} name: {}连接上来了", taskId, name);
        sessionPairs.add(new SessionPair(session, taskId, name, uuid));
        addOnlineCount();
        logger.info("有新连接加入！当前在线人数为" + getOnlineCount());
        SESSION_USER_MAP.put(session.getId(), name);
        logger.info("s-a|linkSuccess|{}|{}|{}连接成功", taskId, uuid, name);
        try {

            String msg = String.format("%s 连接成功,当前群聊有: %s", name, String.join(",", SESSION_USER_MAP.values()));
            sendMessage(session, msg);
            sendMessageToOther(session, msg);
        } catch (IOException e) {
            logger.info("IO异常", e);
        }
    }

    /**
     * 连接关闭调用
     */
    @OnClose
    public void onClose() {
        String closeName = SESSION_USER_MAP.get(this.session.getId());
        try {
            deleteSessionPair(this.session);
        } catch (Exception ex) {
            logger.info(ex.getMessage());
        }
        sendMessageToOther(null, String.format("%s 断开连接", closeName));
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     *            客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        String username = SESSION_USER_MAP.get(session.getId());
        logger.info("来自客户端用户{} 的消息:{}", username, message);
        String msg = String.format("%s发送消息:%s", username, message);
        sendMessageToOther(session, msg);
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        SessionPair sessionPair = getThisPairSession(session);
        logger.info("发生错误:对象为" + sessionPair.getTaskId() + "|" + sessionPair.getUuid() + "|" + sessionPair.getType());
        error.printStackTrace();
    }

    private void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    private void sendMessageToOther(Session session, String message) {
        // 根据得到的消息发送 组装消息
        List<SessionPair> otherSessionPairs = Collections.EMPTY_LIST;
        if (session == null) {
            otherSessionPairs = sessionPairs;
        } else {
            SessionPair thisPairSession = getThisPairSession(session);
            if (thisPairSession != null) {
                otherSessionPairs = getOtherPairSession(session);
            }
        }
        if (otherSessionPairs.size() > 0) {
            try {
                for (SessionPair sessionPair : otherSessionPairs) {
                    sendMessage(sessionPair.getSession(), message);
                }
            } catch (IOException e) {
                logger.error("发送消息异常" + e);
            }
        } else {
            logger.info("对方session未建立，暂存消息。");
        }
    }

    private static int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        onlineCount--;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * 查找session的sessionPair
     */
    private SessionPair getThisPairSession(Session session) {
        for (SessionPair sessionPair : sessionPairs) {
            if (session == sessionPair.getSession()) {
                return sessionPair;
            }
        }
        return null;
    }

    /**
     * 根据session获取另一方的sessionPair
     */
    private List<SessionPair> getOtherPairSession(Session session) {
        String taskId = null;
        for (SessionPair sessionPair : sessionPairs) {
            if (session == sessionPair.getSession()) {
                taskId = sessionPair.getTaskId();
                break;
            }
        }
        ArrayList<SessionPair> otherList = new ArrayList<>();
        for (SessionPair sessionPair : sessionPairs) {
            if (taskId.equals(sessionPair.getTaskId()) && sessionPair.getSession() != session) {
                otherList.add(sessionPair);
            }
        }
        return otherList;
    }

    /**
     * 如果session关闭连接，则关闭双方的连接
     */
    private void deleteSessionPair(Session session) {
        try {
            SESSION_USER_MAP.remove(session.getId());
            SessionPair thisSessionPair = getThisPairSession(session);
            sessionPairs.remove(thisSessionPair);
            session.close();
            subOnlineCount();
           /* List<SessionPair> otherSessionPairs = getOtherPairSession(session);
            for (SessionPair sessionPair : otherSessionPairs) {
                sessionPairs.remove(sessionPair);
                sessionPair.getSession().close();
                subOnlineCount();
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getUUID32() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

}
