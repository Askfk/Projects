package com.bjtu.etransfer.etransfer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
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
public class xianluchaxun extends AppCompatActivity {
    private SharedPreferences sharedPreferences2;

    private int k;
    private ListView lv ;       //声明一个列表
    /* 显示ListView的两种方法：
     * 1)在activity对应的布局里声明ListView控件，使用findViewById初始listView对象，最后listView.setAdapter显示listVIew
     * 2)直接初始化ListVIew = new ListView(this),setAdapter后，通过setContentView(listView)把listView显示出来
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        sharedPreferences2 = getSharedPreferences("users2",Context.MODE_PRIVATE);
        setContentView(R.layout.xianluxuanze);
        lv = (ListView)findViewById(R.id.listviewline) ;
//        lv = new ListView(this) ;
        //String[]的img 要和int[]的img名称一致
        MySimpleAdapter adapter = new MySimpleAdapter(this,getData(),R.layout.itemline,
                new String[]{"view1","view2","img",},
                new int[]{R.id.tv_title,R.id.tv_subtitle,R.id.iv_logo}) ;

        lv.setAdapter(adapter) ;
//        setContentView(lv) ;

        //添加监听器

        lv.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                //Toast.makeText(xianluchaxun.this, "点击的是ListView的item："+arg2, Toast.LENGTH_LONG).show() ;

                SharedPreferences.Editor editor = sharedPreferences2.edit();
                switch (arg2){
                    case 0: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=1;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 1: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=2;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 2: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=4;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 3: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=5;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 4: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=6;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 5: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=7;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 6: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=8;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 7: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=9;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 8: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=10;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 9: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=13;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 10: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=141;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 11: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=149;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 12: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=15;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 13: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=16;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 14: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=24;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 15: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=25;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 16: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=27;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 17: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=30;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                    case 18: {
                        Intent intent0 = new Intent();
                        intent0.setClass(xianluchaxun.this, linedetail.class);
                        xianluchaxun.this.startActivity(intent0);
                        k=31;
                        editor.putInt("Line", k);
                        editor.commit();
                        break;
                    }
                }
            }

        }) ;


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
        item = new HashMap<String,Object>() ;
        item.put("view1", "苹果园—四惠东") ;
        item.put("view2", "苹果园—四惠东") ;
        item.put("img", R.drawable.l1) ;
        data.add(item) ;
        item = new HashMap<String,Object>() ;
        item.put("view1", "环线") ;
        item.put("view2", "西直门—积水潭;积水潭—西直门") ;
        item.put("img", R.drawable.l2) ;
        data.add(item) ;
        item = new HashMap<String,Object>() ;
        item.put("view1", "安河桥北—天宫院") ;
        item.put("view2", "安河桥北—公益西桥/新宫/天宫院") ;
        item.put("img", R.drawable.l4) ;
        data.add(item) ;
        item = new HashMap<String,Object>() ;
        item.put("view1", "天通苑北—宋家庄") ;
        item.put("view2", "天通苑北—宋家庄") ;
        item.put("img", R.drawable.l5) ;
        data.add(item) ;
        item = new HashMap<String,Object>() ;
        item.put("view1", "海淀五路居—潞城") ;
        item.put("view2", "海淀五路居—草房/潞城") ;
        item.put("img", R.drawable.l6) ;
        data.add(item) ;
        item = new HashMap<String,Object>() ;
        item.put("view1", "北京西站—焦化厂") ;
        item.put("view2", "北京西站—焦化厂") ;
        item.put("img", R.drawable.l7) ;
        data.add(item) ;
        item = new HashMap<String,Object>() ;
        item.put("view1", "南锣鼓巷—朱辛庄") ;
        item.put("view2", "南锣鼓巷—朱辛庄") ;
        item.put("img", R.drawable.l8) ;
        data.add(item) ;
        item = new HashMap<String,Object>() ;
        item.put("view1", "国家图书馆—郭公庄") ;
        item.put("view2", "国家图书馆—郭公庄") ;
        item.put("img", R.drawable.l9) ;
        data.add(item) ;
        item = new HashMap<String,Object>() ;
        item.put("view1", "环线") ;
        item.put("view2", "慈寿寺—车道沟;苏州街—巴沟;宋家庄—成寿寺") ;
        item.put("img", R.drawable.l10) ;
        data.add(item) ;
        item = new HashMap<String,Object>() ;
        item.put("view1", "西直门—东直门") ;
        item.put("view2", "西直门—东直门;西直门—霍营;东直门—回龙观") ;
        item.put("img", R.drawable.l13) ;
        data.add(item) ;
        item = new HashMap<String,Object>() ;
        item.put("view1", "(西段)西局—张郭庄") ;
        item.put("view2", "西局—大瓦窑/张郭庄") ;
        item.put("img", R.drawable.l14) ;
        data.add(item) ;
        item = new HashMap<String,Object>() ;
        item.put("view1", "(东段)北京南站—善各庄") ;
        item.put("view2", "北京南站—善各庄") ;
        item.put("img", R.drawable.l14) ;
        data.add(item) ;
        item = new HashMap<String,Object>() ;
        item.put("view1", "清华东路西口—俸伯") ;
        item.put("view2", "清华东路西口—马泉营/俸伯") ;
        item.put("img", R.drawable.l15) ;
        data.add(item) ;
        item = new HashMap<String,Object>() ;
        item.put("view1", "北安河—西苑") ;
        item.put("view2", "北安河—西苑") ;
        item.put("img", R.drawable.l16) ;
        data.add(item) ;item = new HashMap<String,Object>() ;
        item.put("view1", "宋家庄—次渠") ;
        item.put("view2", "宋家庄—次渠") ;
        item.put("img", R.drawable.l24) ;
        data.add(item) ;item = new HashMap<String,Object>() ;
        item.put("view1", "郭公庄—苏庄") ;
        item.put("view2", "郭公庄—苏庄") ;
        item.put("img", R.drawable.l25) ;
        data.add(item) ;
        item = new HashMap<String,Object>() ;
        item.put("view1", "昌平西山口—西二旗") ;
        item.put("view2", "昌平西山口—西二旗/朱辛庄") ;
        item.put("img", R.drawable.l27) ;
        data.add(item) ;
        item = new HashMap<String,Object>() ;
        item.put("view1", "四惠—土桥") ;
        item.put("view2", "四惠—土桥") ;
        item.put("img", R.drawable.l30) ;
        data.add(item) ;
        item = new HashMap<String,Object>() ;
        item.put("view1", "东直门—机场—东直门") ;
        item.put("view2", "东直门—三元桥—T3—T2—三元桥—东直门") ;
        item.put("img", R.drawable.l31) ;
        data.add(item) ;


        return data ;

    }

}