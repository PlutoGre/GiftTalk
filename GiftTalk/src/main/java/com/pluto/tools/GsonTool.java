package com.pluto.tools;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Pluto on 2016/3/16.
 */
public class GsonTool {

    private static Gson gson = new Gson();

    /**
     * 将得到的json数据解析成object对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseJson2Object(String json , Class<T> clazz){
        return gson.fromJson(json , clazz);
    }

    /**
     * 将得到的json数据解析成list对象
     * @param json
     * @param typeToken
     * @param <T>
     * @return
     */
    public static <T> List<T> parseJason2Array(String json , TypeToken<List<T>> typeToken){
        return gson.fromJson(json , typeToken.getType());
    }
}
