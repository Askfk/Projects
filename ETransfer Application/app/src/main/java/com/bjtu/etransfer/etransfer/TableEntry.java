//import java.util.ArrayList;
package com.bjtu.etransfer.etransfer;
class TableEntry {
    private boolean known;
    private int dist;
    private int path;
    private int time;
    private int length;

    public void setKnown(boolean flag){
        known = flag;
    }

    public boolean getKnown(){
        return known;
    }

    public void setDist(int dist){
        this.dist = dist;
    }

    public int getDist(){
        return dist;
    }

    public void setPath(int path){
        this.path = path;
    }

    public int getPath(){
        return path;
    }

    public void setTime(int time){
        this.time = time;
    }

    public int getTime(){
        return time;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}