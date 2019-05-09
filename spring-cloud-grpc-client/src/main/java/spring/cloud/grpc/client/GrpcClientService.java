package spring.cloud.grpc.client;

import cn.springcloud.grpc.lib.HelloReply;
import cn.springcloud.grpc.lib.HelloRequest;
import cn.springcloud.grpc.lib.SimpleGrpc;
import com.google.protobuf.Descriptors;
import io.grpc.Channel;

import io.grpc.protobuf.ProtoFileDescriptorSupplier;
import io.grpc.protobuf.ProtoMethodDescriptorSupplier;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.springframework.stereotype.Service;
/**
 * emil:miles02@613.com
 * Created by forezp on 2018/8/11.
 */

@Service
public class GrpcClientService {

    /**
     * 与名为spring-cloud-grpc-server的服务端建立连接通道
     */
    @GrpcClient("spring-cloud-grpc-server")
    private Channel serverChannel;

    public String sendMessage(String name) {
        SimpleGrpc.SimpleBlockingStub stub = SimpleGrpc.newBlockingStub(serverChannel);
        HelloReply response = stub.sayHello(HelloRequest.newBuilder().setName(name).build());
        return response.getMessage();
    }
}
