package com.hyjt.db.bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ModuleBeanDb {
    @Id
    private String name;

    private int type;
    private boolean isShow;
    private String img;
    private int clickId;
    private Integer message_nub;
    public Integer getMessage_nub() {
        return this.message_nub;
    }
    public void setMessage_nub(Integer message_nub) {
        this.message_nub = message_nub;
    }
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(int type) {
        this.type = type;
    }
    public boolean getIsShow() {
        return this.isShow;
    }
    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }
    public int getType() {
        return this.type;
    }
    public int getClickId() {
        return this.clickId;
    }
    public void setClickId(int clickId) {
        this.clickId = clickId;
    }
    @Generated(hash = 637425569)
    public ModuleBeanDb(String name, int type, boolean isShow, String img,
            int clickId, Integer message_nub) {
        this.name = name;
        this.type = type;
        this.isShow = isShow;
        this.img = img;
        this.clickId = clickId;
        this.message_nub = message_nub;
    }
    @Generated(hash = 1073315082)
    public ModuleBeanDb() {
    }
}
