package com.pluto.bean;

import java.util.List;

/**
 * Created by Pluto on 2016/3/30.
 */
public class SearchFlowLayoutInfo {

    /**
     * code : 200
     * data : {"hot_words":["抹茶","手机壳","杯子","情侣","双肩包","手表","钱包","手链","耳钉","戒指","外套","雨伞"],"placeholder":"快选一份礼物，送给亲爱的Ta吧"}
     * message : OK
     */

    private int code;
    /**
     * hot_words : ["抹茶","手机壳","杯子","情侣","双肩包","手表","钱包","手链","耳钉","戒指","外套","雨伞"]
     * placeholder : 快选一份礼物，送给亲爱的Ta吧
     */

    private DataEntity data;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class DataEntity {
        private String placeholder;
        private List<String> hot_words;

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public void setHot_words(List<String> hot_words) {
            this.hot_words = hot_words;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public List<String> getHot_words() {
            return hot_words;
        }
    }
}
