package com.examples.grpcdemo.grpc;

import com.examples.grpcdemo.helloworld.GreeterGrpc;
import com.examples.grpcdemo.helloworld.HelloReply;
import com.examples.grpcdemo.helloworld.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.Iterator;

@Component
public class HelloWorldClient {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(com.examples.grpcdemo.grpc.HelloWorldClient.class);

    private GreeterGrpc.GreeterBlockingStub helloWorldServiceBlockingStubForGo;

    @PostConstruct
    private void init() {
        //初始化一个连接Go grpc的客户端
        ManagedChannel managedChannelForGo = ManagedChannelBuilder
                .forAddress("127.0.0.1", 31127).usePlaintext().build();

        helloWorldServiceBlockingStubForGo =
                GreeterGrpc.newBlockingStub(managedChannelForGo);
    }

    public String SayHello(String name) {
        HelloRequest person = HelloRequest.newBuilder().setName(name).build();
        LOGGER.info("client sending {}", person);
        HelloReply greeting =
                helloWorldServiceBlockingStubForGo.sayHello(person);
        LOGGER.info("client received {}", greeting);

        String res = greeting.getMessage();

        return res;
    }

    public void SayList() {
        HelloRequest person = HelloRequest.newBuilder().setName("list").build();
        java.util.Iterator<HelloReply> dd = helloWorldServiceBlockingStubForGo.sayList(person);
        for (Iterator<HelloReply> it = dd; it.hasNext(); ) {
            HelloReply item = it.next();

            LOGGER.info("client received {}", item);
        }
    }




}
