package com.crystal.websocket.server;

import com.crystal.websocket.component.Message;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author chyw1
 * @Date 2020/5/12 10:14
 */
@ServerEndpoint("/test-one")
@Component
@Slf4j
public class MyOneToOneServer {

    /**
     * 用于存放所有在线客户端
     **/
    private static Map<String, Session> clients = new ConcurrentHashMap<>(); // ConcurrentHashMap细节

    private Gson gson = new Gson();

    @OnOpen
    public void onOpen(Session session){
        log.info("有新的客户端上线:{}",session.getId());
        clients.put(session.getId(), session);
    }

    @OnClose
    public void onClose(Session session){
        String sessionId = session.getId();
        log.info("有客户端离线:{}", sessionId);
        clients.remove(sessionId);
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        throwable.printStackTrace();
        if(clients.get(session.getId()) != null){
            clients.remove(session.getId());
        }
    }

    @OnMessage
    public void onMessage(Session session,String message) {
        log.info("当前SessionId:{},收到客户端发来的消息:{}",session.getId(),message);
//        this.sendTo(session.getId(),gson.fromJson(message,Message.class));
        this.sendAll(session.getId(),message);
    }

    public void sendTo(String sessionId,Message message){
        Session s = clients.get(message.getUserId());
        if (s != null){
            try {
                s.getBasicRemote().sendText(sessionId+"发给你的消息："+message.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendAll(String sessionId,String message){
        for(Map.Entry<String,Session> sessionEntry : clients.entrySet()){
            if (!sessionEntry.getValue().getId().equals(sessionId)){
                sessionEntry.getValue().getAsyncRemote().sendText(sessionId+"的群发消息："+message);
            } else {
                sessionEntry.getValue().getAsyncRemote().sendText("我的群发消息："+message);
            }
        }
    }

}
