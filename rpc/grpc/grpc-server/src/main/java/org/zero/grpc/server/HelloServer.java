package org.zero.grpc.server;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.zero.grpc.api.HelloServiceGrpc;
import org.zero.grpc.api.HelloServiceProto;

/**
 * @author 水寒
 * @date 2020/4/24
 */
@GrpcService
public class HelloServer extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void sayHello(HelloServiceProto.HelloRequest request, StreamObserver<HelloServiceProto.HelloResponse> responseObserver) {
        String name = request.getName();
        System.out.println("接收请求,name=" + name);
	    HelloServiceProto.HelloResponse response = HelloServiceProto.HelloResponse.newBuilder().setReply("hello " + name).build();
	    responseObserver.onNext(response);
	    responseObserver.onCompleted();
    }
}
