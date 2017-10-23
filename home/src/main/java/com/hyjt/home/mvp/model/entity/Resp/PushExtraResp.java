package com.hyjt.home.mvp.model.entity.Resp;

/**
 * @author 赵宇驰
 * @time 2017/8/9  9:01
 * @desc ${TODO}
 */

public class PushExtraResp {

    private String Id;
    private String Type;
    private String Mode;


    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }
}
