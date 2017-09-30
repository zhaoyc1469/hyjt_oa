package com.hyjt.client.mvp.model.entity.Bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

//@Entity
public class ModuleBean {

//    @Id
    private String name;

    private String img;
    private Integer Msg;



    public ModuleBean(String img, String name) {
        this.img = img;
        this.name = name;
    }

    public ModuleBean(String img, String name, Integer msg) {
        this.img = img;
        this.name = name;
        Msg = msg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMsg() {
        return Msg;
    }

    public void setMsg(Integer msg) {
        Msg = msg;
    }
}
