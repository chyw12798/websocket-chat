package com.crystal.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author chyw1
 * @Date 2020/5/12 10:04
 * WebSocket配置类
 */
@Configuration
public class WebSocketConfig {

    /**
     * 再来看服务端开发, java 定义了一套 javax.servlet-api, 一个 HttpServlet 就是一个 HTTP 服务。
     * java websocket 并非基于 servlet-api 简单扩展, 而是新定义了一套 javax.websocket-api。
     * 一个 websocket 服务对应一个 Endpoint。
     * 与 ServletContext 对应, websocket-api 也定义了 WebSocketContainer, 而编程方式注册 websocket 的接口是继承自 WebSocketContainer 的 ServerContainer。
     * 一个 websocket 可以接受并管理多个连接, 因此可被视作一个 server。
     * 主流 servlet 容器都支持 websocket, 如 tomcat, jetty 等。
     * 看 ServerContainer api 文档, 可从 ServletContext attribute 找到 ServerContainer。
     **/
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
