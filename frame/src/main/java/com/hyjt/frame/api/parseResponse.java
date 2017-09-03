package com.hyjt.frame.api;

import android.annotation.TargetApi;
import android.os.Build;

import io.reactivex.functions.Function;


/**
 * @author 赵宇驰
 * @time 2017/6/2  14:24
 * @desc ${TODO}
 */

@TargetApi(Build.VERSION_CODES.N)
public class parseResponse<T> implements Function<BaseJson<T>, T> {


    @Override
    public T apply(BaseJson<T> tBaseJson) throws Exception {

        if (tBaseJson.isSuccess()) {
            return tBaseJson.getData() == null ? (T) "无内容" : tBaseJson.getData();
        } else {
            if ("202".equals(tBaseJson.getCode())) {
                throw new Exception("202");
            }
            throw new Exception(tBaseJson.getMessage());
        }
    }
}
