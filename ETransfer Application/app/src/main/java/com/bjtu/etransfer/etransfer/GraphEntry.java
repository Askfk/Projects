package com.bjtu.etransfer.etransfer;
import java.util.ArrayList;
import java.util.Hashtable;

class GraphEntry {
    private ArrayList<Integer> list, time, length;
    private ArrayList<String> arrline;
    private ArrayList<String> endTime;
    private int line;
    private int info;
    private int foot;
    private Hashtable<Integer, Integer> mHashTable1;
    private Hashtable<Integer, Integer> mHashTable2;

    public GraphEntry(){
        list = new ArrayList<Integer>();
        time = new ArrayList<Integer>();
        length = new ArrayList<Integer>();
        endTime = new ArrayList<String>();
        arrline = new ArrayList<String>();
        mHashTable1 = new Hashtable<Integer, Integer>();
        mHashTable2 = new Hashtable<Integer, Integer>();
    }

    public void addLine(int line){
        this.line |= line;
    }

    public int getLine(){
        return line;
    }
    public void setInfo(int info){
        this.info = info;
    }
    public int getInfo(){
        return info;
    }
    public void setfoot(int foot){
        this.info = foot;
    }
    public int getfoot(){
        return foot;
    }
    public void insertItem(int id){
        list.add(id);
    }

    public int getItem(int index){
        int id = list.get(index);
        return id;
    }
    public int size(){
        return list.size();
    }

    public void insertTime(int t){
        time.add(t);
    }

    public int getTime(int index){
        int t = time.get(index);
        return t;
    }
    public void insertLength(int l){
        length.add(l);
    }
    public int getLength(int index){
        int l = length.get(index);
        return l;
    }

    public void insertEtime(String t){
        endTime.add(t);
    }

    public String geEtime(int index){
        String t = endTime.get(index);
        return t;
    }

    public int arrLinesize(){
        return arrline.size();
    }

    public void insertArrline(String arr){
        arrline.add(arr);
    }
    public String getArrline(int index){
        String arr = arrline.get(index);
        return arr;
    }
    public boolean arrContains(String arr){
        return arrline.contains(arr);
    }


    public void put(int info, int t){
        mHashTable1.put(info, t);
        mHashTable2.put(t, info);
    }

    public boolean contains(int info){
        return mHashTable2.contains(info);
    }

    public int get(int info){
        return mHashTable1.get(info);
    }

}  
