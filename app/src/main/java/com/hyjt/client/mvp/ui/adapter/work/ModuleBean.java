package com.hyjt.client.mvp.ui.adapter.work;

/**
 * @author 赵宇驰
 * @time 2017/7/7  8:37
 * @desc ${TODO}
 */
public class ModuleBean {

    public static final int PARENT_ITEM = 0;//父布局
    public static final int CHILD_ITEM = 1;//子布局
    public static final int EMPTY_ITEM = 2;//空布局

    private int type;// 显示类型
    private boolean isExpand;// 是否展开
    private ModuleBean childBean;

    private String ID;

    private String Name;
    private String parentOneTxt;
    private String parentOneIv;
    private int parentOneID;
    private String parentTwoTxt;
    private String parentTwoIv;
    private int parentTwoID;
    private String parentThreeTxt;
    private String parentThreeIv;
    private int parentThreeID;
    private String parentFourTxt;
    private String parentFourIv;
    private int parentFourID;
    private String parentFiveTxt;
    private String parentFiveIv;
    private int parentFiveID;

    private String childOneTxt;
    private String childOneIv;
    private int childOneID;
    private String childTwoTxt;
    private String childTwoIv;
    private int childTwoID;
    private String childThreeTxt;
    private String childThreeIv;
    private int childThreeID;
    private String childFourTxt;
    private String childFourIv;
    private int childFourID;
    private String childFiveTxt;
    private String childFiveIv;
    private int childFiveID;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public String getParentOneIv() {
        return parentOneIv;
    }

    public void setParentOneIv(String parentOneIv) {
        this.parentOneIv = parentOneIv;
    }

    public String getParentTwoIv() {
        return parentTwoIv;
    }

    public void setParentTwoIv(String parentTwoIv) {
        this.parentTwoIv = parentTwoIv;
    }

    public String getParentThreeIv() {
        return parentThreeIv;
    }

    public void setParentThreeIv(String parentThreeIv) {
        this.parentThreeIv = parentThreeIv;
    }

    public String getParentFourIv() {
        return parentFourIv;
    }

    public void setParentFourIv(String parentFourIv) {
        this.parentFourIv = parentFourIv;
    }

    public String getParentFiveIv() {
        return parentFiveIv;
    }

    public void setParentFiveIv(String parentFiveIv) {
        this.parentFiveIv = parentFiveIv;
    }

    public String getChildTwoIv() {
        return childTwoIv;
    }

    public void setChildTwoIv(String childTwoIv) {
        this.childTwoIv = childTwoIv;
    }

    public String getChildThreeIv() {
        return childThreeIv;
    }

    public void setChildThreeIv(String childThreeIv) {
        this.childThreeIv = childThreeIv;
    }

    public String getChildFourIv() {
        return childFourIv;
    }

    public void setChildFourIv(String childFourIv) {
        this.childFourIv = childFourIv;
    }

    public String getChildFiveIv() {
        return childFiveIv;
    }

    public void setChildFiveIv(String childFiveIv) {
        this.childFiveIv = childFiveIv;
    }

    public String getChildOneIv() {
        return childOneIv;
    }

    public void setChildOneIv(String childOneIv) {
        this.childOneIv = childOneIv;
    }

    public String getParentOneTxt() {
        return parentOneTxt;
    }

    public void setParentOneTxt(String parentOneTxt) {
        this.parentOneTxt = parentOneTxt;
    }

    public String getParentTwoTxt() {
        return parentTwoTxt;
    }

    public void setParentTwoTxt(String parentTwoTxt) {
        this.parentTwoTxt = parentTwoTxt;
    }

    public String getParentThreeTxt() {
        return parentThreeTxt;
    }

    public void setParentThreeTxt(String parentThreeTxt) {
        this.parentThreeTxt = parentThreeTxt;
    }

    public String getParentFourTxt() {
        return parentFourTxt;
    }

    public void setParentFourTxt(String parentFourTxt) {
        this.parentFourTxt = parentFourTxt;
    }

    public String getParentFiveTxt() {
        return parentFiveTxt;
    }

