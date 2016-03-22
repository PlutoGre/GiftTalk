package com.pluto.http;

import android.os.Handler;
import android.os.Message;

import com.google.gson.reflect.TypeToken;
import com.pluto.tools.GsonTool;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Pluto on 2016/3/16.
 */
public class OkHttpTools {

    private static OkHttpClient okHttpClient ;

    static {
        if(okHttpClient == null){
            okHttpClient = new OkHttpClient();
        }
    }

    private Handler mHandler = new Handler();

    public static OkHttpTools newInstance(){
        return new OkHttpTools();
    }

    /**
     * okHttp的get请求,json类型为object
     * @param url
     * @param okCallBack
     */
    public <T> void okGet(String url ,Class<T> clazz , final IOkCallBack okCallBack , int requestCode){
        //创建一个请求
        Request request = new Request.Builder().url(url).build();
        //执行请求
        doRequest(okCallBack, request , requestCode , clazz);
    }

    /**
     * okHttp的get请求,json类型为Array
     * @param url
     * @param typeToken
     * @param okCallBack
     * @param requestCode
     * @param <T>
     */
    public <T> void okGet(String url , final TypeToken<List<T>> typeToken, final IOkCallBack okCallBack , final int requestCode){
        //创建一个请求
        Request request = new Request.Builder().url(url).build();
        //执行请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            //请求失败
            @Override
            public void onFailure(Call call, IOException e) {
                //工作线程，不能更新UI
            }

            //请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //工作线程，不能更新UI
                String result = response.body().string();
                final List<T> resultListInfo = GsonTool.parseJason2Array(result, typeToken);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        okCallBack.onSuccess(resultListInfo, requestCode);
                    }
                });
            }
        });
    }

    private <T> void doRequest(final IOkCallBack okCallBack, Request request , final int requestCode , final Class<T> clazz) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            //请求失败
            @Override
            public void onFailure(Call call, IOException e) {
                //工作线程，不能更新UI
            }

            //请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //工作线程，不能更新UI
                String result = response.body().string();
                final T resultInfo = GsonTool.parseJson2Object(result, clazz);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        okCallBack.onSuccess(resultInfo, requestCode);
                    }
                });
            }
        });
    }

    /**
     * okHttp的get请求
     * @param url
     * @param param
     * @param okCallBack
     * @param requestCode
     */
    public <T> void okPost(String url ,Class<T> clazz  , Map<String,String> param , final IOkCallBack okCallBack , int requestCode){
        //application/json是Http协议中的ContentType，charset=utf-8是Http协议中的编码格式
        //制定参数的编码方式和参数的格式
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        //设置POST的请求入参
        String formatParam = formatParam(param);
        RequestBody requestBody = RequestBody.create(mediaType, formatParam);
        //创建请求
        Request request = new Request.Builder().url(url).post(requestBody).build();
        //执行请求
        doRequest(okCallBack, request,requestCode , clazz);
    }

    /**
     *
     * @param param
     * @return
     */
    private String formatParam(Map<String,String> param) {
        JSONObject jsonObject = new JSONObject();
        try {
            Set<String> keySet = param.keySet();
            for (String key:keySet) {
                jsonObject.put(key,param.get(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }
}
