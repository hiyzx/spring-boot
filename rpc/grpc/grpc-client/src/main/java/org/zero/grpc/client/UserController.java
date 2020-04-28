package org.zero.grpc.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zero.grpc.api.*;

import com.google.protobuf.Int32Value;

import net.devh.boot.grpc.client.inject.GrpcClient;

/**
 * @author 水寒
 * @date 2020/4/26
 */
@RestController
public class UserController {

    @GrpcClient("UserService")
    private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    @GetMapping("/user/create")
    public Integer hello(@RequestParam String name, @RequestParam int age) {
        UserCreateReq request = UserCreateReq.newBuilder().setName(name).setAge(age).build();
        return userServiceBlockingStub.create(request).getValue();
    }

    @GetMapping("/user/get")
    public String hello(@RequestParam Integer id) {
        UserGetResp userGetResp = userServiceBlockingStub.get(Int32Value.of(id));
        System.out.println(userGetResp.toBuilder().toString());
        // UserGetResp不能直接返回,在JSON序列化会报错.
        return userGetResp.getName();
    }
}
