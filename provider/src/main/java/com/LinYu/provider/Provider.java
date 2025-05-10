package com.LinYu.provider;


import com.LinYu.common.service.UserService;
import com.LinYu.linrpc.RpcApplication;
import com.LinYu.linrpc.config.RegistryConfig;
import com.LinYu.linrpc.config.RpcConfig;
import com.LinYu.linrpc.model.ServiceMetaInfo;
import com.LinYu.linrpc.registry.LocalRegistry;
import com.LinYu.linrpc.registry.Registry;
import com.LinYu.linrpc.registry.RegistryFactory;
import com.LinYu.linrpc.server.HttpServer;
import com.LinYu.linrpc.server.VertxHttpServer;
import com.LinYu.linrpc.server.tcp.VertxTcpServer;

/**
 * 简易服务提供者示例
 */
public class Provider {

    public static void main(String[] args) {
        // RPC框架初始化
        RpcApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        // 启动 TCP 服务
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(8081);
    }
}
