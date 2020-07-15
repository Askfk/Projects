package com.bjtu.etransfer.etransfer;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class stationdetail extends AppCompatActivity {
    private SharedPreferences sharedPreferences3;
    private int j;
    private ListView lv;       //声明一个列表

    /* 显示ListView的两种方法：
     * 1)在activity对应的布局里声明ListView控件，使用findViewById初始listView对象，最后listView.setAdapter显示listVIew
     * 2)直接初始化ListVIew = new ListView(this),setAdapter后，通过setContentView(listView)把listView显示出来
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stationdetail);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        lv = (ListView) findViewById(R.id.shoumoban);
        sharedPreferences3 = getSharedPreferences("users3", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences3.edit();
        j = sharedPreferences3.getInt("Line", 0);
//        lv = new ListView(this) ;
        //String[]的img 要和int[]的img名称一致
        MySimpleAdapter adapter = new MySimpleAdapter(this, getData(), R.layout.itemshoumoban,
                new String[]{"img", "tv1", "tv2", "tv3"},
                new int[]{R.id.iv_logo99, R.id.tv_fangxiang, R.id.tv_shouban, R.id.tv_moban});

        lv.setAdapter(adapter);
//        setContentView(lv) ;

        //添加监听器


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Map<String, Object>> getData() {
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Map<String, Object> item;
        switch(j){
            case 101:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:10") ;
                item.put("tv3", "22:55") ;
                data.add(item) ;
                break;
            }
            case 102:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "4:58") ;
                item.put("tv3", "22:59") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:36") ;
                item.put("tv3", "0:06") ;
                data.add(item) ;
                break;
            }
            case 103:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:01") ;
                item.put("tv3", "23:02") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:32") ;
                item.put("tv3", "0:03") ;
                data.add(item) ;
                break;
            }
            case 104:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:04") ;
                item.put("tv3", "23:05") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:29") ;
                item.put("tv3", "0:00") ;
                data.add(item) ;
                break;
            }
            case 105:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:06") ;
                item.put("tv3", "23:07") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:26") ;
                item.put("tv3", "23:58") ;
                data.add(item) ;
                break;
            }
            case 106:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:09") ;
                item.put("tv3", "23:10") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:23") ;
                item.put("tv3", "23:55") ;
                data.add(item) ;
                break;
            }
            case 107:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:12") ;
                item.put("tv3", "23:13") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:21") ;
                item.put("tv3", "23:52") ;
                data.add(item) ;
                break;
            }
            case 108:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:14") ;
                item.put("tv3", "23:15") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:18") ;
                item.put("tv3", "23:50") ;
                data.add(item) ;
                break;
            }
            case 109:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:17") ;
                item.put("tv3", "23:18") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:16") ;
                item.put("tv3", "23:47") ;
                data.add(item) ;
                break;
            }
            case 110:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:19") ;
                item.put("tv3", "23:20") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:13") ;
                item.put("tv3", "23:45") ;
                data.add(item) ;
                break;
            }
            case 111:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:21") ;
                item.put("tv3", "23:22") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:11") ;
                item.put("tv3", "23:42") ;
                data.add(item) ;
                break;
            }
            case 112:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:15") ;
                item.put("tv3", "23:25") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:10") ;
                item.put("tv3", "23:41") ;
                data.add(item) ;
                break;
            }
            case 113:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:17") ;
                item.put("tv3", "23:27") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:17") ;
                item.put("tv3", "23:38") ;
                data.add(item) ;
                break;
            }
            case 114:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:19") ;
                item.put("tv3", "23:29") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:15") ;
                item.put("tv3", "23:36") ;
                data.add(item) ;
                break;
            }
            case 115:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:21") ;
                item.put("tv3", "23:31") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:13") ;
                item.put("tv3", "23:34") ;
                data.add(item) ;
                break;
            }
            case 116:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:23") ;
                item.put("tv3", "23:33") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:11") ;
                item.put("tv3", "23:32") ;
                data.add(item) ;
                break;
            }
            case 117:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:25") ;
                item.put("tv3", "23:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:09") ;
                item.put("tv3", "23:30") ;
                data.add(item) ;
                break;
            }
            case 118:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:28") ;
                item.put("tv3", "23:38") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:07") ;
                item.put("tv3", "23:28") ;
                data.add(item) ;
                break;
            }
            case 119:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "23:40") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:04") ;
                item.put("tv3", "23:25") ;
                data.add(item) ;
                break;
            }
            case 120:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:32") ;
                item.put("tv3", "23:42") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:02") ;
                item.put("tv3", "23:23") ;
                data.add(item) ;
                break;
            }
            case 121:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:35") ;
                item.put("tv3", "23:45") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:00") ;
                item.put("tv3", "23:21") ;
                data.add(item) ;
                break;
            }
            case 122:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 四惠东") ;
                item.put("tv2", "5:38") ;
                item.put("tv3", "23:48") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "4:57") ;
                item.put("tv3", "23:18") ;
                data.add(item) ;
                break;
            }
            case 123:{

                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l1) ;
                item.put("tv1", "开往 苹果园") ;
                item.put("tv2", "5:05") ;
                item.put("tv3", "23:15") ;
                data.add(item) ;
                break;
            }
            case 201:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:10") ;
                item.put("tv3", "22:15") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                item.put("tv3", "23:00") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:34") ;
                item.put("tv3", "22:42") ;
                data.add(item) ;
                //item = new HashMap<String,Object>() ;
                //item.put("img", R.drawable.l1) ;
                //item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                //item.put("tv3", "23:48") ;
                //data.add(item) ;
                break;
            }
            case 218:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:35") ;
                item.put("tv3", "22:56") ;
                data.add(item) ;
                //item = new HashMap<String,Object>() ;
                //item.put("img", R.drawable.l1) ;
                //item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                //item.put("tv3", "23:00") ;
                //data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:04") ;
                item.put("tv3", "21:56") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                item.put("tv3", "22:45") ;
                data.add(item) ;
                break;
            }
            case 217:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:32") ;
                item.put("tv3", "22:53") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                item.put("tv3", "23:38") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:06") ;
                item.put("tv3", "21:59") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                item.put("tv3", "22:48") ;
                data.add(item) ;
                break;
            }
            case 216:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:29") ;
                item.put("tv3", "22:50") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                item.put("tv3", "23:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:09") ;
                item.put("tv3", "22:01") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                item.put("tv3", "22:60") ;
                data.add(item) ;
                break;
            }
            case 215:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:28") ;
                item.put("tv3", "22:49") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                item.put("tv3", "23:34") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:11") ;
                item.put("tv3", "22:03") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                item.put("tv3", "22:52") ;
                data.add(item) ;
                break;
            }
            case 214:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:24") ;
                item.put("tv3", "22:45") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                item.put("tv3", "23:30") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:14") ;
                item.put("tv3", "22:07") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                item.put("tv3", "22:56") ;
                data.add(item) ;
                break;
            }
            case 213:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:22") ;
                item.put("tv3", "22:43") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                item.put("tv3", "23:28") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:16") ;
                item.put("tv3", "22:09") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                item.put("tv3", "22:58") ;
                data.add(item) ;
                break;
            }
            case 212:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:20") ;
                item.put("tv3", "22:41") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                item.put("tv3", "23:26") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:18") ;
                item.put("tv3", "22:11") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                item.put("tv3", "23:00") ;
                data.add(item) ;
                break;
            }
            case 211:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:17") ;
                item.put("tv3", "22:38") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                item.put("tv3", "23:23") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:21") ;
                item.put("tv3", "22:14") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                item.put("tv3", "23:03") ;
                data.add(item) ;
                break;
            }
            case 210:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:15") ;
                item.put("tv3", "22:36") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                item.put("tv3", "23:21") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:12") ;
                item.put("tv3", "22:16") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                item.put("tv3", "23:05") ;
                data.add(item) ;
                break;
            }
            case 209:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:28") ;
                item.put("tv3", "22:33") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                item.put("tv3", "23:18") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:14") ;
                item.put("tv3", "22:19") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                item.put("tv3", "23:08") ;
                data.add(item) ;
                break;
            }
            case 208:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:25") ;
                item.put("tv3", "22:30") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                item.put("tv3", "23:15") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:17") ;
                item.put("tv3", "22:21") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                item.put("tv3", "23:10") ;
                data.add(item) ;
                break;
            }
            case 207:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:23") ;
                item.put("tv3", "22:28") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                item.put("tv3", "23:13") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:19") ;
                item.put("tv3", "22:24") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                item.put("tv3", "23:13") ;
                data.add(item) ;
                break;
            }
            case 206:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:21") ;
                item.put("tv3", "22:26") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                item.put("tv3", "23:11") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:21") ;
                item.put("tv3", "22:25") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                item.put("tv3", "23:14") ;
                data.add(item) ;
                break;
            }
            case 205:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:19") ;
                item.put("tv3", "22:24") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                item.put("tv3", "23:09") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:23") ;
                item.put("tv3", "22:27") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                item.put("tv3", "23:16") ;
                data.add(item) ;
                break;
            }
            case 204:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:17") ;
                item.put("tv3", "22:22") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                item.put("tv3", "23:07") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:25") ;
                item.put("tv3", "22:30") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                item.put("tv3", "23:19") ;
                data.add(item) ;
                break;
            }
            case 203:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:14") ;
                item.put("tv3", "22:19") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                item.put("tv3", "23:04") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:28") ;
                item.put("tv3", "22:33") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                item.put("tv3", "23:22") ;
                data.add(item) ;
                break;
            }
            case 202:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:12") ;
                item.put("tv3", "22:17") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点积水潭") ;
                //item.put("tv2", "5:38") ;
                item.put("tv3", "23:02") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "22:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l2) ;
                item.put("tv1", "区间 终点西直门") ;
                // item.put("tv2", "5:38") ;
                item.put("tv3", "23:24") ;
                data.add(item) ;
                break;
            }
            case 2501:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 苏庄") ;
                item.put("tv2", "5:58") ;
                item.put("tv3", "23:00") ;
                data.add(item) ;
                //item = new HashMap<String,Object>() ;
                //item.put("img", R.drawable.l25) ;
                //item.put("tv1", "开往 郭公庄") ;
                //item.put("tv2", "5:38") ;
                //item.put("tv3", "23:24") ;
                //data.add(item) ;
                break;
            }
            case 2502:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 苏庄") ;
                item.put("tv2", "6:00") ;
                item.put("tv3", "23:02") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "5:44") ;
                item.put("tv3", "22:29") ;
                data.add(item) ;
                break;
            }
            case 2503:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 苏庄") ;
                item.put("tv2", "6:06") ;
                item.put("tv3", "23:08") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "5:38") ;
                item.put("tv3", "22:23") ;
                data.add(item) ;
                break;
            }
            case 2504:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 苏庄") ;
                item.put("tv2", "6:11") ;
                item.put("tv3", "23:13") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "5:33") ;
                item.put("tv3", "22:18") ;
                data.add(item) ;
                break;
            }
            case 2505:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 苏庄") ;
                item.put("tv2", "6:14") ;
                item.put("tv3", "23:16") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "22:15") ;
                data.add(item) ;
                break;
            }
            case 2506:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 苏庄") ;
                item.put("tv2", "6:17") ;
                item.put("tv3", "23:19") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "5:27") ;
                item.put("tv3", "22:12") ;
                data.add(item) ;
                break;
            }
            case 2507:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 苏庄") ;
                item.put("tv2", "6:20") ;
                item.put("tv3", "23:22") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "5:24") ;
                item.put("tv3", "22:09") ;
                data.add(item) ;
                break;
            }
            case 2508:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 苏庄") ;
                item.put("tv2", "6:22") ;
                item.put("tv3", "23:24") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "5:22") ;
                item.put("tv3", "22:07") ;
                data.add(item) ;
                break;
            }
            case 2509:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 苏庄") ;
                item.put("tv2", "6:25") ;
                item.put("tv3", "23:27") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "5:19") ;
                item.put("tv3", "22:04") ;
                data.add(item) ;
                break;
            }
            case 2510:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 苏庄") ;
                item.put("tv2", "6:27") ;
                item.put("tv3", "23:29") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "5:17") ;
                item.put("tv3", "22:02") ;
                data.add(item) ;
                break;
            }
            case 2511:{
                //  item = new HashMap<String,Object>() ;
                //  item.put("img", R.drawable.l25) ;
                //  item.put("tv1", "开往 苏庄") ;
                //  item.put("tv2", "6:00") ;
                //  item.put("tv3", "23:02") ;
                //  data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l25) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "5:15") ;
                item.put("tv3", "22:00") ;
                data.add(item) ;
                break;
            }
            case 501:{
                //  item = new HashMap<String,Object>() ;
                //  item.put("img", R.drawable.l5) ;
                //  item.put("tv1", "开往 天通苑北") ;
                //  item.put("tv2", "6:00") ;
                //  item.put("tv3", "23:02") ;
                //  data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:00") ;
                item.put("tv3", "22:48") ;
                data.add(item) ;
                break;
            }
            case 502:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "6:07") ;
                item.put("tv3", "23:58") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:02") ;
                item.put("tv3", "22:50") ;
                data.add(item) ;
                break;
            }
            case 503:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "6:06") ;
                item.put("tv3", "23:57") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:04") ;
                item.put("tv3", "22:52") ;
                data.add(item) ;
                break;
            }
            case 504:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "6:03") ;
                item.put("tv3", "23:54") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:06") ;
                item.put("tv3", "22:54") ;
                data.add(item) ;
                break;
            }
            case 505:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "6:00") ;
                item.put("tv3", "23:51") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:09") ;
                item.put("tv3", "22:57") ;
                data.add(item) ;
                break;
            }
            case 506:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:58") ;
                item.put("tv3", "23:49") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:11") ;
                item.put("tv3", "22:59") ;
                data.add(item) ;
                break;
            }
            case 507:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:54") ;
                item.put("tv3", "23:45") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:15") ;
                item.put("tv3", "23:03") ;
                data.add(item) ;
                break;
            }
            case 508:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:52") ;
                item.put("tv3", "23:43") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:17") ;
                item.put("tv3", "23:05") ;
                data.add(item) ;
                break;
            }
            case 509:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:50") ;
                item.put("tv3", "23:41") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:20") ;
                item.put("tv3", "23:08") ;
                data.add(item) ;
                break;
            }
            case 510:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:47") ;
                item.put("tv3", "23:38") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:22") ;
                item.put("tv3", "23:10") ;
                data.add(item) ;
                break;
            }
            case 511:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:45") ;
                item.put("tv3", "23:36") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:24") ;
                item.put("tv3", "23:12") ;
                data.add(item) ;
                break;
            }
            case 512:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:43") ;
                item.put("tv3", "23:34") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:26") ;
                item.put("tv3", "23:14") ;
                data.add(item) ;
                break;
            }
            case 513:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:41") ;
                item.put("tv3", "23:32") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:28") ;
                item.put("tv3", "23:16") ;
                data.add(item) ;
                break;
            }
            case 514:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:39") ;
                item.put("tv3", "23:30") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "23:18") ;
                data.add(item) ;
                break;
            }
            case 515:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:37") ;
                item.put("tv3", "23:28") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:32") ;
                item.put("tv3", "23:20") ;
                data.add(item) ;
                break;
            }
            case 516:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:35") ;
                item.put("tv3", "23:26") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:34") ;
                item.put("tv3", "23:22") ;
                data.add(item) ;
                break;
            }
            case 517:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:33") ;
                item.put("tv3", "23:24") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:36") ;
                item.put("tv3", "23:24") ;
                data.add(item) ;
                break;
            }
            case 518:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:31") ;
                item.put("tv3", "23:22") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:38") ;
                item.put("tv3", "23:26") ;
                data.add(item) ;
                break;
            }
            case 519:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:29") ;
                item.put("tv3", "23:20") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:40") ;
                item.put("tv3", "23:28") ;
                data.add(item) ;
                break;
            }
            case 520:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:27") ;
                item.put("tv3", "23:18") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:42") ;
                item.put("tv3", "23:30") ;
                data.add(item) ;
                break;
            }
            case 521:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:24") ;
                item.put("tv3", "23:15") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:45") ;
                item.put("tv3", "23:33") ;
                data.add(item) ;
                break;
            }
            case 522:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:22") ;
                item.put("tv3", "23:13") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:47") ;
                item.put("tv3", "23:35") ;
                data.add(item) ;
                break;
            }
            case 523:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l5) ;
                item.put("tv1", "开往 天通苑北") ;
                item.put("tv2", "5:20") ;
                item.put("tv3", "23:11") ;
                data.add(item) ;
                //item = new HashMap<String,Object>() ;
                //item.put("img", R.drawable.l5) ;
                //item.put("tv1", "开往 宋家庄") ;
                //item.put("tv2", "5:32") ;
                //item.put("tv3", "23:20") ;
                //data.add(item) ;
                break;
            }
            case 601:{
                //item = new HashMap<String,Object>() ;
                //item.put("img", R.drawable.l6) ;
                //item.put("tv1", "开往 海淀五路居") ;
                //item.put("tv2", "5:20") ;
                //item.put("tv3", "23:11") ;
                //data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:23") ;
                item.put("tv3", "22:40") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "23:19") ;
                data.add(item) ;
                break;
            }
            case 602:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "6:03") ;
                item.put("tv3", "23:56") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:25") ;
                item.put("tv3", "22:42") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "23:21") ;
                data.add(item) ;
                break;
            }
            case 603:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "6:00") ;
                item.put("tv3", "23:53") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:28") ;
                item.put("tv3", "22:45") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "23:24") ;
                data.add(item) ;
                break;
            }
            case 604:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:58") ;
                item.put("tv3", "23:51") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "22:47") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "23:26") ;
                data.add(item) ;
                break;
            }
            case 605:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:55") ;
                item.put("tv3", "23:48") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:33") ;
                item.put("tv3", "22:50") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "23:29") ;
                data.add(item) ;
                break;
            }
            case 606:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:53") ;
                item.put("tv3", "23:46") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:35") ;
                item.put("tv3", "22:52") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "23:31") ;
                data.add(item) ;
                break;
            }
            case 607:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:50") ;
                item.put("tv3", "23:44") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:38") ;
                item.put("tv3", "22:55") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "23:34") ;
                data.add(item) ;
                break;
            }
            case 608:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:48") ;
                item.put("tv3", "23:41") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:40") ;
                item.put("tv3", "22:57") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "23:36") ;
                data.add(item) ;
                break;
            }
            case 609:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:45") ;
                item.put("tv3", "23:39") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:43") ;
                item.put("tv3", "23:00") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "23:39") ;
                data.add(item) ;
                break;
            }
            case 610:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:42") ;
                item.put("tv3", "23:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:47") ;
                item.put("tv3", "23:04") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "23:43") ;
                data.add(item) ;
                break;
            }
            case 611:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:39") ;
                item.put("tv3", "23:32") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:49") ;
                item.put("tv3", "23:06") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "23:45") ;
                data.add(item) ;
                break;
            }
            case 612:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:36") ;
                item.put("tv3", "23:29") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:52") ;
                item.put("tv3", "23:09") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "23:48") ;
                data.add(item) ;
                break;
            }
            case 613:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:34") ;
                item.put("tv3", "23:27") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:54") ;
                item.put("tv3", "23:11") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "23:50") ;
                data.add(item) ;
                break;
            }
            case 614:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:31") ;
                item.put("tv3", "23:25") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:57") ;
                item.put("tv3", "23:14") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "23:53") ;
                data.add(item) ;
                break;
            }
            case 615:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:28") ;
                item.put("tv3", "23:22") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:59") ;
                item.put("tv3", "23:16") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "23:55") ;
                data.add(item) ;
                break;
            }
            case 616:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:26") ;
                item.put("tv3", "23:19") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "6:02") ;
                item.put("tv3", "23:29") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "23:58") ;
                data.add(item) ;
                break;
            }
            case 617:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:22") ;
                item.put("tv3", "23:15") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "6:06") ;
                item.put("tv3", "23:23") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "0:02") ;
                data.add(item) ;
                break;
            }
            case 618:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:20") ;
                item.put("tv3", "23:13") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "6:08") ;
                item.put("tv3", "23:25") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "0:04") ;
                data.add(item) ;
                break;
            }
            case 619:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:17") ;
                item.put("tv3", "23:10") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "6:11") ;
                item.put("tv3", "23:28") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 草房") ;
                item.put("tv3", "0:07") ;
                data.add(item) ;
                break;
            }
            case 620:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:15") ;
                item.put("tv3", "23:08") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:23") ;
                item.put("tv3", "23:31") ;
                data.add(item) ;
                break;
            }
            case 621:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "6:06") ;
                item.put("tv3", "23:05") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:26") ;
                item.put("tv3", "23:34") ;
                data.add(item) ;
                break;
            }
            case 622:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "6:02") ;
                item.put("tv3", "23:01") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:29") ;
                item.put("tv3", "23:38") ;
                data.add(item) ;
                break;
            }
            case 623:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:58") ;
                item.put("tv3", "22:57") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:33") ;
                item.put("tv3", "23:41") ;
                data.add(item) ;
                break;
            }
            case 624:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:54") ;
                item.put("tv3", "22:53") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:37") ;
                item.put("tv3", "23:45") ;
                data.add(item) ;
                break;
            }
            case 625:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:52") ;
                item.put("tv3", "22:51") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 潞城") ;
                item.put("tv2", "5:40") ;
                item.put("tv3", "23:48") ;
                data.add(item) ;
                break;
            }
            case 626:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l6) ;
                item.put("tv1", "开往 海淀五路居") ;
                item.put("tv2", "5:50") ;
                item.put("tv3", "22:49") ;
                data.add(item) ;

                break;
            }
            case 401:{
                //item = new HashMap<String,Object>() ;
                //item.put("img", R.drawable.l6) ;
                //item.put("tv1", "开往 安河桥北") ;
                //item.put("tv2", "5:26") ;
                ///item.put("tv3", "23:19") ;
                //data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:00") ;
                item.put("tv3", "22:20") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "22:45") ;
                data.add(item) ;
                break;
            }
            case 402:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:57") ;
                item.put("tv3", "23:57") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:01") ;
                item.put("tv3", "22:21") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "22:46") ;
                data.add(item) ;
                break;
            }
            case 403:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:55") ;
                item.put("tv3", "23:55") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:04") ;
                item.put("tv3", "22:24") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "22:49") ;
                data.add(item) ;
                break;
            }
            case 404:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:52") ;
                item.put("tv3", "23:52") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:06") ;
                item.put("tv3", "22:26") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "22:51") ;
                data.add(item) ;
                break;
            }
            case 405:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:50") ;
                item.put("tv3", "23:50") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:09") ;
                item.put("tv3", "22:29") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "22:54") ;
                data.add(item) ;
                break;
            }
            case 406:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:48") ;
                item.put("tv3", "23:48") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:10") ;
                item.put("tv3", "22:30") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "22:55") ;
                data.add(item) ;
                break;
            }
            case 407:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:47") ;
                item.put("tv3", "23:47") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:13") ;
                item.put("tv3", "22:33") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "22:58") ;
                data.add(item) ;
                break;
            }
            case 408:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:44") ;
                item.put("tv3", "23:44") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:15") ;
                item.put("tv3", "22:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "23:00") ;
                data.add(item) ;
                break;
            }
            case 409:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:42") ;
                item.put("tv3", "23:42") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:17") ;
                item.put("tv3", "22:37") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "23:02") ;
                data.add(item) ;
                break;
            }
            case 410:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:39") ;
                item.put("tv3", "23:39") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:19") ;
                item.put("tv3", "22:39") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "23:04") ;
                data.add(item) ;
                break;
            }
            case 411:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:37") ;
                item.put("tv3", "23:37") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:22") ;
                item.put("tv3", "22:42") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "23:07") ;
                data.add(item) ;
                break;
            }
            case 412:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:35") ;
                item.put("tv3", "23:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:25") ;
                item.put("tv3", "22:45") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "23:10") ;
                data.add(item) ;
                break;
            }
            case 413:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:32") ;
                item.put("tv3", "23:32") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:26") ;
                item.put("tv3", "22:46") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "23:11") ;
                data.add(item) ;
                break;
            }
            case 414:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "23:30") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:28") ;
                item.put("tv3", "22:48") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "23:13") ;
                data.add(item) ;
                break;
            }
            case 415:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:28") ;
                item.put("tv3", "23:28") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "22:50") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "23:15") ;
                data.add(item) ;
                break;
            }
            case 416:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:26") ;
                item.put("tv3", "23:26") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:32") ;
                item.put("tv3", "22:52") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "23:17") ;
                data.add(item) ;
                break;
            }
            case 417:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:25") ;
                item.put("tv3", "23:25") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:35") ;
                item.put("tv3", "22:55") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "23:20") ;
                data.add(item) ;
                break;
            }
            case 418:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:23") ;
                item.put("tv3", "23:23") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:37") ;
                item.put("tv3", "22:57") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "23:32") ;
                data.add(item) ;
                break;
            }
            case 419:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:20") ;
                item.put("tv3", "23:20") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:38") ;
                item.put("tv3", "22:58") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "23:23") ;
                data.add(item) ;
                break;
            }
            case 420:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:18") ;
                item.put("tv3", "23:18") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:41") ;
                item.put("tv3", "23:01") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "23:26") ;
                data.add(item) ;
                break;
            }
            case 421:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:15") ;
                item.put("tv3", "23:15") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:43") ;
                item.put("tv3", "23:03") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "23:28") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.ll4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "每周五") ;
                item.put("tv3", "周日") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("tv1", "沿途站只下不上") ;
                item.put("tv2", "23:30") ;
                item.put("tv3", "23:45") ;
                data.add(item) ;
                break;
            }
            case 422:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:13") ;
                item.put("tv3", "23:13") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:46") ;
                item.put("tv3", "23:06") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "23:31") ;
                data.add(item) ;
                break;
            }
            case 423:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:11") ;
                item.put("tv3", "23:11") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:38") ;
                item.put("tv3", "23:08") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 公益西桥") ;
                item.put("tv3", "23:3") ;
                data.add(item) ;
                break;
            }
            case 424:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:10") ;
                item.put("tv3", "23:10") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:50") ;
                item.put("tv3", "23:10") ;
                data.add(item) ;
                break;
            }
            case 425:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:57") ;
                item.put("tv3", "23:05") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "5:54") ;
                item.put("tv3", "23:14") ;
                data.add(item) ;
                break;
            }
            case 426:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:51") ;
                item.put("tv3", "22:59") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "6:00") ;
                item.put("tv3", "23:19") ;
                data.add(item) ;
                break;
            }
            case 427:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:48") ;
                item.put("tv3", "22:56") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "6:02") ;
                item.put("tv3", "23:22") ;
                data.add(item) ;
                break;
            }
            case 428:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:46") ;
                item.put("tv3", "22:54") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "6:04") ;
                item.put("tv3", "23:24") ;
                data.add(item) ;
                break;
            }
            case 429:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:44") ;
                item.put("tv3", "22:52") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "6:06") ;
                item.put("tv3", "23:26") ;
                data.add(item) ;
                break;
            }
            case 430:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:42") ;
                item.put("tv3", "22:50") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "6:08") ;
                item.put("tv3", "23:28") ;
                data.add(item) ;
                break;
            }
            case 431:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:40") ;
                item.put("tv3", "22:48") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "6:10") ;
                item.put("tv3", "23:30") ;
                data.add(item) ;
                break;
            }
            case 432:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:38") ;
                item.put("tv3", "22:46") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "6:12") ;
                item.put("tv3", "23:32") ;
                data.add(item) ;
                break;
            }
            case 433:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:35") ;
                item.put("tv3", "22:43") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "6:15") ;
                item.put("tv3", "23:35") ;
                data.add(item) ;
                break;
            }
            case 434:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:32") ;
                item.put("tv3", "22:40") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 天宫院") ;
                item.put("tv2", "6:19") ;
                item.put("tv3", "23:38") ;
                data.add(item) ;
                break;
            }
            case 435:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l4) ;
                item.put("tv1", "开往 安河桥北") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "22:48") ;
                data.add(item) ;

            }
            case 1601:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l16) ;
                item.put("tv1", "开往 北安河") ;
                item.put("tv2", "6:00") ;
                item.put("tv3", "22:35") ;
                data.add(item) ;
                break;
            }
            case 1602:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l16) ;
                item.put("tv1", "开往 北安河") ;
                item.put("tv2", "6:06") ;
                item.put("tv3", "23:01") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l16) ;
                item.put("tv1", "开往 西苑") ;
                item.put("tv2", "5:49") ;
                item.put("tv3", "22:54") ;
                data.add(item) ;
                break;
            }
            case 1603:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l16) ;
                item.put("tv1", "开往 北安河") ;
                item.put("tv2", "6:09") ;
                item.put("tv3", "23:04") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l16) ;
                item.put("tv1", "开往 西苑") ;
                item.put("tv2", "5:46") ;
                item.put("tv3", "22:51") ;
                data.add(item) ;
                break;
            }
            case 1604:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l16) ;
                item.put("tv1", "开往 北安河") ;
                item.put("tv2", "6:13") ;
                item.put("tv3", "23:08") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l16) ;
                item.put("tv1", "开往 西苑") ;
                item.put("tv2", "5:42") ;
                item.put("tv3", "22:47") ;
                data.add(item) ;
                break;
            }
            case 1605:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l16) ;
                item.put("tv1", "开往 北安河") ;
                item.put("tv2", "6:16") ;
                item.put("tv3", "23:11") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l16) ;
                item.put("tv1", "开往 西苑") ;
                item.put("tv2", "5:39") ;
                item.put("tv3", "22:44") ;
                data.add(item) ;
                break;
            }
            case 1606:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l16) ;
                item.put("tv1", "开往 北安河") ;
                item.put("tv2", "6:20") ;
                item.put("tv3", "23:15") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l16) ;
                item.put("tv1", "开往 西苑") ;
                item.put("tv2", "5:35") ;
                item.put("tv3", "22:40") ;
                data.add(item) ;
                break;
            }
            case 1607:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l16) ;
                item.put("tv1", "开往 北安河") ;
                item.put("tv2", "6:23") ;
                item.put("tv3", "23:18") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l16) ;
                item.put("tv1", "开往 西苑") ;
                item.put("tv2", "5:32") ;
                item.put("tv3", "22:37") ;
                data.add(item) ;
                break;
            }
            case 1608:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l16) ;
                item.put("tv1", "开往 北安河") ;
                item.put("tv2", "6:27") ;
                item.put("tv3", "23:22") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l16) ;
                item.put("tv1", "开往 西苑") ;
                item.put("tv2", "5:28") ;
                item.put("tv3", "22:33") ;
                data.add(item) ;
                break;
            }
            case 1609:{

                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l16) ;
                item.put("tv1", "开往 西苑") ;
                item.put("tv2", "5:25") ;
                item.put("tv3", "22:30") ;
                data.add(item) ;
                break;
            }
            case 701:{
                /*item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l16) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "6:27") ;
                item.put("tv3", "23:22") ;
                data.add(item) ;*/
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "23:15") ;
                data.add(item) ;
                break;
            }
            case 702:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:50") ;
                item.put("tv3", "23:05") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "5:32") ;
                item.put("tv3", "23:17") ;
                data.add(item) ;
                break;
            }
            case 703:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:48") ;
                item.put("tv3", "23:03") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "5:34") ;
                item.put("tv3", "23:19") ;
                data.add(item) ;
                break;
            }
            case 704:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:46") ;
                item.put("tv3", "23:01") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "5:37") ;
                item.put("tv3", "23:22") ;
                data.add(item) ;
                break;
            }
            case 705:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:43") ;
                item.put("tv3", "22:58") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "5:39") ;
                item.put("tv3", "23:24") ;
                data.add(item) ;
                break;
            }
            case 706:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:41") ;
                item.put("tv3", "22:56") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "5:41") ;
                item.put("tv3", "23:26") ;
                data.add(item) ;
                break;
            }
            case 707:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:39") ;
                item.put("tv3", "22:54") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "5:43") ;
                item.put("tv3", "23:28") ;
                data.add(item) ;
                break;
            }
            case 708:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:37") ;
                item.put("tv3", "22:52") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "5:45") ;
                item.put("tv3", "23:30") ;
                data.add(item) ;
                break;
            }
            case 709:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:35") ;
                item.put("tv3", "22:50") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "5:48") ;
                item.put("tv3", "23:33") ;
                data.add(item) ;
                break;
            }
            case 710:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:33") ;
                item.put("tv3", "22:48") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "5:50") ;
                item.put("tv3", "23:35") ;
                data.add(item) ;
                break;
            }
            case 711:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "22:45") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "5:52") ;
                item.put("tv3", "23:37") ;
                data.add(item) ;
                break;
            }
            case 712:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:26") ;
                item.put("tv3", "22:41") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "5:56") ;
                item.put("tv3", "23:41") ;
                data.add(item) ;
                break;
            }
            case 713:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:24") ;
                item.put("tv3", "22:39") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "5:58") ;
                item.put("tv3", "23:43") ;
                data.add(item) ;
                break;
            }
            case 714:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:22") ;
                item.put("tv3", "22:37") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "6:00") ;
                item.put("tv3", "23:45") ;
                data.add(item) ;
                break;
            }
            case 715:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:20") ;
                item.put("tv3", "22:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "6:02") ;
                item.put("tv3", "23:47") ;
                data.add(item) ;
                break;
            }
            case 716:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:18") ;
                item.put("tv3", "22:33") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "6:04") ;
                item.put("tv3", "23:49") ;
                data.add(item) ;
                break;
            }
            case 717:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:16") ;
                item.put("tv3", "22:31") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "6:07") ;
                item.put("tv3", "23:52") ;
                data.add(item) ;
                break;
            }
            case 718:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:12") ;
                item.put("tv3", "22:27") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "6:11") ;
                item.put("tv3", "23:56") ;
                data.add(item) ;
                break;
            }
            case 719:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 北京西站") ;
                item.put("tv2", "5:10") ;
                item.put("tv3", "22:25") ;
                data.add(item) ;
                /*item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l7) ;
                item.put("tv1", "开往 焦化厂") ;
                item.put("tv2", "6:00") ;
                item.put("tv3", "23:45") ;
                data.add(item) ;*/
                break;
            }
            case 801:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "5:10") ;
                item.put("tv3", "22:05") ;
                data.add(item) ;
                /*item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "6:00") ;
                item.put("tv3", "23:45") ;
                data.add(item) ;*/
                break;
            }
            case 802:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "5:13") ;
                item.put("tv3", "22:08") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "4:58") ;
                item.put("tv3", "23:43") ;
                data.add(item) ;
                break;
            }
            case 803:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "5:16") ;
                item.put("tv3", "22:11") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "4:55") ;
                item.put("tv3", "23:43") ;
                data.add(item) ;
                break;
            }
            case 804:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "4:45") ;
                item.put("tv3", "22:14") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "6:07") ;
                item.put("tv3", "23:37") ;
                data.add(item) ;
                break;
            }
            case 805:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "4:47") ;
                item.put("tv3", "22:17") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "6:04") ;
                item.put("tv3", "23:34") ;
                data.add(item) ;
                break;
            }
            case 806:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "4:50") ;
                item.put("tv3", "22:20") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "6:01") ;
                item.put("tv3", "23:31") ;
                data.add(item) ;
                break;
            }
            case 807:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "4:53") ;
                item.put("tv3", "22:22") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "5:58") ;
                item.put("tv3", "23:28") ;
                data.add(item) ;
                break;
            }
            case 808:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "4:55") ;
                item.put("tv3", "22:25") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "5:56") ;
                item.put("tv3", "23:26") ;
                data.add(item) ;
                break;
            }
            case 809:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "4:59") ;
                item.put("tv3", "22:29") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "5:52") ;
                item.put("tv3", "23:22") ;
                data.add(item) ;
                break;
            }
            case 810:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "5:03") ;
                item.put("tv3", "22:33") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "5:49") ;
                item.put("tv3", "23:19") ;
                data.add(item) ;
                break;
            }
            case 811:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "5:05") ;
                item.put("tv3", "22:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "5:47") ;
                item.put("tv3", "23:17") ;
                data.add(item) ;
                break;
            }
            case 812:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "5:08") ;
                item.put("tv3", "22:38") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "5:43") ;
                item.put("tv3", "23:13") ;
                data.add(item) ;
                break;
            }
            case 813:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "5:10") ;
                item.put("tv3", "22:40") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "5:41") ;
                item.put("tv3", "23:11") ;
                data.add(item) ;
                break;
            }
            case 814:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "5:12") ;
                item.put("tv3", "22:42") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "5:39") ;
                item.put("tv3", "23:09") ;
                data.add(item) ;
                break;
            }
            case 815:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "5:15") ;
                item.put("tv3", "22:44") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "5:36") ;
                item.put("tv3", "23:06") ;
                data.add(item) ;
                break;
            }
            case 816:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "5:17") ;
                item.put("tv3", "22:47") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "5:34") ;
                item.put("tv3", "23:04") ;
                data.add(item) ;
                break;
            }
            case 817:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "5:19") ;
                item.put("tv3", "22:49") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "5:32") ;
                item.put("tv3", "23:02") ;
                data.add(item) ;
                break;
            }
            case 818:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 南锣鼓巷") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "23:00") ;
                data.add(item) ;
                /*item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l8) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv2", "5:52") ;
                item.put("tv3", "23:22") ;
                data.add(item) ;*/
                break;
            }
            case 901:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "5:59") ;
                item.put("tv3", "23:19") ;
                data.add(item) ;
                /*item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 国家图书馆") ;
                item.put("tv2", "5:52") ;
                item.put("tv3", "23:22") ;
                data.add(item) ;*/
                break;
            }
            case 902:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "6:01") ;
                item.put("tv3", "23:21") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 国家图书馆") ;
                item.put("tv2", "5:48") ;
                item.put("tv3", "23:08") ;
                data.add(item) ;
                break;
            }
            case 903:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "6:03") ;
                item.put("tv3", "23:23") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 国家图书馆") ;
                item.put("tv2", "5:46") ;
                item.put("tv3", "23:06") ;
                data.add(item) ;
                break;
            }
            case 904:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "6:07") ;
                item.put("tv3", "23:27") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 国家图书馆") ;
                item.put("tv2", "5:43") ;
                item.put("tv3", "23:03") ;
                data.add(item) ;
                break;
            }
            case 905:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "6:10") ;
                item.put("tv3", "23:30") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 国家图书馆") ;
                item.put("tv2", "5:40") ;
                item.put("tv3", "23:00") ;
                data.add(item) ;
                break;
            }
            case 906:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "6:12") ;
                item.put("tv3", "23:32") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 国家图书馆") ;
                item.put("tv2", "5:37") ;
                item.put("tv3", "22:57") ;
                data.add(item) ;
                break;
            }
            case 907:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "6:15") ;
                item.put("tv3", "23:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 国家图书馆") ;
                item.put("tv2", "5:34") ;
                item.put("tv3", "22:54") ;
                data.add(item) ;
                break;
            }
            case 908:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "6:19") ;
                item.put("tv3", "23:39") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 国家图书馆") ;
                item.put("tv2", "5:31") ;
                item.put("tv3", "22:51") ;
                data.add(item) ;
                break;
            }
            case 909:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "6:21") ;
                item.put("tv3", "23:41") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 国家图书馆") ;
                item.put("tv2", "5:28") ;
                item.put("tv3", "22:48") ;
                data.add(item) ;
                break;
            }
            case 910:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "6:23") ;
                item.put("tv3", "23:43") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 国家图书馆") ;
                item.put("tv2", "5:26") ;
                item.put("tv3", "22:46") ;
                data.add(item) ;
                break;
            }
            case 911:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "6:25") ;
                item.put("tv3", "23:45") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 国家图书馆") ;
                item.put("tv2", "5:24") ;
                item.put("tv3", "22:44") ;
                data.add(item) ;
                break;
            }
            case 912:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "6:27") ;
                item.put("tv3", "23:47") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 国家图书馆") ;
                item.put("tv2", "5:22") ;
                item.put("tv3", "22:42") ;
                data.add(item) ;
                break;
            }
            case 913:{
                /*item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "6:12") ;
                item.put("tv3", "23:32") ;
                data.add(item) ;*/
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l9) ;
                item.put("tv1", "开往 国家图书馆") ;
                item.put("tv2", "5:20") ;
                item.put("tv3", "22:40") ;
                data.add(item) ;
                break;
            }
            case 1001:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "4:54") ;
                item.put("tv3", "22:31") ;
                data.add(item) ;
                /*item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "22:57") ;
                data.add(item) ;*/
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:55") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "4:49") ;
                item.put("tv3", "22:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "0:20") ;
                data.add(item) ;
                break;
            }
            case 1002:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "4:56") ;
                item.put("tv3", "20:48") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "22:34") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:58") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "6:19") ;
                item.put("tv3", "22:32") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "0:18") ;
                data.add(item) ;
                break;
            }
            case 1003:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "4:58") ;
                item.put("tv3", "20:50") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "22:36") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:00") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "6:18") ;
                item.put("tv3", "22:31") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "0:16") ;
                data.add(item) ;
                break;
            }
            case 1004:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:00") ;
                item.put("tv3", "20:52") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "22:37") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:01") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "6:15") ;
                item.put("tv3", "22:28") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "0:14") ;
                data.add(item) ;
                break;
            }
            case 1005:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:02") ;
                item.put("tv3", "20:54") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "22:40") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:04") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "6:14") ;
                item.put("tv3", "22:27") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "0:12") ;
                data.add(item) ;
                break;
            }
            case 1006:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:04") ;
                item.put("tv3", "20:56") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "22:42") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:06") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "6:11") ;
                item.put("tv3", "22:24") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "0:10") ;
                data.add(item) ;
                break;
            }
            case 1007:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:06") ;
                item.put("tv3", "20:58") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "22:44") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:08") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "6:09") ;
                item.put("tv3", "22:22") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "0:07") ;
                data.add(item) ;
                break;
            }
            case 1008:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:08") ;
                item.put("tv3", "21:00") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "22:46") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:10") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "6:07") ;
                item.put("tv3", "22:20") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "0:05") ;
                data.add(item) ;
                break;
            }
            case 1009:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:10") ;
                item.put("tv3", "21:03") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "22:48") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:12") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "6:05") ;
                item.put("tv3", "22:18") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "0:03") ;
                data.add(item) ;
                break;
            }
            case 1010:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:12") ;
                item.put("tv3", "21:05") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "22:50") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:14") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "6:03") ;
                item.put("tv3", "22:16") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "0:01") ;
                data.add(item) ;
                break;
            }
            case 1011:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:15") ;
                item.put("tv3", "21:07") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "22:53") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:17") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "6:01") ;
                item.put("tv3", "22:14") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:59") ;
                data.add(item) ;
                break;
            }
            case 1012:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:18") ;
                item.put("tv3", "21:10") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "22:56") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:20") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:58") ;
                item.put("tv3", "22:11") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:56") ;
                data.add(item) ;
                break;
            }
            case 1013:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:20") ;
                item.put("tv3", "21:12") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "22:57") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:17") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:55") ;
                item.put("tv3", "22:08") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:54") ;
                data.add(item) ;
                break;
            }
            case 1014:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:22") ;
                item.put("tv3", "21:15") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:00") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:24") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:53") ;
                item.put("tv3", "22:06") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:51") ;
                data.add(item) ;
                break;
            }
            case 1015:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:25") ;
                item.put("tv3", "21:17") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:03") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:27") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:50") ;
                item.put("tv3", "22:03") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:48") ;
                data.add(item) ;
                break;
            }
            case 1016:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:27") ;
                item.put("tv3", "21:19") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:05") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:29") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:48") ;
                item.put("tv3", "22:01") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:46") ;
                data.add(item) ;
                break;
            }
            case 1017:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:29") ;
                item.put("tv3", "21:21") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:06") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:30") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:46") ;
                item.put("tv3", "21:59") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:45") ;
                data.add(item) ;
                break;
            }
            case 1018:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:31") ;
                item.put("tv3", "21:23") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:09") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:33") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:44") ;
                item.put("tv3", "21:57") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:43") ;
                data.add(item) ;
                break;
            }
            case 1019:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:33") ;
                item.put("tv3", "21:25") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:10") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:34") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:42") ;
                item.put("tv3", "21:55") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:41") ;
                data.add(item) ;
                break;
            }
            case 1020:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:35") ;
                item.put("tv3", "21:27") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:13") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:37") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:41") ;
                item.put("tv3", "21:54") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:39") ;
                data.add(item) ;
                break;
            }
            case 1021:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:37") ;
                item.put("tv3", "21:30") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:15") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:39") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:38") ;
                item.put("tv3", "21:51") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:36") ;
                data.add(item) ;
                break;
            }
            case 1022:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:39") ;
                item.put("tv3", "21:32") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:17") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:41") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:36") ;
                item.put("tv3", "21:49") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:34") ;
                data.add(item) ;
                break;
            }
            case 1023:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:41") ;
                item.put("tv3", "21:34") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:19") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:43") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:34") ;
                item.put("tv3", "21:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:32") ;
                data.add(item) ;
                break;
            }
            case 1024:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:43") ;
                item.put("tv3", "21:36") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:21") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:45") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:32") ;
                item.put("tv3", "21:45") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:30") ;
                data.add(item) ;
                break;
            }
            case 1025:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:46") ;
                item.put("tv3", "21:38") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:24") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "23:48") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:29") ;
                item.put("tv3", "21:42") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:27") ;
                data.add(item) ;
                break;
            }
            case 1026:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:48") ;
                item.put("tv3", "21:40") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:26") ;
                data.add(item) ;

                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:27") ;
                item.put("tv3", "21:40") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:25") ;
                data.add(item) ;
                break;
            }
            case 1027:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:35") ;
                item.put("tv3", "21:43") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:29") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:08") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:37") ;
                item.put("tv3", "21:37") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:22") ;
                data.add(item) ;
                break;
            }
            case 1028:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:37") ;
                item.put("tv3", "21:45") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:31") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:10") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:34") ;
                item.put("tv3", "21:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:20") ;
                data.add(item) ;
                break;
            }
            case 1029:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:39") ;
                item.put("tv3", "21:48") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:33") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:13") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:32") ;
                item.put("tv3", "21:33") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:18") ;
                data.add(item) ;
                break;
            }
            case 1030:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:41") ;
                item.put("tv3", "21:50") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:15") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "21:30") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:16") ;
                data.add(item) ;
                break;
            }
            case 1031:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:44") ;
                item.put("tv3", "21:52") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:37") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:10") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:28") ;
                item.put("tv3", "21:28") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:14") ;
                data.add(item) ;
                break;
            }
            case 1032:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:47") ;
                item.put("tv3", "21:55") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:41") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:10") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:24") ;
                item.put("tv3", "21:25") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:10") ;
                data.add(item) ;
                break;
            }
            case 1033:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:49") ;
                item.put("tv3", "21:57") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:43") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:22") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:22") ;
                item.put("tv3", "21:23") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:08") ;
                data.add(item) ;
                break;
            }
            case 1034:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:52") ;
                item.put("tv3", "22:01") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:46") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:25") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:20") ;
                item.put("tv3", "21:21") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:06") ;
                data.add(item) ;
                break;
            }
            case 1035:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:55") ;
                item.put("tv3", "22:04") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:49") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:28") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:17") ;
                item.put("tv3", "21:17") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "23:02") ;
                data.add(item) ;
                break;
            }
            case 1036:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:57") ;
                item.put("tv3", "22:06") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:51") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:30") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:14") ;
                item.put("tv3", "21:14") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "22:59") ;
                data.add(item) ;
                break;
            }
            case 1037:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "5:59") ;
                item.put("tv3", "22:09") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:53") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:33") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:12") ;
                item.put("tv3", "21:12") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "22:57") ;
                data.add(item) ;
                break;
            }
            case 1038:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "6:02") ;
                item.put("tv3", "22:11") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:56") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:10") ;
                item.put("tv3", "21:10") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "22:55") ;
                data.add(item) ;
                break;
            }
            case 1039:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "6:06") ;
                item.put("tv3", "22:15") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "23:59") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:39") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:06") ;
                item.put("tv3", "21:06") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "22:51") ;
                data.add(item) ;
                break;
            }
            case 1040:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "6:08") ;
                item.put("tv3", "22:17") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "0:02") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:41") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:04") ;
                item.put("tv3", "21:04") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "22:49") ;
                data.add(item) ;
                break;
            }
            case 1041:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "6:11") ;
                item.put("tv3", "22:20") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "0:05") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:44") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "5:01") ;
                item.put("tv3", "21:01") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "22:46") ;
                data.add(item) ;
                break;
            }
            case 1042:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "6:13") ;
                item.put("tv3", "22:23") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "0:07") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:47") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "4:59") ;
                item.put("tv3", "20:59") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "22:44") ;
                data.add(item) ;
                break;
            }
            case 1043:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "4:47") ;
                item.put("tv3", "22:25") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "0:09") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:49") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "4:56") ;
                item.put("tv3", "22:41") ;
                data.add(item) ;

                break;
            }
            case 1044:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "4:49") ;
                item.put("tv3", "22:27") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "0:12") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:25") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "4:54") ;
                item.put("tv3", "22:39") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "0:24") ;
                data.add(item) ;
                break;
            }
            case 1045:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环") ;
                item.put("tv2", "4:51") ;
                item.put("tv3", "22:29") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站巴沟") ;
                item.put("tv3", "0:14") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "内环 终点站成寿寺") ;
                item.put("tv3", "22:53") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环") ;
                item.put("tv2", "4:52") ;
                item.put("tv3", "22:37") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l10) ;
                item.put("tv1", "外环 终点站车道沟") ;
                item.put("tv3", "0:22") ;
                data.add(item) ;
                break;
            }
            case 1301:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 东直门") ;
                item.put("tv2", "5:35") ;
                item.put("tv3", "22:42") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 霍营") ;
                item.put("tv3", "23:45") ;
                data.add(item) ;
                break;
            }
            case 1302:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 东直门") ;
                item.put("tv2", "5:39") ;
                item.put("tv3", "22:46") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 霍营") ;
                item.put("tv3", "23:49") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 西直门") ;
                item.put("tv2", "5:26") ;
                item.put("tv3", "23:34") ;
                data.add(item) ;
                break;
            }
            case 1303:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 东直门") ;
                item.put("tv2", "5:42") ;
                item.put("tv3", "22:49") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 霍营") ;
                item.put("tv3", "23:52") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 西直门") ;
                item.put("tv2", "5:24") ;
                item.put("tv3", "23:32") ;
                data.add(item) ;
                break;
            }
            case 1304:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 东直门") ;
                item.put("tv2", "5:44") ;
                item.put("tv3", "22:51") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 霍营") ;
                item.put("tv3", "23:54") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 西直门") ;
                item.put("tv2", "5:21") ;
                item.put("tv3", "23:29") ;
                data.add(item) ;
                break;
            }
            case 1305:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 东直门") ;
                item.put("tv2", "5:50") ;
                item.put("tv3", "22:57") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 霍营") ;
                item.put("tv3", "0:00") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 西直门") ;
                item.put("tv2", "5:15") ;
                item.put("tv3", "23:23") ;
                data.add(item) ;
                break;
            }
            case 1306:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 东直门") ;
                item.put("tv2", "5:54") ;
                item.put("tv3", "23:01") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 霍营") ;
                item.put("tv3", "0:04") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 西直门") ;
                item.put("tv2", "5:11") ;
                item.put("tv3", "23:19") ;
                data.add(item) ;
                break;
            }
            case 1307:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 东直门") ;
                item.put("tv2", "5:59") ;
                item.put("tv3", "23:06") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 霍营") ;
                item.put("tv3", "0:09") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 西直门") ;
                item.put("tv2", "5:06") ;
                item.put("tv3", "23:14") ;
                data.add(item) ;
                break;
            }
            case 1308:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 东直门") ;
                item.put("tv2", "6:01") ;
                item.put("tv3", "23:11") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 霍营") ;
                item.put("tv3", "0:11") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 西直门") ;
                item.put("tv2", "5:03") ;
                item.put("tv3", "23:08") ;
                data.add(item) ;
                break;
            }

            case 1309:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 东直门") ;
                item.put("tv2", "5:00") ;
                item.put("tv3", "23:11") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 西直门") ;
                item.put("tv2", "5:00") ;
                item.put("tv3", "23:08") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 回龙观") ;
                item.put("tv3", "0:11") ;
                data.add(item) ;
                break;
            }
            case 1310:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 东直门") ;
                item.put("tv2", "5:05") ;
                item.put("tv3", "23:17") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 西直门") ;
                item.put("tv2", "5:56") ;
                item.put("tv3", "23:03") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 回龙观") ;
                item.put("tv3", "0:06") ;
                data.add(item) ;
                break;
            }
            case 1311:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 东直门") ;
                item.put("tv2", "5:08") ;
                item.put("tv3", "23:20") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 西直门") ;
                item.put("tv2", "5:52") ;
                item.put("tv3", "22:59") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 回龙观") ;
                item.put("tv3", "0:02") ;
                data.add(item) ;
                break;
            }
            case 1312:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 东直门") ;
                item.put("tv2", "5:16") ;
                item.put("tv3", "23:27") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 西直门") ;
                item.put("tv2", "5:45") ;
                item.put("tv3", "22:52") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 回龙观") ;
                item.put("tv3", "23:55") ;
                data.add(item) ;
                break;
            }
            case 1313:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 东直门") ;
                item.put("tv2", "5:19") ;
                item.put("tv3", "23:30") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 西直门") ;
                item.put("tv2", "5:42") ;
                item.put("tv3", "22:49") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 回龙观") ;
                item.put("tv3", "23:52") ;
                data.add(item) ;
                break;
            }
            case 1314:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 东直门") ;
                item.put("tv2", "5:21") ;
                item.put("tv3", "23:33") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 西直门") ;
                item.put("tv2", "5:39") ;
                item.put("tv3", "22:46") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 回龙观") ;
                item.put("tv3", "23:49") ;
                data.add(item) ;
                break;
            }
            case 1315:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 东直门") ;
                item.put("tv2", "5:23") ;
                item.put("tv3", "23:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 西直门") ;
                item.put("tv2", "5:37") ;
                item.put("tv3", "22:44") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 回龙观") ;
                item.put("tv3", "23:47") ;
                data.add(item) ;
                break;
            }
            case 1316:{

                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 西直门") ;
                item.put("tv2", "5:35") ;
                item.put("tv3", "22:42") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l13) ;
                item.put("tv1", "开往 回龙观") ;
                item.put("tv3", "23:45") ;
                data.add(item) ;
                break;
            }
            case 14101:{
                /*item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 西局") ;
                item.put("tv2", "5:39") ;
                item.put("tv3", "22:46") ;
                data.add(item) ;*/
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 张郭庄") ;
                item.put("tv2", "5:45") ;
                item.put("tv3", "22:10") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 大瓦窑") ;
                item.put("tv3", "22:30") ;
                data.add(item) ;
                break;
            }
            case 14102:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 西局") ;
                item.put("tv2", "5:45") ;
                item.put("tv3", "22:24") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 张郭庄") ;
                item.put("tv2", "5:46") ;
                item.put("tv3", "22:11") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 大瓦窑") ;
                item.put("tv3", "22:31") ;
                data.add(item) ;
                break;
            }
            case 14103:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 西局") ;
                item.put("tv2", "5:42") ;
                item.put("tv3", "22:22") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 张郭庄") ;
                item.put("tv2", "5:48") ;
                item.put("tv3", "22:13") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 大瓦窑") ;
                item.put("tv3", "22:33") ;
                data.add(item) ;
                break;
            }
            case 14104:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 西局") ;
                item.put("tv2", "5:39") ;
                item.put("tv3", "22:19") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 张郭庄") ;
                item.put("tv2", "5:51") ;
                item.put("tv3", "22:16") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 大瓦窑") ;
                item.put("tv3", "22:36") ;
                data.add(item) ;
                break;
            }
            case 14105:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 西局") ;
                item.put("tv2", "5:36") ;
                item.put("tv3", "22:16") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 张郭庄") ;
                item.put("tv2", "5:54") ;
                item.put("tv3", "22:19") ;
                data.add(item) ;
            }
            case 14106:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 西局") ;
                item.put("tv2", "5:31") ;
                item.put("tv3", "22:11") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 张郭庄") ;
                item.put("tv2", "5:58") ;
                item.put("tv3", "22:23") ;
                data.add(item) ;
            }
            case 14107:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 西局") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "22:10") ;
                data.add(item) ;
            }
            case 14901:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "5:45") ;
                item.put("tv3", "22:24") ;
                data.add(item) ;

                /*item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "22:40") ;
                data.add(item) ;*/
                break;
            }

            case 14902:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "5:33") ;
                item.put("tv3", "22:43") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:48") ;
                item.put("tv3", "23:18") ;
                data.add(item) ;
                break;
            }

            case 14903:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "5:35") ;
                item.put("tv3", "22:45") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:46") ;
                item.put("tv3", "23:16") ;
                data.add(item) ;
                break;
            }
            case 14904:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "5:38") ;
                item.put("tv3", "22:48") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:44") ;
                item.put("tv3", "23:14") ;
                data.add(item) ;
                break;
            }
            case 14905:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "5:40") ;
                item.put("tv3", "22:50") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:41") ;
                item.put("tv3", "23:11") ;
                data.add(item) ;
                break;
            }
            case 14906:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "5:43") ;
                item.put("tv3", "22:53") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:38") ;
                item.put("tv3", "23:08") ;
                data.add(item) ;
                break;
            }
            case 14907:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "5:47") ;
                item.put("tv3", "22:47") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:34") ;
                item.put("tv3", "23:04") ;
                data.add(item) ;
                break;
            }
            case 14908:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "5:51") ;
                item.put("tv3", "23:01") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "23:00") ;
                data.add(item) ;
                break;
            }
            case 14909:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "5:54") ;
                item.put("tv3", "23:04") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:27") ;
                item.put("tv3", "22:57") ;
                data.add(item) ;
                break;
            }

            case 14910:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "5:57") ;
                item.put("tv3", "23:07") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:24") ;
                item.put("tv3", "22:54") ;
                data.add(item) ;
                break;
            }

            case 14911:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "5:59") ;
                item.put("tv3", "23:09") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:22") ;
                item.put("tv3", "22:52") ;
                data.add(item) ;
                break;
            }

            case 14912:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "6:02") ;
                item.put("tv3", "23:12") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:20") ;
                item.put("tv3", "22:50") ;
                data.add(item) ;
                break;
            }

            case 14913:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "6:05") ;
                item.put("tv3", "23:15") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:17") ;
                item.put("tv3", "22:47") ;
                data.add(item) ;
                break;
            }

            case 14914:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "6:07") ;
                item.put("tv3", "23:17") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:14") ;
                item.put("tv3", "22:44") ;
                data.add(item) ;
                break;
            }

            case 14915:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "6:11") ;
                item.put("tv3", "23:21") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:10") ;
                item.put("tv3", "22:40") ;
                data.add(item) ;
                break;
            }

            case 14916:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "6:13") ;
                item.put("tv3", "23:23") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:08") ;
                item.put("tv3", "22:38") ;
                data.add(item) ;
                break;
            }

            case 14917:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "6:15") ;
                item.put("tv3", "23:25") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:06") ;
                item.put("tv3", "22:36") ;
                data.add(item) ;
                break;
            }
            case 14918:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "6:18") ;
                item.put("tv3", "23:28") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:04") ;
                item.put("tv3", "22:34") ;
                data.add(item) ;
                break;
            }

            case 14919:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "6:15") ;
                item.put("tv3", "23:25") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:06") ;
                item.put("tv3", "22:36") ;
                data.add(item) ;
                break;
            }


            case 14920:{
                /*item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 善各庄") ;
                item.put("tv2", "6:15") ;
                item.put("tv3", "23:25") ;
                data.add(item) ;*/
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l14) ;
                item.put("tv1", "开往 北京南站") ;
                item.put("tv2", "5:00") ;
                item.put("tv3", "22:30") ;
                data.add(item) ;
                break;
            }


            case 1501:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "22:11") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 马泉营") ;
                item.put("tv3", "23:18") ;
                data.add(item) ;
                /*item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "23:45") ;
                data.add(item) ;*/
                break;
            }
            case 1502:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:33") ;
                item.put("tv3", "22:11") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 马泉营") ;
                item.put("tv3", "23:21") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "5:54") ;
                item.put("tv3", "24:09") ;
                data.add(item) ;
                break;
            }
            case 1503:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:35") ;
                item.put("tv3", "22:16") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 马泉营") ;
                item.put("tv3", "23:23") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "5:52") ;
                item.put("tv3", "24:07") ;
                data.add(item) ;
                break;
            }
            case 1504:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:39") ;
                item.put("tv3", "22:20") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 马泉营") ;
                item.put("tv3", "23:27") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "5:49") ;
                item.put("tv3", "24:03") ;
                data.add(item) ;
                break;
            }
            case 1505:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:44") ;
                item.put("tv3", "22:25") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 马泉营") ;
                item.put("tv3", "23:32") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "5:44") ;
                item.put("tv3", "23:59") ;
                data.add(item) ;
                break;
            }
            case 1506:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:47") ;
                item.put("tv3", "22:28") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 马泉营") ;
                item.put("tv3", "23:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "5:40") ;
                item.put("tv3", "23:55") ;
                data.add(item) ;
                break;
            }
            case 1507:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:50") ;
                item.put("tv3", "22:31") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 马泉营") ;
                item.put("tv3", "23:38") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "5:38") ;
                item.put("tv3", "23:52") ;
                data.add(item) ;
                break;
            }
            case 1508:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:54") ;
                item.put("tv3", "22:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 马泉营") ;
                item.put("tv3", "23:42") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "5:34") ;
                item.put("tv3", "23:49") ;
                data.add(item) ;
                break;
            }

            case 1509:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:07") ;
                item.put("tv3", "22:40") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "23:45") ;
                data.add(item) ;
                break;
            }
            case 1510:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:07") ;
                item.put("tv3", "22:40") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "23:45") ;
                data.add(item) ;
                break;
            }
            case 1511:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:10") ;
                item.put("tv3", "22:43") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "6:09") ;
                item.put("tv3", "23:42") ;
                data.add(item) ;
                break;
            }
            case 1512:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:13") ;
                item.put("tv3", "22:46") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "6:06") ;
                item.put("tv3", "23:39") ;
                data.add(item) ;
                break;
            }
            case 1513:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:16") ;
                item.put("tv3", "22:49") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "6:03") ;
                item.put("tv3", "23:36") ;
                data.add(item) ;
                break;
            }
            case 1514:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:19") ;
                item.put("tv3", "22:52") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "6:00") ;
                item.put("tv3", "23:33") ;
                data.add(item) ;
                break;
            }
            case 1515:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:23") ;
                item.put("tv3", "22:55") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "5:56") ;
                item.put("tv3", "23:29") ;
                data.add(item) ;
                break;
            }
            case 1516:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:26") ;
                item.put("tv3", "22:58") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "5:54") ;
                item.put("tv3", "23:27") ;
                data.add(item) ;
                break;
            }
            case 1517:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:27") ;
                item.put("tv3", "23:00") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "5:51") ;
                item.put("tv3", "23:24") ;
                data.add(item) ;
                break;
            }
            case 1518:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:30") ;
                item.put("tv3", "23:03") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "5:49") ;
                item.put("tv3", "23:22") ;
                data.add(item) ;
                break;
            }
            case 1519:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:33") ;
                item.put("tv3", "23:05") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "5:46") ;
                item.put("tv3", "23:19") ;
                data.add(item) ;
                break;
            }
            case 1520:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:35") ;
                item.put("tv3", "23:08") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "5:44") ;
                item.put("tv3", "23:17") ;
                data.add(item) ;
                break;
            }
            case 1521:{
                /*item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 清华东路西口") ;
                item.put("tv2", "5:35") ;
                item.put("tv3", "23:08") ;
                data.add(item) ;*/
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l15) ;
                item.put("tv1", "开往 俸伯") ;
                item.put("tv2", "5:42") ;
                item.put("tv3", "23:17") ;
                data.add(item) ;
                break;
            }

            case 2401:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 次渠") ;
                item.put("tv2", "6:00") ;
                item.put("tv3", "23:02") ;
                data.add(item) ;
                /*item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:44") ;
                item.put("tv3", "23:17") ;
                data.add(item) ;*/
                break;
            }

            case 2402:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 次渠") ;
                item.put("tv2", "6:03") ;
                item.put("tv3", "23:06") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:51") ;
                item.put("tv3", "22:53") ;
                data.add(item) ;
                break;
            }
            case 2403:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 次渠") ;
                item.put("tv2", "6:05") ;
                item.put("tv3", "23:08") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:49") ;
                item.put("tv3", "22:51") ;
                data.add(item) ;
                break;
            }
            case 2404:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 次渠") ;
                item.put("tv2", "6:09") ;
                item.put("tv3", "23:11") ;
                        data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:45") ;
                item.put("tv3", "22:47") ;
                data.add(item) ;
                break;
            }
            case 2405:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 次渠") ;
                item.put("tv2", "6:11") ;
                item.put("tv3", "23:14") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:43") ;
                item.put("tv3", "22:45") ;
                data.add(item) ;
                break;
            }
            case 2406:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 次渠") ;
                item.put("tv2", "6:13") ;
                item.put("tv3", "23:16") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:41") ;
                item.put("tv3", "22:43") ;
                data.add(item) ;
                break;
            }
            case 2407:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 次渠") ;
                item.put("tv2", "6:16") ;
                item.put("tv3", "23:18") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:38") ;
                item.put("tv3", "22:40") ;
                data.add(item) ;
                break;
            }
            case 2408:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 次渠") ;
                item.put("tv2", "6:18") ;
                item.put("tv3", "23:21") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:36") ;
                item.put("tv3", "22:38") ;
                data.add(item) ;
                break;
            }
            case 2409:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 次渠") ;
                item.put("tv2", "6:20") ;
                item.put("tv3", "23:23") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:34") ;
                item.put("tv3", "22:36") ;
                data.add(item) ;
                break;
            }
            case 2410:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 次渠") ;
                item.put("tv2", "6:24") ;
                item.put("tv3", "23:26") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:31") ;
                item.put("tv3", "22:33") ;
                data.add(item) ;
                break;
            }
            case 2411:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 次渠") ;
                item.put("tv2", "6:27") ;
                item.put("tv3", "23:29") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:28") ;
                item.put("tv3", "22:30") ;
                data.add(item) ;
                break;
            }

            case 2412:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 次渠") ;
                item.put("tv2", "6:29") ;
                item.put("tv3", "23:32") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 宋家庄") ;
                item.put("tv2", "5:25") ;
                item.put("tv3", "22:27") ;
                data.add(item) ;
                break;
            }
            case 2413:{
                /*item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 苏庄") ;
                item.put("tv2", "5:58") ;
                item.put("tv3", "23:32") ;
                data.add(item) ;*/
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l24) ;
                item.put("tv1", "开往 郭公庄") ;
                item.put("tv2", "5:23") ;
                item.put("tv3", "22:25") ;
                data.add(item) ;
                break;
            }

            case 2701:{
                /*item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 西二旗") ;
                item.put("tv2", "6:03") ;
                item.put("tv3", "23:06") ;
                data.add(item) ;*/
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 昌平西山口") ;
                item.put("tv2", "5:50") ;
                item.put("tv3", "23:05") ;
                data.add(item) ;
                break;
            }
            case 2702:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 西二旗") ;
                item.put("tv2", "5:40") ;
                item.put("tv3", "22:54") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 昌平西山口") ;
                item.put("tv2", "5:55") ;
                item.put("tv3", "23:10") ;
                data.add(item) ;
                break;
            }
            case 2703:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 西二旗") ;
                item.put("tv2", "5:37") ;
                item.put("tv3", "22:51") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 昌平西山口") ;
                item.put("tv2", "5:40") ;
                item.put("tv3", "23:14") ;
                data.add(item) ;
                break;
            }
            case 2704:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 西二旗") ;
                item.put("tv2", "6:11") ;
                item.put("tv3", "22:46") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv3", "23:31") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 昌平西山口") ;
                item.put("tv2", "5:44") ;
                item.put("tv3", "23:18") ;
                data.add(item) ;
                break;
            }
            case 2705:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 西二旗") ;
                item.put("tv2", "6:08") ;
                item.put("tv3", "22:43") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv3", "23:28") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 昌平西山口") ;
                item.put("tv2", "5:47") ;
                item.put("tv3", "23:21") ;
                data.add(item) ;
                break;
            }
            case 2706:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 西二旗") ;
                item.put("tv2", "6:05") ;
                item.put("tv3", "22:40") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv3", "23:25") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 昌平西山口") ;
                item.put("tv2", "5:50") ;
                item.put("tv3", "23:24") ;
                data.add(item) ;
                break;
            }
            case 2707:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 西二旗") ;
                item.put("tv2", "6:00") ;
                item.put("tv3", "22:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv3", "23:20") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 昌平西山口") ;
                item.put("tv2", "5:55") ;
                item.put("tv3", "23:29") ;
                data.add(item) ;
                break;
            }
            case 2708:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 西二旗") ;
                item.put("tv2", "5:57") ;
                item.put("tv3", "22:32") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv3", "23:17") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 昌平西山口") ;
                item.put("tv2", "5:58") ;
                item.put("tv3", "23:32") ;
                data.add(item) ;
                break;
            }
            case 2709:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 西二旗") ;
                item.put("tv2", "5:54") ;
                item.put("tv3", "22:29") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv3", "23:14") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 昌平西山口") ;
                item.put("tv2", "6:00") ;
                item.put("tv3", "23:35") ;
                data.add(item) ;
                break;
            }
            case 2710:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 西二旗") ;
                item.put("tv2", "5:51") ;
                item.put("tv3", "22:26") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv3", "23:11") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 昌平西山口") ;
                item.put("tv2", "6:03") ;
                item.put("tv3", "23:38") ;
                data.add(item) ;
                break;
            }
            case 2711:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 西二旗") ;
                item.put("tv2", "5:47") ;
                item.put("tv3", "22:22") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv3", "23:07") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 昌平西山口") ;
                item.put("tv2", "6:07") ;
                item.put("tv3", "23:42") ;
                data.add(item) ;
                break;
            }
            case 2712:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 西二旗") ;
                item.put("tv2", "5:45") ;
                item.put("tv3", "22:20") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l27) ;
                item.put("tv1", "开往 朱辛庄") ;
                item.put("tv3", "23:05") ;
                data.add(item) ;
                break;
            }
            case 3001:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 土桥") ;
                item.put("tv2", "6:00") ;
                item.put("tv3", "23:22") ;
                data.add(item) ;
                /*item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 四惠") ;
                item.put("tv2", "5:55") ;
                item.put("tv3", "23:10") ;
                data.add(item) ;*/
                break;
            }
            case 3002:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 土桥") ;
                item.put("tv2", "6:02") ;
                item.put("tv3", "23:24") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 四惠") ;
                item.put("tv2", "5:48") ;
                item.put("tv3", "23:10") ;
                data.add(item) ;
                break;
            }
            case 3003:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 土桥") ;
                item.put("tv2", "6:05") ;
                item.put("tv3", "23:27") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 四惠") ;
                item.put("tv2", "5:46") ;
                item.put("tv3", "23:08") ;
                data.add(item) ;
                break;
            }
            case 3004:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 土桥") ;
                item.put("tv2", "6:08") ;
                item.put("tv3", "23:30") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 四惠") ;
                item.put("tv2", "5:43") ;
                item.put("tv3", "23:05") ;
                data.add(item) ;
                break;
            }
            case 3005:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 土桥") ;
                item.put("tv2", "6:11") ;
                item.put("tv3", "23:33") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 四惠") ;
                item.put("tv2", "5:40") ;
                item.put("tv3", "23:02") ;
                data.add(item) ;
                break;
            }
            case 3006:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 土桥") ;
                item.put("tv2", "6:13") ;
                item.put("tv3", "23:35") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 四惠") ;
                item.put("tv2", "5:37") ;
                item.put("tv3", "22:59") ;
                data.add(item) ;
                break;
            }
            case 3007:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 土桥") ;
                item.put("tv2", "6:16") ;
                item.put("tv3", "23:38") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 四惠") ;
                item.put("tv2", "5:33") ;
                item.put("tv3", "22:55") ;
                data.add(item) ;
                break;
            }
            case 3008:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 土桥") ;
                item.put("tv2", "6:19") ;
                item.put("tv3", "23:41") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 四惠") ;
                item.put("tv2", "5:31") ;
                item.put("tv3", "22:53") ;
                data.add(item) ;
                break;
            }
            case 3009:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 土桥") ;
                item.put("tv2", "6:22") ;
                item.put("tv3", "23:44") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 四惠") ;
                item.put("tv2", "5:28") ;
                item.put("tv3", "22:50") ;
                data.add(item) ;
                break;
            }
            case 3010:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 土桥") ;
                item.put("tv2", "6:24") ;
                item.put("tv3", "23:46") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 四惠") ;
                item.put("tv2", "5:26") ;
                item.put("tv3", "22:48") ;
                data.add(item) ;
                break;
            }
            case 3011:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 土桥") ;
                item.put("tv2", "6:26") ;
                item.put("tv3", "23:48") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 四惠") ;
                item.put("tv2", "5:24") ;
                item.put("tv3", "22:46") ;
                data.add(item) ;
                break;
            }
            case 3012:{
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 土桥") ;
                item.put("tv2", "6:29") ;
                item.put("tv3", "23:50") ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 四惠") ;
                item.put("tv2", "5:21") ;
                item.put("tv3", "22:43") ;
                data.add(item) ;
                break;
            }
            case 3013:{

                item = new HashMap<String,Object>() ;
                item.put("img", R.drawable.l30) ;
                item.put("tv1", "开往 四惠") ;
                item.put("tv2", "5:20") ;
                item.put("tv3", "22:42") ;
                data.add(item) ;
                break;
            }

        }


        return data ;
    };
}
