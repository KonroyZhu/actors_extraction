package com.konroy.app.entity.actor;

public class Actor {
    public Actor(String nickName, String realName) {
        this.nickName = nickName;
        this.realName = realName;
    }

    public Actor() {
    }

    private String nickName;
    private String realName;

    @Override
    public String toString() {
        return "Actor{" +
                "nickName='" + nickName + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {

        return nickName;
    }

    public String getRealName() {
        return realName;
    }
}

