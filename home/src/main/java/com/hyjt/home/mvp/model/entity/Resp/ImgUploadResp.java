package com.hyjt.home.mvp.model.entity.Resp;

/**
 * @author 赵宇驰
 * @time 2017/7/17  16:20
 * @desc ${TODO}
 */

public class ImgUploadResp {
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    String Id;//保存到数据库的文件的唯一码，即Id
    String Name;//保存到数据库的文件的文件名
}
