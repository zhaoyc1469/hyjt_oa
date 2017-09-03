package com.hyjt.frame.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 赵宇驰
 * @time 2017/6/1  11:14
 * @desc ${TODO}
 */

public class JsonUtils {

    static Gson mGson;

    /**
     * javabean to json
     *
     * @return bean
     */
    public static <T> T parseJson(String json ,Class<T> tClass){
        if(mGson == null){
            mGson = new Gson();
        }

        if(TextUtils.isEmpty(json)){
            return null ;
        }

        return mGson.fromJson(json,tClass);
    }

    /**
     * javabean to json
     * @return json
     */
    public static String beanToJson(Object Bean) {
        if(mGson == null){
            mGson = new Gson();
        }
        return mGson.toJson(Bean);
    }

    /**
     * list to json
     * @return json
     */
    public static String listToJson(List<Object> list) {
        return mGson.toJson(list);
    }

    /**
     * map to json
     * @return json
     */
    public static String mapToJson(Map<String, Object> map) {
        return mGson.toJson(map);
    }


    public static String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


//    public static Map<String, Object> objectToMap(Object obj) throws Exception {
//        if(obj == null)
//            return null;
//
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
//        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//        for (PropertyDescriptor property : propertyDescriptors) {
//            String key = property.getName();
//            if (key.compareToIgnoreCase("class") == 0) {
//                continue;
//            }
//            Method getter = property.getReadMethod();
//            Object value = getter!=null ? getter.invoke(obj) : null;
//            map.put(key, value);
//        }
//
//        return map;
//    }

    public static Map<String, String> objectToMap(Object obj) throws Exception {
        if(obj == null){
            return null;
        }

        Map<String, String> map = new HashMap<String, String>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj).toString());
        }

        return map;
    }
}
