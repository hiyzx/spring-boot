package org.zero.grpc.server;

import com.google.protobuf.Int32Value;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.zero.grpc.api.UserCreateReq;
import org.zero.grpc.api.UserGetResp;
import org.zero.grpc.api.UserServiceGrpc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 水寒
 * @date 2020/4/28
 */
@GrpcService
public class UserServer extends UserServiceGrpc.UserServiceImplBase {

    private Map<Integer, UserGetResp> userMap = new ConcurrentHashMap<>();
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void create(UserCreateReq request, StreamObserver<Int32Value> responseObserver) {
        UserGetResp userGetResp = UserGetResp.newBuilder().setName(request.getName()).setAge(request.getAge()).build();
        int id = atomicInteger.incrementAndGet();
        userMap.put(id, userGetResp);
        responseObserver.onNext(Int32Value.of(id));
        responseObserver.onCompleted();
    }

    @Override
    public void get(Int32Value request, StreamObserver<UserGetResp> responseObserver) {
        if (request == null || request == Int32Value.getDefaultInstance()) {
            responseObserver.onError(new StatusRuntimeException(Status.INTERNAL));
        } else {
            UserGetResp userGetResp = userMap.get(request.getValue());
	        userGetResp = userGetResp.toBuilder().setId(request.getValue()).build();
	        responseObserver.onNext(userGetResp);
        }
	    responseObserver.onCompleted();
    }
}
