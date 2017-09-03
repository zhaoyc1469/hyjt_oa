package com.hyjt.home.mvp.model.entity.Reqs;

import java.io.Serializable;

/**
 * @author 赵宇驰
 * @time 2017/7/19  9:57
 * @desc ${TODO}
 */

public class StaffNameIdKey implements Serializable {

    private String Name;
    private String Id;
    private String SendKey;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSendKey() {
        return SendKey;
    }

    public void setSendKey(String sendKey) {
        SendKey = sendKey;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
