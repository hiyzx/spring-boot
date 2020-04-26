package org.zero.grpc.client;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.grpc.api.HelloRequest;
import org.zero.grpc.api.MyServiceGrpc;

/**
 * @author 水寒
 * @date 2020/4/26
 */
@RestController
public class HelloController {

	@GrpcClient("MyService")
	private MyServiceGrpc.MyServiceBlockingStub myServiceBlockingStub;

	@GetMapping("/hello")
	public String hello(){
		HelloRequest request = HelloRequest.newBuilder().setName("shuihan").build();
		return myServiceBlockingStub.sayHello(request).getMessage();
	}
}
