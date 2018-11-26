package org.zero.socket.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SocketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocketServerApplication.class, args);
    }
}
