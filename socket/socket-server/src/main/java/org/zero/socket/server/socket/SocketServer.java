package org.zero.socket.server.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.ServerSocket;
import java.net.Socket;

@Component("socketServer")
@Slf4j
public class SocketServer {

    @Resource
    private SocketOperate socketOperate;

    public void startSocketServer() {
        new SocketServerThread().start();
    }

    private class SocketServerThread extends Thread {
        private ServerSocket serverSocket;

        SocketServerThread() {
            try {
                this.serverSocket = new ServerSocket(3334);
                log.info("socket start");
            } catch (Exception e) {
                log.info("创建socket服务异常");
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    if (serverSocket == null) {
                        break;
                    } else if (serverSocket.isClosed()) {
                        break;
                    }
                    Socket socket = serverSocket.accept();
                    if (null != socket && !socket.isClosed()) {
                        //处理接受的数据
                        socketOperate.operate(socket);
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    log.info("socket处理请求异常");
                }
            }
        }
    }

}

