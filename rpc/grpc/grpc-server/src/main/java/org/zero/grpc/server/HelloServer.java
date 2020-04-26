package org.zero.grpc.server;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.zero.grpc.api.HelloReply;
import org.zero.grpc.api.HelloRequest;
import org.zero.grpc.api.MyServiceGrpc;

/**
 * @author 水寒
 * @date 2020/4/24
 */
@GrpcService
public class HelloServer extends MyServiceGrpc.MyServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        String name = request.getName();
        System.out.println("接收请求,name=" + name);
        HelloReply response = HelloReply.newBuilder().setMessage("hello " + name).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
