package com.panda.model;

import java.util.Date;

public class User {
    private String id;
    private Short status;
    private String nickname;
    private String password;
    private Date createTime;
    private Short tag;

    public User()
    {
        this.setCreateTime(new Date());
    }


    public User(String id,Short status,String nickname,String password)
    {
        this.id=id;
        this.status=status;
        this.password=password;
        this.nickname=nickname;
        this.setCreateTime(new Date());
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Short getStatus() {
        return status;
    }
    public void setStatus(Short status) {
        this.status = status;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public Short getTag() {
        return tag;
    }


    public void setTag(Short tag) {
        this.tag = tag;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
