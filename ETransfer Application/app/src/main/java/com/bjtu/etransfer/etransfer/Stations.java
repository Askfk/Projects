package com.bjtu.etransfer.etransfer;

import cn.bmob.v3.BmobObject;

/**
 * Created by liyiming on 2017/3/12.
 */

public class Stations extends BmobObject {
    private String time;
    private String StartStation;
    private String EndStation;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStartStation() {
        return StartStation;
    }

    public void setStartStation(String startStation) {
        StartStation = startStation;
    }

    public String getEndStation() {
        return EndStation;
    }

    public void setEndStation(String endStation) {
        EndStation = endStation;
    }
}
