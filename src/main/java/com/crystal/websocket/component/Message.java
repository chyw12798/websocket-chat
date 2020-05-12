package com.crystal.websocket.component;

import lombok.Builder;

/**
 * @Author chyw1
 * @Date 2020/5/12 10:08
 * 消息对象
 */
@Builder  // 建造者模式（具体看这个对象生成的样子）
public class Message {

    private String userId;
    private String message;

    public Message() {
    }

    public Message(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
