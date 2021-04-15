package com.examples.grpcdemo;

import com.examples.grpcdemo.grpc.HelloWorldClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class GrpcdemoApplicationTests {

    @Autowired
    HelloWorldClient grpcClient;

    @Test
    void contextLoads() {
    }

    @Test
    void TestGrpcCall() {
        String res = grpcClient.SayHello("my my my");
        Assert.notNull(res);

        grpcClient.SayList();
    }

}
