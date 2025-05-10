package com.LinYu.consumer;


import com.LinYu.common.model.User;
import com.LinYu.common.service.UserService;
import com.LinYu.linrpc.config.RpcConfig;
import com.LinYu.linrpc.proxy.ServiceProxyFactory;
import com.LinYu.linrpc.utils.ConfigUtils;

/**
 * 简易服务消费者示例
 */
public class Consumer {

    public static void main(String[] args) {

        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);

        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("linyu");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}
