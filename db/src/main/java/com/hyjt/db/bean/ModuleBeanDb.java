package com.hyjt.db.bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class ModuleBeanDb {

    @Id
    //模块名称
    private String name;

    @Property
    //所属展示
    private int type;
    //是否展示
    private boolean isShow;
    //是否展示删除
    private boolean showDel;

    //图标名称
    private String img;
    //点击事件
    private int clickId;
    //消息数量
    private Integer message_nub;
    //排序
    private Integer row_nub;
    public Integer getRow_nub() {
        return this.row_nub;
    }
    public void setRow_nub(Integer row_nub) {
        this.row_nub = row_nub;
    }
    public Integer getMessage_nub() {
        return this.message_nub;
    }
    public void setMessage_nub(Integer message_nub) {
        this.message_nub = message_nub;
    }
    public int getClickId() {
        return this.clickId;
    }
    public void setClickId(int clickId) {
        this.clickId = clickId;
    }
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public boolean getShowDel() {
        return this.showDel;
    }
    public void setShowDel(boolean showDel) {
        this.showDel = showDel;
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
    public void setType(int type) {
        this.type = type;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Generated(hash = 1043021727)
    public ModuleBeanDb(String name, int type, boolean isShow, boolean showDel,
            String img, int clickId, Integer message_nub, Integer row_nub) {
        this.name = name;
        this.type = type;
        this.isShow = isShow;
        this.showDel = showDel;
        this.img = img;
        this.clickId = clickId;
        this.message_nub = message_nub;
        this.row_nub = row_nub;
    }
    @Generated(hash = 1073315082)
    public ModuleBeanDb() {
    }
}
