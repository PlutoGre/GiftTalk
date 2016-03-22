package com.pluto.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Pluto on 2016/3/16.
 */
public class DateFormatTool {

    /**
     * 格式化日期
     * @param time
     * @return
     */
    public static String formatDate(long time){
        return formatDate(time , "yyyy-MM-dd E");
    }

    public static String formatDate(long time , String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date(time));
    }
}
