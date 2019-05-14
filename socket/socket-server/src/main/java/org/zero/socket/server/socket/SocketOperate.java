package org.zero.socket.server.socket;

import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
@Slf4j
public class SocketOperate {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static final String ENCODING_UTF_8 = "UTF-8";

    public void operate(Socket socket) throws IOException {
        executorService.execute(new SocketOperateThread(socket));
    }

    private class SocketOperateThread implements Runnable {

        private Socket socket;
        //该线程所处理的Socket所对应的输入流
        private BufferedReader br = null;
        private InputStreamReader reader = null;
        private OutputStream os = null;

        SocketOperateThread(Socket socket) throws IOException {
            this.socket = socket;
            reader = new InputStreamReader(this.socket.getInputStream(), ENCODING_UTF_8);
            br = new BufferedReader(reader);
            os = socket.getOutputStream();
        }

        @Override
        public void run() {
            try {
                // 采用循环不断从Socket中读取客户端发送过来的数据
                StringBuilder content = new StringBuilder();
                String tmp;
                while ((tmp = br.readLine()) != null) {
                    content.append(tmp);
                }
                log.info("请求报文: {}", content.toString());
                String resp = handleRequest(content.toString());
                log.info("返回报文: {}", resp);
                os.write(resp.getBytes(ENCODING_UTF_8));
                os.flush();
            } catch (IOException e) {
                log.info(e.getMessage(), e);
            } finally {
                IoUtil.close(br);
                IoUtil.close(reader);
                IoUtil.close(os);
                IoUtil.close(socket);
            }
        }

        private String handleRequest(String content) {
            // 调用业务逻辑处理请求
            return content;
        }
    }
}