    public void setParentFiveTxt(String parentFiveTxt) {
        this.parentFiveTxt = parentFiveTxt;
    }

    public String getChildOneTxt() {
        return childOneTxt;
    }

    public void setChildOneTxt(String childOneTxt) {
        this.childOneTxt = childOneTxt;
    }

    public String getChildTwoTxt() {
        return childTwoTxt;
    }

    public void setChildTwoTxt(String childTwoTxt) {
        this.childTwoTxt = childTwoTxt;
    }

    public String getChildThreeTxt() {
        return childThreeTxt;
    }

    public void setChildThreeTxt(String childThreeTxt) {
        this.childThreeTxt = childThreeTxt;
    }

    public String getChildFourTxt() {
        return childFourTxt;
    }

    public void setChildFourTxt(String childFourTxt) {
        this.childFourTxt = childFourTxt;
    }

    public String getChildFiveTxt() {
        return childFiveTxt;
    }

    public void setChildFiveTxt(String childFiveTxt) {
        this.childFiveTxt = childFiveTxt;
    }


    public int getParentOneID() {
        return parentOneID;
    }

    public void setParentOneID(int parentOneID) {
        this.parentOneID = parentOneID;
    }

    public int getParentTwoID() {
        return parentTwoID;
    }

    public void setParentTwoID(int parentTwoID) {
        this.parentTwoID = parentTwoID;
    }

    public int getParentThreeID() {
        return parentThreeID;
    }

    public void setParentThreeID(int parentThreeID) {
        this.parentThreeID = parentThreeID;
    }

    public int getParentFourID() {
        return parentFourID;
    }

    public void setParentFourID(int parentFourID) {
        this.parentFourID = parentFourID;
    }

    public int getParentFiveID() {
        return parentFiveID;
    }

    public void setParentFiveID(int parentFiveID) {
        this.parentFiveID = parentFiveID;
    }


    public int getChildOneID() {
        return childOneID;
    }

    public void setChildOneID(int childOneID) {
        this.childOneID = childOneID;
    }

    public int getChildTwoID() {
        return childTwoID;
    }

    public void setChildTwoID(int childTwoID) {
        this.childTwoID = childTwoID;
    }

    public int getChildThreeID() {
        return childThreeID;
    }

    public void setChildThreeID(int childThreeID) {
        this.childThreeID = childThreeID;
    }

    public int getChildFourID() {
        return childFourID;
    }

    public void setChildFourID(int childFourID) {
        this.childFourID = childFourID;
    }

    public int getChildFiveID() {
        return childFiveID;
    }

    public void setChildFiveID(int childFiveID) {
        this.childFiveID = childFiveID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public ModuleBean getChildBean() {
        return childBean;
    }

    public void setChildBean(ModuleBean childBean) {
        this.childBean = childBean;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "ModuleBean{" +
                "type=" + type +
                ", isExpand=" + isExpand +
                ", childBean=" + childBean +
                ", ID='" + ID + '\'' +
                ", parentOneTxt='" + parentOneTxt + '\'' +
                ", parentOneID=" + parentOneID +
                ", parentTwoTxt='" + parentTwoTxt + '\'' +
                ", parentTwoID=" + parentTwoID +
                ", parentThreeTxt='" + parentThreeTxt + '\'' +
                ", parentThreeID=" + parentThreeID +
                ", parentFourTxt='" + parentFourTxt + '\'' +
                ", parentFourID=" + parentFourID +
                ", parentFiveTxt='" + parentFiveTxt + '\'' +
                ", parentFiveID=" + parentFiveID +
                ", childOneTxt='" + childOneTxt + '\'' +
                ", childOneID=" + childOneID +
                ", childTwoTxt='" + childTwoTxt + '\'' +
                ", childTwoID=" + childTwoID +
                ", childThreeTxt='" + childThreeTxt + '\'' +
                ", childThreeID=" + childThreeID +
                ", childFourTxt='" + childFourTxt + '\'' +
                ", childFourID=" + childFourID +
                ", childFiveTxt='" + childFiveTxt + '\'' +
                ", childFiveID=" + childFiveID +
                '}';
    }
}
