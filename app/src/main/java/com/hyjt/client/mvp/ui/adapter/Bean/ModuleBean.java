package com.hyjt.client.mvp.ui.adapter.Bean;

/**
 * Created by Administrator on 2017/9/29.
 */

public class ModuleBean {

    private String img;
    private String name;
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
