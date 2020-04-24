package org.zero.grpc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 水寒
 * @date 2020/4/24
 */
@SpringBootApplication
public class GrpcServerApplication {

	public static void main(String[] args){
		SpringApplication.run(GrpcServerApplication.class, args);
	}
}
