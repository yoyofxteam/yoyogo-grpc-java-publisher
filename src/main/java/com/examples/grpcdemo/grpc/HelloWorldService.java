package com.examples.grpcdemo.grpc;

import com.examples.grpcdemo.helloworld.HelloReply;
import com.examples.grpcdemo.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import com.examples.grpcdemo.helloworld.GreeterGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@GRpcService
public class HelloWorldService extends GreeterGrpc.GreeterImplBase {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(com.examples.grpcdemo.grpc.HelloWorldService.class);


    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        LOGGER.info("server received {}", request);
        String message = "Java gRpc服务的响应为： Hello " + request.getName() + "!";
        HelloReply greeting =
                HelloReply.newBuilder().setMessage(message).build();
        LOGGER.info("server responded {}", greeting);
        responseObserver.onNext(greeting);
        responseObserver.onCompleted();
    }
}
