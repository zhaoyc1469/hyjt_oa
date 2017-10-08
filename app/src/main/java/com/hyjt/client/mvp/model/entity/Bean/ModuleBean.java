package com.hyjt.client.mvp.model.entity.Bean;



public class ModuleBean {

    private String name;

    private String img;
    private Integer Msg;
    private Integer clickId;
    private boolean show_del;

    public boolean isShow_del() {
        return show_del;
    }

    public void setShow_del(boolean show_del) {
        this.show_del = show_del;
    }

    public ModuleBean(String img, String name, Integer msg, Integer clickId, boolean show_del) {
        this.img = img;
        this.name = name;
        Msg = msg;
        this.clickId = clickId;
        this.show_del = show_del;
    }

    public ModuleBean(String img, String name) {
        this.img = img;
        this.name = name;
    }

    public ModuleBean(String img, String name, Integer msg) {
        this.img = img;
        this.name = name;
        Msg = msg;
    }

    public ModuleBean(String img, String name, Integer msg, Integer clickId) {
        this.img = img;
        this.name = name;
        Msg = msg;
        this.clickId = clickId;
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

    public Integer getClickId() {
        return clickId;
    }

    public void setClickId(Integer clickId) {
        this.clickId = clickId;
    }
}
