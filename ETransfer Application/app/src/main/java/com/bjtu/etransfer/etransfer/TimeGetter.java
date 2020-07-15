package com.bjtu.etransfer.etransfer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by liyiming on 2017/3/13.
 */

public class TimeGetter {
    String webUrl2 = "http://www.baidu.com";// 百度
    public String fullTime;
    public String halfTime;

    public void setfullTime(){
        this.fullTime = getNetworkTime(webUrl2);
    }

    public String getfullTime(){
        return fullTime;
    }

    public void setHalfTime(){
        this.halfTime = gethalfNetworkTime(webUrl2);
    }

    public String getHalfTime(){
        return halfTime;
    }

    public static String getNetworkTime(String webUrl) {
        try {
            URL url = new URL(webUrl);
            URLConnection conn = url.openConnection();
            conn.connect();
            long dateL = conn.getDate();
            Date date = new Date(dateL);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            return dateFormat.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String gethalfNetworkTime(String webUrl) {
        try {
            URL url = new URL(webUrl);
            URLConnection conn = url.openConnection();
            conn.connect();
            long dateL = conn.getDate();
            Date date = new Date(dateL);
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss",  Locale.CHINA);
            return dateFormat.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
