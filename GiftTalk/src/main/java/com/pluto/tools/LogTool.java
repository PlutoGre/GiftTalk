package com.pluto.tools;

import android.util.Log;

/**
 * Created by Pluto on 2016/3/16.
 */
public class LogTool {

    private static boolean debug = true;

    public static void LOG_D(Class clazz,String log) {
        if (debug) {
            int len = log.length();
            int num = len/1000;

            String str;
            for (int i=0; i<num+1; i++) {
                if (i == num) {
                    str = log.substring(i*1000,len);
                } else {
                    str = log.substring(i * 1000, 1000 * (i + 1));
                }

                Log.d(clazz.toString(), str);
            }
        }
    }
}
