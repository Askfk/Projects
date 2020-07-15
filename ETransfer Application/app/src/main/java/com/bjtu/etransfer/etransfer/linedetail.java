package com.bjtu.etransfer.etransfer;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 继承simpleAdapter实现listView每个Item上面的按钮添加事件
 *
 */
public class linedetail extends AppCompatActivity {
    private SharedPreferences sharedPreferences2,sharedPreferences3;
    private int j,k;
    private ListView lv ;       //声明一个列表

    /* 显示ListView的两种方法：
     * 1)在activity对应的布局里声明ListView控件，使用findViewById初始listView对象，最后listView.setAdapter显示listVIew
     * 2)直接初始化ListVIew = new ListView(this),setAdapter后，通过setContentView(listView)把listView显示出来
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_linedetail);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebtn);
        lv = (ListView)findViewById(R.id.listviewlinedetail) ;
        sharedPreferences2 = getSharedPreferences("users2",Context.MODE_PRIVATE);
        sharedPreferences3 = getSharedPreferences("users3",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences2.edit();
        k = sharedPreferences2.getInt("Line", 0);
//        lv = new ListView(this) ;
        //String[]的img 要和int[]的img名称一致
        MySimpleAdapter adapter = new MySimpleAdapter(this,getData(),R.layout.itemlinedetail,
                new String[]{"view1","img","img2","img3"},
                new int[]{R.id.tv_title1,R.id.iv_logo1,R.id.iv_logo2,R.id.iv_logo3}) ;

        lv.setAdapter(adapter) ;
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


    private ArrayList<Map<String,Object>> getData()
    {
        ArrayList<Map<String,Object>> data = new ArrayList<Map<String,Object>>() ;
        Map<String,Object> item ;
        switch (k){
            case 1:
            {
                item = new HashMap<String,Object>() ;
                item.put("view1", "苹果园") ;
                item.put("img", R.drawable.head1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "古城") ;
                item.put("img", R.drawable.body1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "八角游乐园") ;
                item.put("img", R.drawable.body1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "八宝山") ;
                item.put("img", R.drawable.body1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "玉泉路") ;
                item.put("img", R.drawable.body1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "五棵松") ;
                item.put("img", R.drawable.body1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "万寿路") ;
                item.put("img", R.drawable.body1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "公主坟") ;
                item.put("img", R.drawable.body1) ;
                item.put("img2", R.drawable.l10) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "军事博物馆") ;
                item.put("img", R.drawable.body1) ;
                item.put("img2", R.drawable.l9) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "木樨地") ;
                item.put("img", R.drawable.body1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "南礼士路") ;
                item.put("img", R.drawable.body1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "复兴门") ;
                item.put("img", R.drawable.body1) ;
                item.put("img2", R.drawable.l2) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "西单") ;
                item.put("img", R.drawable.body1) ;
                item.put("img2", R.drawable.l4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "天安门西") ;
                item.put("img", R.drawable.body1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "天安门东") ;
                item.put("img", R.drawable.body1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "王府井") ;
                item.put("img", R.drawable.body1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "东单") ;
                item.put("img", R.drawable.body1) ;
                item.put("img2", R.drawable.l5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "建国门") ;
                item.put("img", R.drawable.body1) ;
                item.put("img2", R.drawable.l2) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "永安里") ;
                item.put("img", R.drawable.body1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "国贸") ;
                item.put("img", R.drawable.body1) ;
                item.put("img2", R.drawable.l10) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "大望路") ;
                item.put("img", R.drawable.body1) ;
                item.put("img2", R.drawable.l14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "四惠") ;
                item.put("img", R.drawable.body1) ;
                item.put("img2", R.drawable.l30) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "四惠东") ;
                item.put("img", R.drawable.foot1) ;
                item.put("img2", R.drawable.l30) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2){
                            case 0:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=101;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=102;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=103;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=104;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=105;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=106;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=107;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 7:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=108;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 8:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=109;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 9:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=110;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 10:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=111;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 11:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=112;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 12:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=113;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 13:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=114;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 14:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=115;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 15:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=116;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 16:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=117;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 17:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=118;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 18:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=119;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 19:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=120;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 20:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=121;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 21:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=122;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 22:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=123;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                        }
                    }

                }) ;
                break;

            }
            case 2:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "西直门") ;
                item.put("img", R.drawable.body2) ;
                item.put("img2", R.drawable.l4) ;
                item.put("img3", R.drawable.l13) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "车公庄") ;
                item.put("img", R.drawable.body2) ;
                item.put("img2", R.drawable.l6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "阜成门") ;
                item.put("img", R.drawable.body2) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "复兴门") ;
                item.put("img", R.drawable.body2) ;
                item.put("img2", R.drawable.l1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "长椿街") ;
                item.put("img", R.drawable.body2) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "宣武门") ;
                item.put("img", R.drawable.body2) ;
                item.put("img2", R.drawable.l4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "和平门") ;
                item.put("img", R.drawable.body2) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "前门") ;
                item.put("img", R.drawable.body2) ;
                //     item.put("img2", R.drawable.l8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "崇文门") ;
                item.put("img", R.drawable.body2) ;
                item.put("img2", R.drawable.l5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "北京站") ;
                item.put("img", R.drawable.body2) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "建国门") ;
                item.put("img", R.drawable.body2) ;
                item.put("img2", R.drawable.l1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "朝阳门") ;
                item.put("img", R.drawable.body2) ;
                item.put("img2", R.drawable.l6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "东四十条") ;
                item.put("img", R.drawable.body2) ;
                //           item.put("img2", R.drawable.l3) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "东直门") ;
                item.put("img", R.drawable.body2) ;
                item.put("img2", R.drawable.l13) ;
                item.put("img3", R.drawable.l31) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "雍和宫") ;
                item.put("img", R.drawable.body2) ;
                item.put("img2", R.drawable.l5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "安定门") ;
                item.put("img", R.drawable.body2) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "鼓楼大街") ;
                item.put("img", R.drawable.body2) ;
                item.put("img2", R.drawable.l8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "积水潭") ;
                item.put("img", R.drawable.body2) ;
                data.add(item) ;

                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=201;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=202;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=203;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=204;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=205;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=206;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=207;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 7:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=208;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 8:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=209;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 9:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=210;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 10:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=211;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 11:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=212;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 12:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=213;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 13:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=214;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 14:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=215;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 15:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=216;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 16:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=217;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 17:{
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j=218;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                        }
                    }
                });
                break;
            }
            case 4:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "安河桥北") ;
                item.put("img", R.drawable.head4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "北宫门") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "西苑") ;
                item.put("img", R.drawable.body4) ;
                item.put("img2", R.drawable.l16) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "圆明园") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "北京大学东门") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "中关村") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "海淀黄庄") ;
                item.put("img", R.drawable.body4) ;
                item.put("img2",R.drawable.l10);
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "人民大学") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "魏公村") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "国家图书馆") ;
                item.put("img", R.drawable.body4) ;
                item.put("img2",R.drawable.l9);
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "动物园") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "西直门") ;
                item.put("img", R.drawable.body4) ;
                item.put("img3", R.drawable.l2) ;
                item.put("img2", R.drawable.l13) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "新街口") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "平安里") ;
                item.put("img", R.drawable.body4) ;
                item.put("img2", R.drawable.l6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "西四") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;item = new HashMap<String,Object>() ;
                item.put("view1", "灵境胡同") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "西单") ;
                item.put("img", R.drawable.body4) ;
                item.put("img2", R.drawable.l1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "宣武门") ;
                item.put("img", R.drawable.body4) ;
                item.put("img2", R.drawable.l2) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "菜市口") ;
                item.put("img", R.drawable.body4) ;
                item.put("img2", R.drawable.l7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "陶然亭") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "北京南站") ;
                item.put("img", R.drawable.body4) ;
                item.put("img2", R.drawable.l14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "马家堡") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "角门西") ;
                item.put("img", R.drawable.body4) ;
                item.put("img2", R.drawable.l10) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "公益西桥") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "新宫") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "西红门") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "高米店北") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "高米店南") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "枣园") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "清源路") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "黄村西大街") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "黄村火车站") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "义和庄") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "生物医药基地") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "天宫院") ;
                item.put("img", R.drawable.body4) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 401;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 402;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 403;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 404;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 405;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 406;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 407;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 7: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 408;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 8: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 409;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 9: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 410;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 10: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 411;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 11: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 412;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 12: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 413;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 13: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 414;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 14: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 415;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 15: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 416;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 16: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 417;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 17: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 418;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 18: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 419;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 19: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 420;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 20: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 421;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 21: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 422;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 22: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 423;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 23: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 424;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 24: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 425;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 25: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 426;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 26: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 427;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 27: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 428;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 28: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 429;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 29: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 430;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 30: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 431;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 31: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 432;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 32: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 433;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 33: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 434;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 34: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 435;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }

                        }
                    }
                });
                break;
            }

            case 5:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "天通苑北") ;
                item.put("img", R.drawable.head5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "天通苑") ;
                item.put("img", R.drawable.body5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "天通苑南") ;
                item.put("img", R.drawable.body5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "立水桥") ;
                item.put("img", R.drawable.body5) ;
                item.put("img2", R.drawable.l13) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "立水桥南") ;
                item.put("img", R.drawable.body5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "北苑路北") ;
                item.put("img", R.drawable.body5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "大屯路东") ;
                item.put("img", R.drawable.body5) ;
                item.put("img2", R.drawable.l15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "惠新西街北口") ;
                item.put("img", R.drawable.body5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "惠新西街南口") ;
                item.put("img", R.drawable.body5) ;
                item.put("img2", R.drawable.l10) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "和平西桥") ;
                item.put("img", R.drawable.body5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "和平里北街") ;
                item.put("img", R.drawable.body5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "雍和宫") ;
                item.put("img", R.drawable.body5) ;
                item.put("img2", R.drawable.l2) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "北新桥") ;
                item.put("img", R.drawable.body5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "张自忠路") ;
                item.put("img", R.drawable.body5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "东四") ;
                item.put("img", R.drawable.body5) ;
                item.put("img2", R.drawable.l6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "灯市口") ;
                item.put("img", R.drawable.body5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "东单") ;
                item.put("img", R.drawable.body5) ;
                item.put("img2", R.drawable.l1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "崇文门") ;
                item.put("img", R.drawable.body5) ;
                item.put("img2", R.drawable.l2) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "磁器口") ;
                item.put("img", R.drawable.body5) ;
                item.put("img2", R.drawable.l7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "天坛东门") ;
                item.put("img", R.drawable.body5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "蒲黄榆") ;
                item.put("img", R.drawable.body5) ;
                item.put("img2", R.drawable.l14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "刘家窑") ;
                item.put("img", R.drawable.body5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "宋家庄") ;
                item.put("img", R.drawable.foot5) ;
                item.put("img3", R.drawable.l10) ;
                item.put("img2", R.drawable.l24) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 501;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 502;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 503;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 504;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 505;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 506;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 507;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 7: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 508;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 8: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 509;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 9: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 510;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 10: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 511;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 11: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 512;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 12: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 513;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 13: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 514;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 14: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 515;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 15: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 516;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 16: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 517;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 17: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 518;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 18: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 519;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 19: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 520;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 20: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 521;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 21: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 522;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 22: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 523;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }

                        }
                    }
                });
                break;
            }
            case 6:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "海淀五路居") ;
                item.put("img", R.drawable.head6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "慈寿寺") ;
                item.put("img", R.drawable.body6) ;
                item.put("img2", R.drawable.l10) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "花园桥") ;
                item.put("img", R.drawable.body6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "白石桥南") ;
                item.put("img", R.drawable.body6) ;
                item.put("img2", R.drawable.l9) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "车公庄西") ;
                item.put("img", R.drawable.body6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "车公庄") ;
                item.put("img", R.drawable.body6) ;
                item.put("img2", R.drawable.l2) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "平安里") ;
                item.put("img", R.drawable.body6) ;
                item.put("img2", R.drawable.l4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "北海北") ;
                item.put("img", R.drawable.body6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "南锣鼓巷") ;
                item.put("img", R.drawable.body6) ;
                item.put("img2", R.drawable.l8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "东四") ;
                item.put("img", R.drawable.body6) ;
                item.put("img2", R.drawable.l5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "朝阳门") ;
                item.put("img", R.drawable.body6) ;
                item.put("img2", R.drawable.l2) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "东大桥") ;
                item.put("img", R.drawable.body6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "呼家楼") ;
                item.put("img", R.drawable.body6) ;
                item.put("img2", R.drawable.l10) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "金台路") ;
                item.put("img", R.drawable.body6) ;
                item.put("img2", R.drawable.l14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "十里堡") ;
                item.put("img", R.drawable.body6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "青年路") ;
                item.put("img", R.drawable.body6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "褡裢坡") ;
                item.put("img", R.drawable.body6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "黄渠") ;
                item.put("img", R.drawable.body6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "常营") ;
                item.put("img", R.drawable.body6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "草房") ;
                item.put("img", R.drawable.body6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "物资学院路") ;
                item.put("img", R.drawable.body6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "通州北关") ;
                item.put("img", R.drawable.body6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "北运河西") ;
                item.put("img", R.drawable.body6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "郝家府") ;
                item.put("img", R.drawable.body6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "东夏园") ;
                item.put("img", R.drawable.body6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "潞城") ;
                item.put("img", R.drawable.foot6) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 601;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 602;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 603;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 604;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 605;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 606;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 607;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 7: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 608;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 8: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 609;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 9: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 610;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 10: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 611;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 11: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 612;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 12: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 613;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 13: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 614;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 14: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 615;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 15: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 616;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 16: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 617;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 17: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 618;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 18: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 619;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 19: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 620;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 20: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 621;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 21: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 622;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 22: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 623;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 23: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 624;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 24: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 625;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 25: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 626;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }


                        }
                    }
                });
                break;
            }
            case 7:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "北京西站") ;
                item.put("img", R.drawable.head7) ;
                item.put("img2", R.drawable.l9) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "湾子") ;
                item.put("img", R.drawable.body7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "达官营") ;
                item.put("img", R.drawable.body7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "广安门内") ;
                item.put("img", R.drawable.body7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "菜市口") ;
                item.put("img", R.drawable.body7) ;
                item.put("img2", R.drawable.l4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "虎坊桥") ;
                item.put("img", R.drawable.body7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "珠市口") ;
                item.put("img", R.drawable.body7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "桥湾") ;
                item.put("img", R.drawable.body7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "磁器口") ;
                item.put("img", R.drawable.body7) ;
                item.put("img2", R.drawable.l5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "广渠门内") ;
                item.put("img", R.drawable.body7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "广渠门外") ;
                item.put("img", R.drawable.body7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "双井") ;
                item.put("img", R.drawable.body7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "九龙山") ;
                item.put("img", R.drawable.body7) ;
                item.put("img2", R.drawable.l14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "大郊亭") ;
                item.put("img", R.drawable.body7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "百子湾") ;
                item.put("img", R.drawable.body7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "化工") ;
                item.put("img", R.drawable.body7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "南楼梓庄") ;
                item.put("img", R.drawable.body7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "欢乐谷景区") ;
                item.put("img", R.drawable.body7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "双合") ;
                item.put("img", R.drawable.body7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "焦化厂") ;
                item.put("img", R.drawable.body7) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 701;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 702;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 703;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 704;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 705;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 706;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 707;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 7: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 708;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 8: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 709;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 9: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 710;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 10: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 711;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 11: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 712;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 12: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 713;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 13: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 714;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 14: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 715;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 15: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 716;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 16: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 717;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 17: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 718;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 18: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 719;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }



                        }
                    }
                });
                break;
            }
            case 8:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "朱辛庄") ;
                item.put("img", R.drawable.head8) ;
                item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "育知路") ;
                item.put("img", R.drawable.body8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "平西府") ;
                item.put("img", R.drawable.body8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "回龙观东大街") ;
                item.put("img", R.drawable.body8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "霍营") ;
                item.put("img", R.drawable.body8) ;
                item.put("img2", R.drawable.l13) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "育新") ;
                item.put("img", R.drawable.body8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "西小口") ;
                item.put("img", R.drawable.body8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "永泰庄") ;
                item.put("img", R.drawable.body8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "林萃桥") ;
                item.put("img", R.drawable.body8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "森林公园南门") ;
                item.put("img", R.drawable.body8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "奥林匹克公园") ;
                item.put("img", R.drawable.body8) ;
                item.put("img2", R.drawable.l15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "奥体中心") ;
                item.put("img", R.drawable.body8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "北土城") ;
                item.put("img", R.drawable.body8) ;
                item.put("img2", R.drawable.l10) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "安华桥") ;
                item.put("img", R.drawable.body8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "安德里北街") ;
                item.put("img", R.drawable.body8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "鼓楼大街") ;
                item.put("img", R.drawable.body8) ;
                item.put("img2", R.drawable.l2) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "什刹海") ;
                item.put("img", R.drawable.body8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "南锣鼓巷") ;
                item.put("img", R.drawable.foot8) ;
                item.put("img2", R.drawable.l6) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 801;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 802;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 803;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 804;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 805;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 806;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 807;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 7: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 808;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 8: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 809;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 9: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 810;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 10: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 811;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 11: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 812;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 12: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 813;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 13: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 814;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 14: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 815;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 15: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 816;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 16: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 817;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 17: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 818;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }

                        }
                    }
                });
                break;
            }
            case 9:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "国家图书馆") ;
                item.put("img", R.drawable.head9) ;
                item.put("img2", R.drawable.l4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "白石桥南") ;
                item.put("img", R.drawable.body9) ;
                item.put("img2", R.drawable.l6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "白堆子") ;
                item.put("img", R.drawable.body9) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "军事博物馆") ;
                item.put("img", R.drawable.body9) ;
                item.put("img2", R.drawable.l1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "北京西站") ;
                item.put("img", R.drawable.body9) ;
                item.put("img2", R.drawable.l7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "六里桥东") ;
                item.put("img", R.drawable.body9) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "六里桥") ;
                item.put("img", R.drawable.body9) ;
                item.put("img2", R.drawable.l10) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "七里庄") ;
                item.put("img", R.drawable.body9) ;
                item.put("img2", R.drawable.l14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "丰台东大街") ;
                item.put("img", R.drawable.body9) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "丰台南路") ;
                item.put("img", R.drawable.body9) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "科怡路") ;
                item.put("img", R.drawable.body9) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "丰台科技园") ;
                item.put("img", R.drawable.body9) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "郭公庄") ;
                item.put("img", R.drawable.foot9) ;
                item.put("img2", R.drawable.l25) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 901;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 902;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 903;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 904;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 905;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 906;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 907;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 7: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 908;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 8: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 909;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 9: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 910;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 10: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 911;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 11: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 912;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 12: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 913;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }



                        }
                    }
                });
                break;
            }
            case 10:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "巴沟") ;
                item.put("img", R.drawable.body10) ;
                //  item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "苏州街") ;
                item.put("img", R.drawable.body10) ;
                //  item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "海淀黄庄") ;
                item.put("img", R.drawable.body10) ;
                item.put("img2", R.drawable.l4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "知春里") ;
                item.put("img", R.drawable.body10) ;
                //   item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "知春路") ;
                item.put("img", R.drawable.body10) ;
                item.put("img2", R.drawable.l13) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "西土城") ;
                item.put("img", R.drawable.body10) ;
                //   item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "牡丹园") ;
                item.put("img", R.drawable.body10) ;
                //    item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "健德门") ;
                item.put("img", R.drawable.body10) ;
                //     item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "北土城") ;
                item.put("img", R.drawable.body10) ;
                item.put("img2", R.drawable.l8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "安贞门") ;
                item.put("img", R.drawable.body10) ;
                //     item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "惠新西街南口") ;
                item.put("img", R.drawable.body10) ;
                item.put("img2", R.drawable.l5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "芍药居") ;
                item.put("img", R.drawable.body10) ;
                item.put("img2", R.drawable.l13) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "太阳宫") ;
                item.put("img", R.drawable.body10) ;
                //      item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "三元桥") ;
                item.put("img", R.drawable.body10) ;
                item.put("img2", R.drawable.l31) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "亮马桥") ;
                item.put("img", R.drawable.body10) ;
                //     item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "农业展览馆") ;
                item.put("img", R.drawable.body10) ;
                //     item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "团结湖") ;
                item.put("img", R.drawable.body10) ;
                //   item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "呼家楼") ;
                item.put("img", R.drawable.body10) ;
                item.put("img2", R.drawable.l6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "金台夕照") ;
                item.put("img", R.drawable.body10) ;
                //   item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "国贸") ;
                item.put("img", R.drawable.body10) ;
                item.put("img2", R.drawable.l1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "双井") ;
                item.put("img", R.drawable.body10) ;
                //   item.put("img2", R.drawable.l27) ;
                data.add(item) ;item = new HashMap<String,Object>() ;
                item.put("view1", "劲松") ;
                item.put("img", R.drawable.body10) ;
                //    item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "潘家园") ;
                item.put("img", R.drawable.body10) ;
                //    item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "十里河") ;
                item.put("img", R.drawable.body10) ;
                item.put("img2", R.drawable.l14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "分钟寺") ;
                item.put("img", R.drawable.body10) ;
                //      item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "成寿寺") ;
                item.put("img", R.drawable.body10) ;
                //item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "宋家庄") ;
                item.put("img", R.drawable.body10) ;
                item.put("img3", R.drawable.l5) ;
                item.put("img2", R.drawable.l24) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "石榴庄") ;
                item.put("img", R.drawable.body10) ;
                //    item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "大红门") ;
                item.put("img", R.drawable.body10) ;
                //     item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "角门东") ;
                item.put("img", R.drawable.body10) ;
                //    item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "角门西") ;
                item.put("img", R.drawable.body10) ;
                item.put("img2", R.drawable.l4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "草桥") ;
                item.put("img", R.drawable.body10) ;
                //     item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "纪家庙") ;
                item.put("img", R.drawable.body10) ;
                //    item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "首经贸") ;
                item.put("img", R.drawable.body10) ;
                //    item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "丰台站") ;
                item.put("img", R.drawable.body10) ;
                //    item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "泥洼") ;
                item.put("img", R.drawable.body10) ;
                //   item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "西局") ;
                item.put("img", R.drawable.body10) ;
                item.put("img2", R.drawable.l14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "六里桥") ;
                item.put("img", R.drawable.body10) ;
                item.put("img2", R.drawable.l9) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "莲花桥") ;
                item.put("img", R.drawable.body10) ;
                //      item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "公主坟") ;
                item.put("img", R.drawable.body10) ;
                item.put("img2", R.drawable.l1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "西钓鱼台") ;
                item.put("img", R.drawable.body10) ;
                //      item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "慈寿寺") ;
                item.put("img", R.drawable.body10) ;
                item.put("img2", R.drawable.l6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "车道沟") ;
                item.put("img", R.drawable.body10) ;
                //      item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "长春桥") ;
                item.put("img", R.drawable.body10) ;
                //       item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "火器营") ;
                item.put("img", R.drawable.body10) ;
                //        item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1001;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1002;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1003;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1004;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1005;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1006;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1007;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 7: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1008;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 8: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1009;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 9: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1010;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 10: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1011;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 11: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1012;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 12: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1013;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 13: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1014;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 14: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1015;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 15: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1016;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 16: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1017;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 17: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1018;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 18: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1019;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 19: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1020;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 20: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1021;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 21: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1022;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 22: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1023;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 23: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1024;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 24: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1025;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 25: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1026;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 26: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1027;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 27: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1028;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 28: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1029;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 29: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1030;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 30: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1031;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 31: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1032;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 32: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1033;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 33: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1034;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 34: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1035;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 35: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1036;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 36: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1037;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 37: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1038;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 38: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j =1039;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 39: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1040;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 40: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1041;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 41: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1042;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 42: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1043;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 43: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1044;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 44: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1045;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }


                        }
                    }
                });
                break;



            }
            case 13:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "西直门") ;
                item.put("img", R.drawable.head13) ;
                item.put("img3", R.drawable.l2) ;
                item.put("img2", R.drawable.l4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "大钟寺") ;
                item.put("img", R.drawable.body13) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "知春路") ;
                item.put("img", R.drawable.body13) ;
                item.put("img2", R.drawable.l10) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "五道口") ;
                item.put("img", R.drawable.body13) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "上地") ;
                item.put("img", R.drawable.body13) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "西二旗") ;
                item.put("img", R.drawable.body13) ;
                item.put("img2", R.drawable.l27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "龙泽") ;
                item.put("img", R.drawable.body13) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "回龙观") ;
                item.put("img", R.drawable.body13) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "霍营") ;
                item.put("img", R.drawable.body13) ;
                item.put("img2", R.drawable.l8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "立水桥") ;
                item.put("img", R.drawable.body13) ;
                item.put("img2", R.drawable.l5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "北苑") ;
                item.put("img", R.drawable.body13) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "望京西") ;
                item.put("img", R.drawable.body13) ;
                item.put("img2", R.drawable.l15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "芍药居") ;
                item.put("img", R.drawable.body13) ;
                item.put("img2", R.drawable.l10) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "光熙门") ;
                item.put("img", R.drawable.body13) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "柳芳") ;
                item.put("img", R.drawable.body13) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "东直门") ;
                item.put("img", R.drawable.foot13) ;
                item.put("img3", R.drawable.l2) ;
                item.put("img2", R.drawable.l31) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1301;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1302;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1303;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1304;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1305;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1306;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1307;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 7: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1308;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 8: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1309;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 9: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1310;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 10: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1311;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 11: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1312;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 12: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1313;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 13: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1314;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 14: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1315;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 15: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1316;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }




                        }
                    }
                });
                break;
            }
            case 141:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "西局") ;
                item.put("img", R.drawable.head14) ;
                item.put("img2", R.drawable.l10) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "七里庄") ;
                item.put("img", R.drawable.body14) ;
                item.put("img2", R.drawable.l9) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "大井") ;
                item.put("img", R.drawable.body14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "郭庄子") ;
                item.put("img", R.drawable.body14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "大瓦窑") ;
                item.put("img", R.drawable.body14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "园博园") ;
                item.put("img", R.drawable.body14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "张郭庄") ;
                item.put("img", R.drawable.foot14) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14101;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14102;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14103;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14104;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14105;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14106;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14107;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }

                        }
                    }
                });
                break;
            }
            case 149:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "北京南站") ;
                item.put("img", R.drawable.head14) ;
                item.put("img2", R.drawable.l4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "永定门外") ;
                item.put("img", R.drawable.body14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "景泰") ;
                item.put("img", R.drawable.body14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "蒲黄榆") ;
                item.put("img", R.drawable.body14) ;
                item.put("img2", R.drawable.l5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "方庄") ;
                item.put("img", R.drawable.body14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "十里河") ;
                item.put("img", R.drawable.body14) ;
                item.put("img2", R.drawable.l10) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "北工大西门") ;
                item.put("img", R.drawable.body14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "九龙山") ;
                item.put("img", R.drawable.body14) ;
                item.put("img2", R.drawable.l7) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "大望路") ;
                item.put("img", R.drawable.body14) ;
                item.put("img2", R.drawable.l1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "金台路") ;
                item.put("img", R.drawable.body14) ;
                item.put("img2", R.drawable.l6) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "朝阳公园") ;
                item.put("img", R.drawable.body14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "枣营") ;
                item.put("img", R.drawable.body14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "东风北桥") ;
                item.put("img", R.drawable.body14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "将台") ;
                item.put("img", R.drawable.body14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "望京南") ;
                item.put("img", R.drawable.body14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "阜通") ;
                item.put("img", R.drawable.body14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "望京") ;
                item.put("img", R.drawable.body14) ;
                item.put("img2", R.drawable.l15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "东湖渠") ;
                item.put("img", R.drawable.body14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "来广营") ;
                item.put("img", R.drawable.body14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "善各庄") ;
                item.put("img", R.drawable.foot14) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14901;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14902;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14903;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j =14904;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14905;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14906;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14907;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 7: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14908;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 8: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14909;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 9: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14910;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 10: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14911;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 11: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14912;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 12: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14913;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 13: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14914;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 14: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14915;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 15: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14916;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 16: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14917;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 17: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14918;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 18: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14919;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 19: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 14920;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }




                        }
                    }
                });
                break;
            }
            case 15:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "清华东路西口") ;
                item.put("img", R.drawable.head15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "六道口") ;
                item.put("img", R.drawable.body15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "北沙滩") ;
                item.put("img", R.drawable.body15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "奥林匹克公园") ;
                item.put("img", R.drawable.body15) ;
                item.put("img2", R.drawable.l8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "安立路") ;
                item.put("img", R.drawable.body15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "大屯路东") ;
                item.put("img", R.drawable.body15) ;
                item.put("img2", R.drawable.l5) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "关庄") ;
                item.put("img", R.drawable.body15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "望京西") ;
                item.put("img", R.drawable.body15) ;
                item.put("img2", R.drawable.l13) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "望京") ;
                item.put("img", R.drawable.body15) ;
                item.put("img2", R.drawable.l14) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "望京东") ;
                item.put("img", R.drawable.body15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "崔各庄") ;
                item.put("img", R.drawable.body15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "马泉营") ;
                item.put("img", R.drawable.body15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "孙河") ;
                item.put("img", R.drawable.body15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "国展") ;
                item.put("img", R.drawable.body15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "花梨坎") ;
                item.put("img", R.drawable.body15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "后沙峪") ;
                item.put("img", R.drawable.body15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "南法信") ;
                item.put("img", R.drawable.body15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "石门") ;
                item.put("img", R.drawable.body15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "顺义") ;
                item.put("img", R.drawable.body15) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "俸伯") ;
                item.put("img", R.drawable.foot15) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1501;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1502;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1503;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1504;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1505;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1506;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1507;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 7: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1508;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 8: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1509;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 9: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1510;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 10: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1511;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 11: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1512;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 12: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1513;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 13: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1514;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 14: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1515;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 15: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1516;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 16: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1517;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 17: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1518;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 18: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1519;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 19: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1520;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }

                        }
                    }
                });
                break;
            }
            case 16:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "西苑") ;
                item.put("img", R.drawable.head16) ;
                item.put("img2", R.drawable.l4) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "马连洼") ;
                item.put("img", R.drawable.body16) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "西北旺") ;
                item.put("img", R.drawable.body16) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "永丰南") ;
                item.put("img", R.drawable.body16) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "永丰") ;
                item.put("img", R.drawable.body16) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "屯佃") ;
                item.put("img", R.drawable.body16) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "稻香湖路") ;
                item.put("img", R.drawable.body16) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "温阳路") ;
                item.put("img", R.drawable.body16) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "北安河") ;
                item.put("img", R.drawable.foot16) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1601;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1602;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1603;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1604;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1605;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1606;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1607;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 7: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1608;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 8: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 1609;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }

                        }
                    }
                });
                break;
            }
            case 24:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "宋家庄") ;
                item.put("img", R.drawable.head24) ;
                item.put("img3", R.drawable.l5) ;
                item.put("img2", R.drawable.l10);
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "肖村") ;
                item.put("img", R.drawable.body24) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "小红门") ;
                item.put("img", R.drawable.body24) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "旧宫") ;
                item.put("img", R.drawable.body24) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "亦庄桥") ;
                item.put("img", R.drawable.body24) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "亦庄文化园") ;
                item.put("img", R.drawable.body24) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "万源街") ;
                item.put("img", R.drawable.body24) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "荣京东街") ;
                item.put("img", R.drawable.body24) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "荣昌东街") ;
                item.put("img", R.drawable.body24) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "同济南路") ;
                item.put("img", R.drawable.body24) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "经海路") ;
                item.put("img", R.drawable.body24) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "次渠南") ;
                item.put("img", R.drawable.body24) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "次渠") ;
                item.put("img", R.drawable.foot24) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2401;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2402;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2403;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2404;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2405;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2406;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2407;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 7: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2408;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 8: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2409;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 9: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2410;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 10: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2411;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 11: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2412;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 12: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2413;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }

                        }
                    }
                });
                break;
            }
            case 25:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "郭公庄") ;
                item.put("img", R.drawable.head25) ;
                item.put("img2", R.drawable.l9) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "大葆台") ;
                item.put("img", R.drawable.body25) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "稻田") ;
                item.put("img", R.drawable.body25) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "长阳") ;
                item.put("img", R.drawable.body25) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "篱笆房") ;
                item.put("img", R.drawable.body25) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "广阳城") ;
                item.put("img", R.drawable.body25) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "良乡大学城北") ;
                item.put("img", R.drawable.body25) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "良乡大学城") ;
                item.put("img", R.drawable.body25) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "良乡大学城西") ;
                item.put("img", R.drawable.body25) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "良乡南关") ;
                item.put("img", R.drawable.body25) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "苏庄") ;
                item.put("img", R.drawable.foot25) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2501;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2502;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2503;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2504;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2505;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2506;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2507;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 7: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2508;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 8: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2509;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 9: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2510;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 10: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2511;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }

                        }
                    }
                });
                break;
            }
            case 27:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "西二旗") ;
                item.put("img", R.drawable.head27) ;
                item.put("img2", R.drawable.l13) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "生命科学园") ;
                item.put("img", R.drawable.body27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "朱辛庄") ;
                item.put("img", R.drawable.body27) ;
                item.put("img2", R.drawable.l8) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "巩华城") ;
                item.put("img", R.drawable.body27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "沙河") ;
                item.put("img", R.drawable.body27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "沙河高教园") ;
                item.put("img", R.drawable.body27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "南邵") ;
                item.put("img", R.drawable.body27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "北邵洼") ;
                item.put("img", R.drawable.body27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "昌平东关") ;
                item.put("img", R.drawable.body27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "昌平") ;
                item.put("img", R.drawable.body27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "十三陵景区") ;
                item.put("img", R.drawable.body27) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "昌平西山口") ;
                item.put("img", R.drawable.foot27) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2701;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2702;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j =2703;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2704;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2705;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2706;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2707;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 7: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2708;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 8: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2709;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 9: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2710;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 10: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2711;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 11: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 2712;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                        }
                    }
                });
                break;
            }
            case 30:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "四惠") ;
                item.put("img", R.drawable.head30) ;
                item.put("img2", R.drawable.l1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "四惠东") ;
                item.put("img", R.drawable.body30) ;
                item.put("img2", R.drawable.l1) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "高碑店") ;
                item.put("img", R.drawable.body30) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "传媒大学") ;
                item.put("img", R.drawable.body30) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "双桥") ;
                item.put("img", R.drawable.body30) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "管庄") ;
                item.put("img", R.drawable.body30) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "八里桥") ;
                item.put("img", R.drawable.body30) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "通州北苑") ;
                item.put("img", R.drawable.body30) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "果园") ;
                item.put("img", R.drawable.body30) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "九棵树") ;
                item.put("img", R.drawable.body30) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "梨园") ;
                item.put("img", R.drawable.body30) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "临河里") ;
                item.put("img", R.drawable.body30) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "土桥") ;
                item.put("img", R.drawable.foot30) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3001;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3002;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3003;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3004;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3005;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3006;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 6: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3007;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 7: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3008;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 8: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3009;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 9: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3010;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 10: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3011;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 11: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3012;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 12: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3013;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                        }
                    }
                });
                break;
            }
            case 31:{
                item = new HashMap<String,Object>() ;
                item.put("view1", "东直门") ;
                item.put("img", R.drawable.head31) ;
                item.put("img3", R.drawable.l2) ;
                item.put("img2", R.drawable.l13) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "三元桥") ;
                item.put("img", R.drawable.body31) ;
                item.put("img2", R.drawable.l10) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "3号航站楼") ;
                item.put("img", R.drawable.body31) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "2号航站楼") ;
                item.put("img", R.drawable.body31) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "三元桥") ;
                item.put("img", R.drawable.body31) ;
                item.put("img2", R.drawable.l10) ;
                data.add(item) ;
                item = new HashMap<String,Object>() ;
                item.put("view1", "东直门") ;
                item.put("img", R.drawable.foot31) ;
                item.put("img3", R.drawable.l2) ;
                item.put("img2", R.drawable.l13) ;
                data.add(item) ;
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;
                        SharedPreferences.Editor editor = sharedPreferences3.edit();
                        switch (arg2) {
                            case 0: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3101;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 1: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3102;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 2: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3103;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 3: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3104;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 4: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3102;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }
                            case 5: {
                                Intent intent0 = new Intent();
                                intent0.setClass(linedetail.this, stationdetail.class);
                                linedetail.this.startActivity(intent0);
                                j = 3101;
                                editor.putInt("Line", j);
                                editor.commit();
                                break;
                            }

                        }
                    }
                });
                break;
            }
        }
        return data ;
    }
}
