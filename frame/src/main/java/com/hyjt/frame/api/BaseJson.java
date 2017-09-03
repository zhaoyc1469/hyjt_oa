package com.hyjt.frame.api;

import java.io.Serializable;


/**
 * 如果你服务器返回的数据固定为这种方式(字段名可根据服务器更改)
 * 替换范型即可重用BaseJson
 * Created by jess on 26/09/2016 15:19
 * Contact with jess.yan.effort@gmail.com
 */

public class BaseJson<T> implements Serializable{

    /**
     * "Code":"200","Message":"登录成功","
     * Data":{"Name":"孙燚龙","PersonName":"孙燚龙","Id":"20170328081716366556360b7a83bf1","SysDepartmentId":"20131205081700359375001120ed65c","Company":null,"RoleMaterial":"2012041713135331326585dd4c1d320^","Part":"信息技术部"}}
     */
    private T Data;
    private String Code;
    private String Message;


    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    /**
     * 请求是否成功
     * @return
     */
    public boolean isSuccess() {
        if (Code.equals(Api.RequestSuccess)) {
            return true;
        } else {
            return false;
        }
    }
}
