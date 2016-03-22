package com.pluto.http;

/**
 * Created by Pluto on 2016/3/16.
 */
public interface IOkCallBack<E> {

    public void onSuccess(E resultInfo , int requestCode);
}
