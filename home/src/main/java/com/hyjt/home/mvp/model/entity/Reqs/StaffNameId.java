package com.hyjt.home.mvp.model.entity.Reqs;

import java.io.Serializable;

/**
 * @author 赵宇驰
 * @time 2017/7/19  9:57
 * @desc ${TODO}
 */

public class StaffNameId implements Serializable {

    private String Name;
    private String Id;

    public StaffNameId() {
    }

    public StaffNameId(String name, String id) {
        Name = name;
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "StaffNameId{" +
                "Name='" + Name + '\'' +
                ", Id='" + Id + '\'' +
                '}';
    }
}
