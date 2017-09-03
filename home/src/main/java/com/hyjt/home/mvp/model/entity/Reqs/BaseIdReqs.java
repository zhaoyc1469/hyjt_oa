package com.hyjt.home.mvp.model.entity.Reqs;

/**
 * @author 赵宇驰
 * @time 2017/8/29  16:25
 * @desc ${TODO}
 */

public class BaseIdReqs {

    private String Id;

    public BaseIdReqs(String id) {
        Id = id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "BaseIdReqs{" +
                "Id='" + Id + '\'' +
                '}';
    }
}
