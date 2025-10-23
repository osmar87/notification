package com.notification.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "notificacao")
public class Notification {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private  UUID id;
 
    private String userId;

    private String msg;

    private boolean  isVisto;

    public Notification() {
    }
    
    public Notification(String userId, String msg) {
        this.userId = userId;
        this.msg = msg;
    }


    

    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }


    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }


    public boolean isVisto() {
        return isVisto;
    }


    public void setVisto(boolean isVisto) {
        this.isVisto = isVisto;
    }


    @Override
    public String toString() {
        return "Notification [id=" + id + ", userId=" + userId + ", msg=" + msg + ", isVisto=" + isVisto + "]";
    }
}
