package com.bjtu.etransfer.etransfer;

/**
 * Created by liyiming on 2017/4/6.
 */

import cn.bmob.v3.BmobObject;

public class user_Location extends BmobObject {
    private String ID;
    private String longtitude;
    private String latitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
