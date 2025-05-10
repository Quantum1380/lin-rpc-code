package com.LinYu;

import com.LinYu.linrpc.RpcApplication;
import com.LinYu.linrpc.serializer.Serializer;
import com.LinYu.linrpc.serializer.SerializerFactory;

import junit.framework.TestCase;

public class SystemTest extends TestCase {

    public void test() {
//        System.out.println(UserService.class.getName());
//        System.out.println(UserService.class.getName().getClass().toString());
//        System.out.println(UserServiceImpl.class);
//        System.out.println(UserServiceImpl.class.getClass().toString());

        // 指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());
        System.out.println(serializer);
        System.out.println(RpcApplication.getRpcConfig().getSerializer());
    }
}
