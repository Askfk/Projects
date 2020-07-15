package com.bjtu.etransfer.etransfer;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class showPath extends AppCompatActivity  {
    private LinearLayout linearLayout;
    private TextView massageTV;
    private Hashtable<Integer, String> mHashTable1;
    private Hashtable<String, Integer> mHashTable2;
    private GraphEntry graph[];
    private TableEntry table[];
    private int vertexNum;
    private String beginStation;
    private int beginVertex;
    private String endStation;
    private int endVertex;
    private String mResult;
    private ArrayList<Integer> mPath;
    private TextView start,end;
    private SharedPreferences sharedPreferences;

    private Button minTime, minStation, minChange;

    private int show = 1;
    private int overtime = 30;
    private int op = 1;
    private String nowTime, posiTime,posiTime1,posiTime2,positime3;
    private int tiHuan = 0;
    private long e;
    private BusPathSearch bps = new BusPathSearch();
    private String[] busstations={"","","","","","","","","",""};
    private String[] busstations2={"","","","","","","","","",""};
    private ImageView mLoading;
    private AnimationDrawable mLoadingAinm;
    RelativeLayout re3;
    LinearLayout re4;
    private Location_service.MyBinder myBinder;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (Location_service.MyBinder) service;
            myBinder.startGPS();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_path);
        showPath sp = new showPath();
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();


        mLoading = (ImageView) findViewById(R.id.loading);
        mLoading.setBackgroundResource(R.drawable.progressbar_activity_waiting);
        mLoadingAinm = (AnimationDrawable) mLoading.getBackground();
        mLoading.post(new Runnable() {
            public void run() {
                mLoadingAinm.start();
            }
        });
        re3=(RelativeLayout)findViewById(R.id.re3);
        re4=(LinearLayout)findViewById(R.id.re4);

        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        sharedPreferences = getSharedPreferences("MassageSavers", Context.MODE_PRIVATE);
        linearLayout = (LinearLayout)findViewById(R.id.testlayout);

        minTime = (Button)findViewById(R.id.minTime);
        minStation = (Button)findViewById(R.id.minStation);
        minChange = (Button)findViewById(R.id.minChange);

        massageTV = (TextView)findViewById(R.id.messageTV);
        start = (TextView)findViewById(R.id.start);
        end = (TextView)findViewById(R.id.end);

        ImageView start2 = (ImageView) findViewById(R.id.start2);
        ImageView end2 = (ImageView) findViewById(R.id.end2);
        start2.setBackgroundResource(R.drawable.start);
        end2.setBackgroundResource(R.drawable.zhong);

        beginStation = sharedPreferences.getString("beginVertex", null);
        endStation = sharedPreferences.getString("endVertex", null);
        start.setText(beginStation);
        end.setText(endStation);

        nowTime = sharedPreferences.getString("nowtime", null);
        //nowTime = "23:30:00";
        posiTime = "22:00:00";
        posiTime1 = "5:00:00";
        posiTime2 = "24:00:00";
        positime3 = "23:00:00";



        try {
            e = sp.fromDateStringToLong(nowTime);
            long u = sp.fromDateStringToLong(posiTime);
            long m = sp.fromDateStringToLong(posiTime1);
            long n = sp.fromDateStringToLong(posiTime2);
            long q = sp.fromDateStringToLong(positime3);
            if(e < u && e > m) tiHuan = 0;
            else if(e >= u && e <= n)
            {
                tiHuan = 1;
                Toast.makeText(showPath.this, "由于末班车原因，为您推荐的最优路线可能与白天有所不同哦~", Toast.LENGTH_SHORT).show();
            }
            else {
                new AlertDialog.Builder(showPath.this)
                        .setMessage("地铁已经过了末班的时间了哦~")
                        .setPositiveButton("我就要坐地铁", new android.content.DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface arg0, int arg1) {
                                nowTime = "12:00:00";
                                tiHuan = 0;

                                PathSearch();
                                try {
                                    linearLayout.removeAllViews();
                                    doMassageShow();
                                } catch (ParseException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("去看看夜班公交", new android.content.DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface arg0, int arg1) {
                                if(bps.shardPath.contains(beginStation) && bps.shardPath.contains(endStation)){
                                    final Editor editor = sharedPreferences.edit();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(showPath.this);
                                    for (int j = 0;j < BusFinalVars.Metro_to_Bus.length-1;j++) {
                                        if ((beginStation.equals(BusFinalVars.Metro_to_Bus[j][0]))) {

                                            builder.setTitle("选择起点站附近的公交车站");
                                            //    指定下拉列表的显示数据
                                            for (int k = 0;k < BusFinalVars.Metro_to_Bus[j].length-1;k++)
                                            {
                                                busstations[k] = BusFinalVars.Metro_to_Bus[j][k+1];};
                                            //    设置一个下拉的列表选择项
                                            builder.setItems(busstations, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Toast.makeText(showPath.this, "选择的公交站为：" + busstations[which], Toast.LENGTH_SHORT).show();
                                                    editor.putString("StartBusStation", busstations[which]);
                                                    editor.commit();

                                                    for (int b = 0;b < BusFinalVars.Metro_to_Bus.length-1;b++) {
                                                        if ((endStation.equals(BusFinalVars.Metro_to_Bus[b][0]))) {
                                                            AlertDialog.Builder builder = new AlertDialog.Builder(showPath.this);
                                                            builder.setTitle("选择终点站附近的公交车站");
                                                            //    指定下拉列表的显示数据
                                                            for (int c = 0;c < BusFinalVars.Metro_to_Bus[b].length-1;c++)
                                                            {
                                                                busstations2[c] = BusFinalVars.Metro_to_Bus[b][c+1];};
                                                            //    设置一个下拉的列表选择项
                                                            builder.setItems(busstations2, new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    Toast.makeText(showPath.this, "选择的公交站为：" + busstations2[which], Toast.LENGTH_SHORT).show();
                                                                    editor.putString("EndBusStation", busstations2[which]);
                                                                    editor.commit();
                                                                    Intent intent2 = new Intent();
                                                                    intent2.setClass(showPath.this, BusShowPath.class);
                                                                    showPath.this.startActivity(intent2);
                                                                    finish();
                                                                }
                                                            });
                                                            builder.setNegativeButton("重新选择", new DialogInterface.OnClickListener()
                                                            {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which)
                                                                {
                                                                    Toast.makeText(showPath.this,"请重新选择", Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                            builder.show();


                                                        }
                                                    }

                                                }
                                            });
                                            builder.show();
                                        }
                                    }

                                }
                                else {
                                    new AlertDialog.Builder(showPath.this)
                                            .setMessage("您所选择的地铁站附近没有公交站哦！")
                                            .setPositiveButton("重选地铁线路", new android.content.DialogInterface.OnClickListener(){
                                        public void onClick(DialogInterface arg0, int arg1){
                                            Intent intent = new Intent();
                                            intent.setClass(showPath.this, MainActivity.class);
                                            showPath.this.startActivity(intent);
                                            finish();
                                        }
                                    }).show();
                                }

                            }}).show();
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        PathSearch();

        try{
            linearLayout.removeAllViews();
            doMassageShow();


            minTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    re3.setVisibility(View.VISIBLE);
                    re4.setVisibility(View.INVISIBLE);
                    handler1.sendEmptyMessageDelayed(0,500);
                    minTime.setBackgroundResource(R.drawable.button_one_choose);
                    minTime.setTextColor(getResources().getColor(R.color.white));
                    minStation.setBackgroundResource(R.drawable.button_two_notchoose);
                    minStation.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    minChange.setBackgroundResource(R.drawable.button_three_notchoose);
                    minChange.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    show = 1;
                    op = 1;
                    linearLayout.removeAllViews();
                    try {
                        doMassageShow();
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            minStation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    re3.setVisibility(View.VISIBLE);
                    re4.setVisibility(View.INVISIBLE);
                    handler1.sendEmptyMessageDelayed(0,500);
                    minTime.setBackgroundResource(R.drawable.button_one_notchoose);
                    minTime.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    minStation.setBackgroundResource(R.drawable.button_two_choose);
                    minStation.setTextColor(getResources().getColor(R.color.white));
                    minChange.setBackgroundResource(R.drawable.button_three_notchoose);
                    minChange.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    show = 2;
                    op = 1;
                    linearLayout.removeAllViews();
                    try {
                        doMassageShow();
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }

                }
            });
            minChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    re3.setVisibility(View.VISIBLE);
                    re4.setVisibility(View.INVISIBLE);
                    handler1.sendEmptyMessageDelayed(0,500);
                    minTime.setBackgroundResource(R.drawable.button_one_notchoose);
                    minTime.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    minStation.setBackgroundResource(R.drawable.button_two_notchoose);
                    minStation.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    minChange.setBackgroundResource(R.drawable.button_three_choose);
                    minChange.setTextColor(getResources().getColor(R.color.white));
                    show = 3;
                    op = 1;
                    linearLayout.removeAllViews();
                    try {
                        doMassageShow();
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }

                }
            });
        }catch (Exception e){
            new AlertDialog.Builder(showPath.this)
                    .setMessage("地铁已经过了末班的时间了哦~")
                    .setPositiveButton("我就要坐地铁", new android.content.DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface arg0, int arg1) {
                            nowTime = "12:00:00";
                            tiHuan = 0;

                            PathSearch();
                            try {
                                linearLayout.removeAllViews();
                                doMassageShow();
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                        }
                    })
                    .setNegativeButton("去看看夜班公交", new android.content.DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface arg0, int arg1) {
                            if(bps.shardPath.contains(beginStation) && bps.shardPath.contains(endStation)){
                                final Editor editor = sharedPreferences.edit();
                                AlertDialog.Builder builder = new AlertDialog.Builder(showPath.this);
                                for (int j = 0;j < BusFinalVars.Metro_to_Bus.length-1;j++) {
                                    if ((beginStation.equals(BusFinalVars.Metro_to_Bus[j][0]))) {

                                        builder.setTitle("选择起点站附近的公交车站");
                                        //    指定下拉列表的显示数据
                                        for (int k = 0;k < BusFinalVars.Metro_to_Bus[j].length-1;k++)
                                        {
                                            busstations[k] = BusFinalVars.Metro_to_Bus[j][k+1];};
                                        //    设置一个下拉的列表选择项
                                        builder.setItems(busstations, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(showPath.this, "选择的公交站为：" + busstations[which], Toast.LENGTH_SHORT).show();
                                                editor.putString("StartBusStation", busstations[which]);
                                                editor.commit();

                                                for (int b = 0;b < BusFinalVars.Metro_to_Bus.length-1;b++) {
                                                    if ((endStation.equals(BusFinalVars.Metro_to_Bus[b][0]))) {
                                                        AlertDialog.Builder builder = new AlertDialog.Builder(showPath.this);
                                                        builder.setTitle("选择终点站附近的公交车站");
                                                        //    指定下拉列表的显示数据
                                                        for (int c = 0;c < BusFinalVars.Metro_to_Bus[b].length-1;c++)
                                                        {
                                                            busstations2[c] = BusFinalVars.Metro_to_Bus[b][c+1];};
                                                        //    设置一个下拉的列表选择项
                                                        builder.setItems(busstations2, new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                Toast.makeText(showPath.this, "选择的公交站为：" + busstations2[which], Toast.LENGTH_SHORT).show();
                                                                editor.putString("EndBusStation", busstations2[which]);
                                                                editor.commit();
                                                                Intent intent2 = new Intent();
                                                                intent2.setClass(showPath.this, BusShowPath.class);
                                                                showPath.this.startActivity(intent2);
                                                                finish();
                                                            }
                                                        });
                                                        builder.setNegativeButton("重新选择", new DialogInterface.OnClickListener()
                                                        {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which)
                                                            {
                                                                Toast.makeText(showPath.this,"请重新选择", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                        builder.show();


                                                    }
                                                }

                                            }
                                        });
                                        builder.show();
                                    }
                                }

                            }
                            else {
                                new AlertDialog.Builder(showPath.this)
                                        .setMessage("您所选择的地铁站附近没有公交站哦！")
                                        .setPositiveButton("重选地铁线路", new android.content.DialogInterface.OnClickListener(){
                                            public void onClick(DialogInterface arg0, int arg1){
                                                Intent intent = new Intent();
                                                intent.setClass(showPath.this, MainActivity.class);
                                                showPath.this.startActivity(intent);
                                                finish();
                                            }
                                        }).show();
                            }

                        }}).show();
        }
    }
    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getHome();
            super.handleMessage(msg);
        }
    };
    public void getHome(){
        re3.setVisibility(View.INVISIBLE);
        re4.setVisibility(View.VISIBLE);
        //finish();
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

    public void PathSearch(){
        op = 1;
        initHashTable();
        initGraph();
        makeGraph();
        addGraphDetail();
        initTable();

    }


    /** hash table init */
    public void initHashTable(){
        int i, j, id = 0;

        mHashTable1 = new Hashtable<Integer, String>();
        mHashTable2 = new Hashtable<String, Integer>();

        for (i = 0; i < ResFinalVars.lines.length; i++)
        {
            //System.out.println("*********" + (i+1) + "号线***************");
            for (j = 0; j < ResFinalVars.lines[i].length; j++){
                if (mHashTable1.contains(ResFinalVars.lines[i][j]) == false)
                {
                    //System.out.println(ResFinalVars.lines[i][j]);
                    mHashTable1.put(id, ResFinalVars.lines[i][j]);
                    mHashTable2.put(ResFinalVars.lines[i][j], id);
                    id++;
                }
            }
        }
        vertexNum = id;
    }

    /** return id by string */
    public int findIdByKey(String key){
        return mHashTable2.get(key);
    }

    /** return string by id */
    public String findStrById(int id){
        return mHashTable1.get(id);
    }

    /** graph init */
    public void initGraph(){
        int vertexNum = this.getVertexNum();
        graph = new GraphEntry[vertexNum];
        for (int i = 0; i < vertexNum; i++)
        {
            graph[i] = new GraphEntry();
        }
        mPath = new ArrayList<Integer>();
    }

    /** graph make */
    public void makeGraph(){
        int i, j, id, index;
        for (i = 0; i < ResFinalVars.lines.length; i++)
        {
            for (j = 1; j < ResFinalVars.lines[i].length - 1; j++)
            {
                id = findIdByKey(ResFinalVars.lines[i][j]);
                graph[id].insertItem(findIdByKey(ResFinalVars.lines[i][j + 1]));
                graph[id].insertTime(Integer.parseInt(ResFinalVars.times_s[i][j]));
                graph[id].insertEtime(ResFinalVars.Endtime[i][j]);
                graph[id].insertLength(Integer.parseInt(ResFinalVars.length[i][j]));

                graph[id].insertItem(findIdByKey(ResFinalVars.lines[i][j - 1]));
                graph[id].insertTime(Integer.parseInt(ResFinalVars.times_n[i][ResFinalVars.lines[i].length - j - 1]));
                graph[id].insertEtime(ResFinalVars.Endtime_s[i][ResFinalVars.lines[i].length - j - 1]);
                graph[id].insertLength(Integer.parseInt(ResFinalVars.length[i][ResFinalVars.lines[i].length - 1 - j]));
            }
            index = 0;
            graph[findIdByKey(ResFinalVars.lines[i][index])].insertItem(findIdByKey(ResFinalVars.lines[i][index + 1]));
            graph[findIdByKey(ResFinalVars.lines[i][index])].insertTime(Integer.parseInt(ResFinalVars.times_s[i][index]));
            graph[findIdByKey(ResFinalVars.lines[i][index])].insertEtime(ResFinalVars.Endtime[i][index]);
            graph[findIdByKey(ResFinalVars.lines[i][index])].insertLength(Integer.parseInt(ResFinalVars.length[i][index]));

            index = ResFinalVars.lines[i].length - 1;
            graph[findIdByKey(ResFinalVars.lines[i][index])].insertItem(findIdByKey(ResFinalVars.lines[i][index - 1]));
            graph[findIdByKey(ResFinalVars.lines[i][index])].insertTime(Integer.parseInt(ResFinalVars.times_n[i][0]));
            graph[findIdByKey(ResFinalVars.lines[i][index])].insertEtime(ResFinalVars.Endtime_s[i][0]);
            graph[findIdByKey(ResFinalVars.lines[i][index])].insertLength(Integer.parseInt(ResFinalVars.length[i][index - 1]));
        }
    }


    /** add graph detail, line number and other info */
    public void addGraphDetail(){

        int i, j, id, n = 0;
        // add line info
        for (i = 0; i < ResFinalVars.lines.length; i++)
        {
            for (j = 0; j < ResFinalVars.lines[i].length; j++)
            {
                id = findIdByKey(ResFinalVars.lines[i][j]);
                graph[id].setInfo(ResFinalVars.NORMAL);
                switch(i){
                    case 0:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINE1.getValue());
                        break;
                    case 1:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINE2.getValue());
                        break;
                    case 2:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINE4.getValue());
                        break;
                    case 3:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINE5.getValue());
                        break;
                    case 4:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINE6.getValue());
                        break;
                    case 5:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINE7.getValue());
                        break;
                    case 6:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINE8.getValue());
                        break;
                    case 7:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINE9.getValue());
                        break;
                    case 8:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINE10.getValue());
                        break;
                    case 9:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINE13.getValue());
                        break;
                    case 10:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINE14x.getValue());
                        break;
                    case 11:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINE14d.getValue());
                        break;
                    case 12:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINE15.getValue());
                        break;
                    case 13:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINE16.getValue());
                        break;
                    case 14:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINEBT.getValue());
                        break;
                    case 15:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINEFS.getValue());
                        break;
                    case 16:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINECP.getValue());
                        break;
                    case 17:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINEYZ.getValue());
                        break;
                    case 18:
                        graph[id].addLine(ResFinalVars.LINESINFO.LINEJC.getValue());
                        break;
                }
            }
        }

        // add transit station info
        for (i = 0; i < ResFinalVars.transit_stations.length; i++)
        {
            id = findIdByKey(ResFinalVars.transit_stations[i]);
            graph[id].setInfo(ResFinalVars.TRANSIT);
            for(int s = 0;s < graph[id].size() - 1;s++)
            {
                for(int e = s + 1;e < graph[id].size();e++)
                {
                    if((graph[graph[id].getItem(s)].getLine() & graph[id].getLine()) != (graph[graph[id].getItem(e)].getLine() & graph[id].getLine()))
                    {
                        if(graph[id].contains((graph[graph[id].getItem(s)].getLine() & graph[id].getLine()) + (graph[graph[id].getItem(e)].getLine() & graph[id].getLine())) == false)
                        {
                            graph[id].put((graph[graph[id].getItem(s)].getLine() & graph[id].getLine()) + (graph[graph[id].getItem(e)].getLine() & graph[id].getLine()), ResFinalVars.change_time[n]);
                            n++;
                        }
                    }
                }
            }
            //      System.out.println(findStrById(id) + "---" + graph[id].l() + "---" + graph[id].size());
            //       n += graph[id].l();
        }
        //   System.out.println(ResFinalVars.transit_stations.length);
        //     System.out.println(ResFinalVars.change_time.length);
    }


    /** table init */
    public void initTable(){
        // table为TableEntry类封装的一个一维数组，在初始化定义时未定义其长度，该句代码为其长度的定义
        table = new TableEntry[this.getVertexNum()];
        for (int i = 0; i < table.length; i++)
        {
            table[i] = new TableEntry();
        }
    }


    /** table clear */
    public void clearTable(){
        for (int i = 0; i < table.length; i++)
        {
            table[i].setKnown(false);
            table[i].setDist(ResFinalVars.INFINITE);
            //System.out.println(i + "__" + table[i].getDist()); //这一步表明初始化是全部dist = 65535
            table[i].setPath(ResFinalVars.UNKNOWN);
        }
    }

    /** set begin vertex by id */
    public boolean setBeginVertex(int id){
        clearTable();
        if (mHashTable1.containsKey(id) == true)
        {
            beginVertex = id;
            beginStation = findStrById(id);
            table[id].setDist(0);
            table[id].setTime(0);
            return true;
        }
        return false;
    }

    /** set begin vertex by name */
    /** set begin vertex by name */
    public boolean setBeginVertex(String name){
        int id;
        clearTable();
        if (mHashTable2.containsKey(name) == true)
        {
            id = findIdByKey(name);
            beginVertex = id;
            beginStation = name;
            table[id].setDist(0);
            table[id].setTime(0);
            return true;
        }
        return false;
    }

    /** get begin vertex in id */
    public int getBeginVertexId(){
        return beginVertex;
    }

    /** get end vertex in name */
    public String getBeginVertexName(){
        return beginStation;
    }

    /** set end vertex by id */
    public boolean setEndVertex(int id){
        if (mHashTable1.containsKey(id) == true)
        {
            endVertex = id;
            endStation = findStrById(id);
            return true;
        }
        return false;
    }

    /** set end vertex by name */
    public boolean setEndVertex(String name){
        if (mHashTable2.containsKey(name) == true)
        {
            endVertex = findIdByKey(name);
            endStation = name;
            return true;
        }
        return false;
    }

    /** get end vertex in id */
    public int getEndVertexId(){
        return endVertex;
    }

    /** get end vertex in name */
    public String getEndVertexName(){
        return endStation;
    }


    /** get Vertex number */
    public int getVertexNum(){
        return vertexNum;
    }

    /** startSearch */
    public void startSearch(){
        int currDist, v, w, i, t, dist;
        for (currDist = 0; currDist < this.getVertexNum() * 7; currDist++)
        {
            for (v = 0; v < this.getVertexNum(); v++)
            {
                if ((table[v].getKnown() == false) && (table[v].getDist() == currDist))
                {
                    table[v].setKnown(true);
                    for (i = 0; i < graph[v].size(); i++)
                    {
                        w = graph[v].getItem(i);
                        t = 0;
                        if (graph[v].getInfo() == ResFinalVars.TRANSIT && table[v].getDist() != 0)
                        {
                            if ((graph[table[v].getPath()].getLine() & graph[v].getLine()) != (graph[w].getLine() & graph[v].getLine()))
                            {
                                if(show == 3)
                                    t = graph[v].get((graph[table[v].getPath()].getLine() & graph[v].getLine()) + (graph[w].getLine() & graph[v].getLine())) + overtime;
                                else
                                    t = graph[v].get((graph[table[v].getPath()].getLine() & graph[v].getLine()) + (graph[w].getLine() & graph[v].getLine()));
                            }
                        }

                        if(show == 2)
                        {
                            dist = 1;
                            if (table[w].getDist() > currDist + dist)
                            {
                                table[w].setTime(table[v].getTime() + graph[v].getTime(i) + t);  // 时间计算有问题，算法没问题
                            }

                        }
                        else
                            dist = graph[v].getTime(i) + t;

                        if (table[w].getDist() > currDist + dist)
                        {
                            table[w].setDist(currDist + dist);
                            table[w].setPath(v);
                            table[w].setLength(table[v].getLength() + graph[v].getLength(i));
                        }
                    }
                }
            }
        }
    }

    private void startEndTimeSearch() throws ParseException {
        int currDist, v, w, i, t, dist;
        String needTime;
        long pathTime, nt;
        showPath sp = new showPath();
            for (currDist = 0; currDist < this.getVertexNum() * 7; currDist++)
            {
                for (v = 0; v < this.getVertexNum(); v++)
                {
                    if ((table[v].getKnown() == false) && (table[v].getDist() == currDist))
                    {
                        table[v].setKnown(true);
                        for (i = 0; i < graph[v].size(); i++)
                        {
                            needTime = graph[v].geEtime(i);
                            pathTime = table[v].getDist() * 60 * 1000;
                            nt = sp.fromDateStringToLong(needTime);
                            if(nt >= (e + pathTime))
                            {
                                w = graph[v].getItem(i);
                                t = 0;
                                if (graph[v].getInfo() == ResFinalVars.TRANSIT && table[v].getDist() != 0)
                                {
                                    if ((graph[table[v].getPath()].getLine() & graph[v].getLine()) != (graph[w].getLine() & graph[v].getLine()))
                                    {
                                        if(show == 3)
                                            t = graph[v].get((graph[table[v].getPath()].getLine() & graph[v].getLine()) + (graph[w].getLine() & graph[v].getLine())) + overtime;
                                        else
                                            t = graph[v].get((graph[table[v].getPath()].getLine() & graph[v].getLine()) + (graph[w].getLine() & graph[v].getLine()));
                                    }
                                }

                                if(show == 2)
                                {
                                    dist = 1;
                                    if (table[w].getDist() > currDist + 1)
                                    {
                                        table[w].setTime(table[v].getTime() + graph[v].getTime(i) + t);
                                    }
                                }
                                else
                                    dist = graph[v].getTime(i) + t;

                                if (table[w].getDist() > currDist + dist)
                                {
                                    table[w].setDist(currDist + dist);
                                    table[w].setPath(v);
                                    table[w].setLength(table[v].getLength() + graph[v].getLength(i));
                                }
                            }
                        }
                    }
                }
            }
    }


    /** find path */
    public void findPath(int id){
            if (table[id].getDist() != 0)
            {
                findPath(table[id].getPath());
            }
            mPath.add(id);


    }

    /** get line info */
    public String getLineInfo(int index){
        String ret;
        switch(index)
        {
            case ResFinalVars.LINE1:
                ret = ResFinalVars.lines_number[0];
                break;
            case ResFinalVars.LINE2:
                ret = ResFinalVars.lines_number[1];
                break;
            case ResFinalVars.LINE3:
                ret = ResFinalVars.lines_number[2];
                break;
            case ResFinalVars.LINE4:
                ret = ResFinalVars.lines_number[3];
                break;
            case ResFinalVars.LINE5:
                ret = ResFinalVars.lines_number[4];
                break;
            case ResFinalVars.LINE6:
                ret = ResFinalVars.lines_number[5];
                break;
            case ResFinalVars.LINE7:
                ret = ResFinalVars.lines_number[6];
                break;
            case ResFinalVars.LINE8:
                ret = ResFinalVars.lines_number[7];
                break;
            case ResFinalVars.LINE9:
                ret = ResFinalVars.lines_number[8];
                break;
            case ResFinalVars.LINE10:
                ret = ResFinalVars.lines_number[9];
                break;
            case ResFinalVars.LINE11:
                ret = ResFinalVars.lines_number[10];
                break;
            case ResFinalVars.LINE12:
                ret = ResFinalVars.lines_number[11];
                break;
            case ResFinalVars.LINE13:
                ret = ResFinalVars.lines_number[12];
                break;
            case ResFinalVars.LINE14x:
                ret = ResFinalVars.lines_number[13];
                break;
            case ResFinalVars.LINE14d:
                ret = ResFinalVars.lines_number[14];
                break;
            case ResFinalVars.LINE15:
                ret = ResFinalVars.lines_number[15];
                break;
            case ResFinalVars.LINE16:
                ret = ResFinalVars.lines_number[16];
                break;
            case ResFinalVars.LINEBT:
                ret = ResFinalVars.lines_number[17];
                break;
            case ResFinalVars.LINEFS:
                ret = ResFinalVars.lines_number[18];
                break;
            case ResFinalVars.LINECP:
                ret = ResFinalVars.lines_number[19];
                break;
            case ResFinalVars.LINEYZ:
                ret = ResFinalVars.lines_number[20];
                break;
            case ResFinalVars.LINEJC:
                ret = ResFinalVars.lines_number[21];
                break;
            default:
                ret = "";
                break;
        }
        return ret;
    }

    public void doMassageShow() throws ParseException {
        int i, id, previd, nextid, cnt = 0, n = 0, a = 0, b = 0;
        String linenum = "", ret = "";
        setBeginVertex(beginStation);
        setEndVertex(endStation);
        Editor editor = sharedPreferences.edit();
        if(tiHuan == 0) startSearch();
        else startEndTimeSearch();
        mPath.clear();
        findPath(this.getEndVertexId());
            for(i = 0;i < mPath.size();i++)
            {
                id = mPath.get(i);
                if(i == 0)
                {
                    int zs = 0, posi = 2, u = 0, toid = getEndVertexId();
                    nextid = mPath.get(i + 1);
                    linenum = getLineInfo(graph[id].getLine() & graph[nextid].getLine());
                    ret += "在 " + findStrById(id) + " 乘坐" + linenum + "(" + findStrById(nextid) + "方向)";
                    for(int j = i + 1;j < mPath.size();j++)
                    {
                        int jd = mPath.get(j);
                        if(graph[jd].getInfo() == ResFinalVars.NORMAL)
                        {
                            zs++;
                        }
                        else if(j == mPath.size() - 1)
                        {
                            zs++;
                            posi = 2;
                            toid = jd;
                        }
                        else if(graph[jd].getInfo() == ResFinalVars.TRANSIT )
                        {
                            zs++;
                            if(j + 1 < mPath.size())
                            {
                                a = mPath.get(j - 1);
                                b = mPath.get(j + 1);
                                if ((graph[a].getLine() & graph[jd].getLine()) != (graph[b].getLine() & graph[jd].getLine()))
                                {
                                    j = mPath.size();
                                    posi = 1;
                                    toid = jd;
                                }
                            }
                        }
                    }
                    addStartInfo(linearLayout, nextid, linenum, posi, toid, a, b, zs);
                }
                else if(i ==  mPath.size() - 1)
                {
                    cnt++;
                    int g = table[id].getLength();
                    g = g / 1000;
                    if(g <= 6)
                        g = 3;
                    else if(g > 6 && g <= 12)
                        g = 4;
                    else if(g > 12 && g <= 22)
                        g = 5;
                    else if(g > 22 && g <= 32)
                        g = 6;
                    else{
                        if((g - 32) % 20 != 0)
                            g = (g - 32) / 20 + 7;
                    }
                    ret += "(坐" + cnt + "站)到 " + findStrById(id) + " 下车。票价：" + g + "元。";
                    if(show == 1) {
                        ret = "本次行程途经" + (mPath.size() - 1) + "站，换乘" + n + "次，共需" + table[id].getDist() + "分钟。票价：" + g + "元 。完整信息如下：" + ret;
                        editor.putInt("TripTime", table[id].getDist());
                        Intent bindIntent = new Intent(showPath.this, Location_service.class);
                        bindService(bindIntent, connection, BIND_AUTO_CREATE);
                    }
                    else if(show == 2)
                        ret = "本次行程途经" + (mPath.size()-1) + "站，换乘" + n + "次，共需" + table[id].getTime() + "分钟。票价:" + g + "元。完整信息如下：" + ret;
                    else
                        ret = "本次行程途经" + (mPath.size()-1) + "站，换乘" + n + "次，共需" + (table[id].getDist() - n * overtime) + "分钟。票价：" + g + "元。完整信息如下：" + ret;
                }
                else if(graph[id].getInfo() == ResFinalVars.NORMAL)
                {
                    cnt++;
                }
                else if(graph[id].getInfo() == ResFinalVars.TRANSIT)
                {
                    cnt++;
                    if (i + 1 < mPath.size()) // make sure (i + 1) not overflow
                    {
                        previd = mPath.get(i - 1);
                        nextid = mPath.get(i + 1);
                        linenum = getLineInfo(graph[id].getLine() & graph[nextid].getLine());
                        if ((graph[previd].getLine() & graph[id].getLine()) != (graph[nextid].getLine() & graph[id].getLine()))
                        {
                            int zs = 0, posi = 2, toid = getEndVertexId(), u = 0, jd;
                            n++;
                            ret += "(坐" + cnt + "站)" + "到 " + findStrById(id) + " 换乘" + linenum + "(" + findStrById(nextid) + "方向)";
                            cnt = 0;
                            for(int j = i + 1;j < mPath.size();j++)
                            {
                                jd = mPath.get(j);
                                if(graph[jd].getInfo() == ResFinalVars.NORMAL)
                                {
                                    zs++;
                                }
                                else if(j == mPath.size() - 1)
                                {
                                    zs++;
                                    posi = 2;
                                    toid = jd;
                                }
                                else if(graph[jd].getInfo() == ResFinalVars.TRANSIT )
                                {
                                    zs++;
                                    if(j + 1 < mPath.size())
                                    {
                                        a = mPath.get(j - 1);
                                        b = mPath.get(j + 1);
                                        if ((graph[a].getLine() & graph[jd].getLine()) != (graph[b].getLine() & graph[jd].getLine()))
                                        {
                                            j = mPath.size();
                                            posi = 1;
                                            toid = jd;
                                        }
                                    }
                                }
                            }
                            addChangeInfo(linearLayout, id, nextid, linenum, posi, toid, a, b, zs);
                        }
                    }
                }

            }
            mResult = ret;
            massageTV.setText(mResult);
    }

    public void addStartInfo(LinearLayout layout, int nextid, String line, int Position, int toid, int preId, int nextId ,int sum){
        String finalStation = "";

        LinearLayout ly = new LinearLayout(this);
        ly.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout ly_k = new LinearLayout(this);
        ly_k.setOrientation(LinearLayout.VERTICAL);
        ImageView im_time = new ImageView(this);
        im_time.setBackgroundResource(R.drawable.one);
        ly_k.addView(im_time);
        ly_k.setBackgroundResource(R.drawable.side);

        LinearLayout ly_m = new LinearLayout(this);
        ly_m.setOrientation(LinearLayout.VERTICAL);
        TextView tv_k1 = new TextView(this);
        tv_k1.setText("　　");
        ly_m.addView(tv_k1);
        TextView tv_k2 = new TextView(this);
        tv_k2.setText("　　");
        ly_m.addView(tv_k2);

        LinearLayout ly_m_1 = new LinearLayout(this);
        ly_m_1.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout ly_m_1_1 = new LinearLayout(this);
        ly_m_1_1.setOrientation(LinearLayout.VERTICAL);
        TextView tv_k3 = new TextView(this);
        tv_k3.setText("　　");
        ly_m_1_1.addView(tv_k3);

        LinearLayout ly_1 = new LinearLayout(this);
        ly_1.setOrientation(LinearLayout.HORIZONTAL);
        TextView tv1 = new TextView(this);
        tv1.setText("在");
        TextView tv2 = new TextView(this);
        tv2.setText(beginStation);
        setColor(tv2, line);
        TextView tv3 = new TextView(this);
        tv3.setText("乘坐  ");
        ImageView iv = new ImageView(this);
        String[] linenumber = setChangePhoto(iv, line);
        ly_1.addView(tv1);
        ly_1.addView(tv2);
        ly_1.addView(tv3);

        ly_m_1_1.addView(ly_1);
        TextView tv_k4 = new TextView(this);
        tv_k4.setText("　　");
        //ly_m_1_1.addView(tv_k4);

        ly_m_1.addView(ly_m_1_1);
        ly_m_1.addView(iv);

        ly_m.addView(ly_m_1);

        LinearLayout ly_zhi = new LinearLayout(this);
        ly_zhi.setOrientation(LinearLayout.HORIZONTAL);
        TextView tv_zuo = new TextView(this);
        tv_zuo.setText("坐 ");
        TextView tv_sum = new TextView(this);
        tv_sum.setText(sum + "");
        setColor(tv_sum, line);
        TextView tv_zhi = new TextView(this);
        tv_zhi.setText(" 站至");
        TextView tv_toid = new TextView(this);
        tv_toid.setText(findStrById(toid));
        setColor(tv_toid, line);
        TextView tv_zhan = new TextView(this);

        if(Position == 1)
        {
            tv_zhan.setText("。");
        }
        else if(Position == 2)
        {
            ly_k.setBackgroundResource(R.drawable.side_30);
            tv_zhan.setText("下车。");
        }

        ly_zhi.addView(tv_zuo);
        ly_zhi.addView(tv_sum);
        ly_zhi.addView(tv_zhi);
        ly_zhi.addView(tv_toid);
        ly_zhi.addView(tv_zhan);
        ly_m.addView(ly_zhi);

        LinearLayout ly_2 = new LinearLayout(this);
        ly_2.setOrientation(LinearLayout.HORIZONTAL);
        TextView tv4 = new TextView(this);
        tv4.setText("方向：");
        out:
        for(int i = 0;i < linenumber.length;i++)
        {
            if(linenumber[i].equals(beginStation))
            {
                for(int j = 0;j < linenumber.length;j++)
                {
                    if(linenumber[j] == findStrById(toid))
                    {
                        if(i > j)
                        {
                            finalStation = linenumber[0];
                        }
                        else if(i < j)
                        {
                            finalStation = linenumber[linenumber.length - 1];
                        }
                        break out;
                    }
                }
            }
        }

        TextView tv5 = new TextView(this);
        tv5.setText(finalStation);
        setColor(tv5, line);
        TextView tv6 = new TextView(this);
        tv6.setText("，下一站：");
        TextView tv7= new TextView(this);
        tv7.setText(findStrById(nextid));
        setColor(tv7, line);

        switch (line)
        {
            case "地铁2号线":
                tv6.setText("下一站：");
                ly_2.addView(tv6);
                ly_2.addView(tv7);
                break;
            case "地铁10号线":
                tv6.setText("下一站：");
                ly_2.addView(tv6);
                ly_2.addView(tv7);
                break;
            case "机场快轨":
                tv6.setText("下一站：");
                ly_2.addView(tv6);
                ly_2.addView(tv7);
                break;
            default:
                ly_2.addView(tv4);
                ly_2.addView(tv5);
                ly_2.addView(tv6);
                ly_2.addView(tv7);
                break;
        }

        ly_m.addView(ly_2);
        TextView tv_k5 = new TextView(this);
        tv_k5.setText("　　");
        //    ly_m.addView(tv_k5);
        if(Position == 1)
        {
            //ly_m.setBackgroundResource(R.drawable.main);
            setScreenDoor(ly_m, preId, toid, nextId);
            long a=preId*1000000+toid*1000+nextId;
            String sR=String.valueOf(a);
            switch (sR){
                case "272130131":
                    ly_k.setBackgroundResource(R.drawable.side_60);
                    break;
                case "273193192":
                    ly_k.setBackgroundResource(R.drawable.side_long);
                    break;
            }
        }

        ly.addView(ly_k);
        ly.addView(ly_m);

        layout.addView(ly);
        if(Position == 2)
        {
            LinearLayout ly_zhong = new LinearLayout(this);
            ly_zhong.setOrientation(LinearLayout.HORIZONTAL);
            ImageView im_zhong = new ImageView(this);
            im_zhong.setBackgroundResource(R.drawable.zhong);
            ly_zhong.addView(im_zhong);
            layout.addView(ly_zhong);
        }
        // layout.addView(tv_k5);



    }

    public void addChangeInfo(LinearLayout layout, int ChangeId, int nextid, String line, int Position, int toid, int preId, int nextId, int sum){
        op++;
        String finalStation = "";

        LinearLayout ly = new LinearLayout(this);
        ly.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout ly_k = new LinearLayout(this);
        ly_k.setOrientation(LinearLayout.VERTICAL);
        ImageView im_time = new ImageView(this);

        switch (op){
            case 2:
                im_time.setBackgroundResource(R.drawable.two);
                break;
            case 3:
                im_time.setBackgroundResource(R.drawable.three);
                break;
            case 4:
                im_time.setBackgroundResource(R.drawable.four);
                break;
            case 5:
                im_time.setBackgroundResource(R.drawable.five);
                break;
            case 6:
                im_time.setBackgroundResource(R.drawable.six);
                break;
            case 7:
                im_time.setBackgroundResource(R.drawable.seven);
                break;
            case 8:
                im_time.setBackgroundResource(R.drawable.eight);
                break;
            case 9:
                im_time.setBackgroundResource(R.drawable.nine);
                break;
            case 10:
                im_time.setBackgroundResource(R.drawable.ten);
                break;
            case 11:
                im_time.setBackgroundResource(R.drawable.eleven);
                break;
            case 12:
                im_time.setBackgroundResource(R.drawable.twelve);
                break;
            case 13:
                im_time.setBackgroundResource(R.drawable.thirteen);
                break;
            case 14:
                im_time.setBackgroundResource(R.drawable.fourteen);
                break;
        }
        ly_k.addView(im_time);
        ly_k.setBackgroundResource(R.drawable.side);


        LinearLayout ly_m = new LinearLayout(this);
        ly_m.setOrientation(LinearLayout.VERTICAL);
        TextView tv_k1 = new TextView(this);
        tv_k1.setText("　　");
        ly_m.addView(tv_k1);
        TextView tv_k2 = new TextView(this);
        tv_k2.setText("　　");
        ly_m.addView(tv_k2);

        LinearLayout ly_m_1 = new LinearLayout(this);
        ly_m_1.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout ly_m_1_1 = new LinearLayout(this);
        ly_m_1_1.setOrientation(LinearLayout.VERTICAL);
        TextView tv_k3 = new TextView(this);
        tv_k3.setText("　　");
        ly_m_1_1.addView(tv_k3);

        LinearLayout ly_1 = new LinearLayout(this);
        ly_1.setOrientation(LinearLayout.HORIZONTAL);
        TextView tv1 = new TextView(this);
        tv1.setText("在");
        TextView tv2 = new TextView(this);
        tv2.setText(findStrById(ChangeId));
        setColor(tv2, line);
        //  tv2.setTextColor(0x554354);
        TextView tv3 = new TextView(this);
        tv3.setText("按站内提示标换乘  ");
        ImageView iv = new ImageView(this);
        String[] linenumber = setChangePhoto(iv, line);
        ly_1.addView(tv1);
        ly_1.addView(tv2);
        ly_1.addView(tv3);

        ly_m_1_1.addView(ly_1);
        TextView tv_k4 = new TextView(this);
        tv_k4.setText("　　");
        //ly_m_1_1.addView(tv_k4);

        ly_m_1.addView(ly_m_1_1);
        ly_m_1.addView(iv);

        ly_m.addView(ly_m_1);

        LinearLayout ly_zhi = new LinearLayout(this);
        ly_zhi.setOrientation(LinearLayout.HORIZONTAL);
        TextView tv_zuo = new TextView(this);
        tv_zuo.setText("坐 ");
        TextView tv_sum = new TextView(this);
        tv_sum.setText(sum + "");
        setColor(tv_sum, line);
        TextView tv_zhi = new TextView(this);
        tv_zhi.setText(" 站至");
        TextView tv_toid = new TextView(this);
        tv_toid.setText(findStrById(toid));
        setColor(tv_toid, line);
        TextView tv_zhan = new TextView(this);

        if(Position == 1){
            tv_zhan.setText("。");
        }
        else if(Position == 2)
        {
            ly_k.setBackgroundResource(R.drawable.side_30);
            tv_zhan.setText("下车。");
        }

        ly_zhi.addView(tv_zuo);
        ly_zhi.addView(tv_sum);
        ly_zhi.addView(tv_zhi);
        ly_zhi.addView(tv_toid);
        ly_zhi.addView(tv_zhan);
        ly_m.addView(ly_zhi);

        LinearLayout ly_2 = new LinearLayout(this);
        ly_2.setOrientation(LinearLayout.HORIZONTAL);
        TextView tv4 = new TextView(this);
        tv4.setText("方向：");

        out:
        for(int i = 0;i < linenumber.length;i++)
        {
            if(linenumber[i] == findStrById(ChangeId))
            {
                for(int j = 0;j < linenumber.length;j++)
                {
                    if(linenumber[j] == findStrById(toid))
                    {
                        if(i > j)
                        {
                            finalStation = linenumber[0];
                        }
                        else if(i < j)
                        {
                            finalStation = linenumber[linenumber.length - 1];
                        }
                        break out;
                    }
                }
            }
        }

        TextView tv5 = new TextView(this);
        tv5.setText(finalStation);
        setColor(tv5, line);
        TextView tv6 = new TextView(this);
        tv6.setText("，下一站：");
        TextView tv7= new TextView(this);
        tv7.setText(findStrById(nextid));
        setColor(tv7, line);
        //  tv5.setTextColor(0x445513);
        switch (line)
        {
            case "地铁2号线":
                tv6.setText("下一站：");
                ly_2.addView(tv6);
                ly_2.addView(tv7);
                break;
            case "地铁10号线":
                tv6.setText("下一站：");
                ly_2.addView(tv6);
                ly_2.addView(tv7);
                break;
            case "机场快轨":
                tv6.setText("下一站：");
                ly_2.addView(tv6);
                ly_2.addView(tv7);
                break;
            default:
                ly_2.addView(tv4);
                ly_2.addView(tv5);
                ly_2.addView(tv6);
                ly_2.addView(tv7);
                break;
        }

        ly_m.addView(ly_2);
        TextView tv_k5 = new TextView(this);
        tv_k5.setText("　　");
        //     ly_m.addView(tv_k5);

        if(Position == 1)
        {
           // ly_m.setBackgroundResource(R.drawable.main);
            setScreenDoor(ly_m, preId, toid, nextId);
            long a=preId*1000000+toid*1000+nextId;
            String sR=String.valueOf(a);
            switch (sR){
                case "272130131":
                    ly_k.setBackgroundResource(R.drawable.side_60);
                    break;
                case "273193192":
                    ly_k.setBackgroundResource(R.drawable.side_long);
                    break;
            }
        }

        ly.addView(ly_k);
        ly.addView(ly_m);


        layout.addView(ly);
        if(Position == 2)
        {
            LinearLayout ly_zhong = new LinearLayout(this);
            ly_zhong.setOrientation(LinearLayout.HORIZONTAL);
            ImageView im_zhong = new ImageView(this);
            im_zhong.setBackgroundResource(R.drawable.zhong);
            ly_zhong.addView(im_zhong);
            layout.addView(ly_zhong);
        }
    }


    public void setColor(TextView tv, String line){
        switch (line){
            case "地铁1号线":
                tv.setTextColor(getResources().getColor(R.color.line1));
                break;
            case "地铁2号线":
                tv.setTextColor(getResources().getColor(R.color.line2));
                break;
            case "地铁3号线":
                tv.setTextColor(getResources().getColor(R.color.line2));
                break;
            case "地铁4号线":
                tv.setTextColor(getResources().getColor(R.color.line4));
                break;
            case "地铁5号线":
                tv.setTextColor(getResources().getColor(R.color.line5));
                break;
            case "地铁6号线":
                tv.setTextColor(getResources().getColor(R.color.line6));
                break;
            case "地铁7号线":
                tv.setTextColor(getResources().getColor(R.color.line7));
                break;
            case "地铁8号线":
                tv.setTextColor(getResources().getColor(R.color.line8));
                break;
            case "地铁9号线":
                tv.setTextColor(getResources().getColor(R.color.line9));
                break;
            case "地铁10号线":
                tv.setTextColor(getResources().getColor(R.color.line10));
                break;
            case "地铁11号线":
                tv.setTextColor(getResources().getColor(R.color.line2));
                break;
            case "地铁12号线":
                tv.setTextColor(getResources().getColor(R.color.line2));
                break;
            case "地铁13号线":
                tv.setTextColor(getResources().getColor(R.color.line13));
                break;
            case "地铁14号线(西)":
                tv.setTextColor(getResources().getColor(R.color.line14));
                break;
            case "地铁14号线(东)":
                tv.setTextColor(getResources().getColor(R.color.line14));
                break;
            case "地铁15号线":
                tv.setTextColor(getResources().getColor(R.color.line15));
                break;
            case "地铁16号线":
                tv.setTextColor(getResources().getColor(R.color.line16));
                break;
            case "地铁八通线":
                tv.setTextColor(getResources().getColor(R.color.line30));
                break;
            case "地铁房山线":
                tv.setTextColor(getResources().getColor(R.color.line25));
                break;
            case "地铁昌平线":
                tv.setTextColor(getResources().getColor(R.color.line27));
                break;
            case "地铁亦庄线":
                tv.setTextColor(getResources().getColor(R.color.line24));
                break;
            case "机场快轨":
                tv.setTextColor(getResources().getColor(R.color.line31));
                break;
        }
    }
    public String[] setChangePhoto(ImageView iv, String line){
        switch (line){
            case "地铁1号线":
                iv.setBackgroundResource(R.drawable.f1low);
                return ResFinalVars.lines[0];
            case "地铁2号线":
                iv.setBackgroundResource(R.drawable.f2low);
                return ResFinalVars.lines[1];
            case "地铁3号线":
                iv.setBackgroundResource(R.drawable.f2low);
                return ResFinalVars.lines[0];
            case "地铁4号线":
                iv.setBackgroundResource(R.drawable.f4low);
                return ResFinalVars.lines[2];
            case "地铁5号线":
                iv.setBackgroundResource(R.drawable.f5low);
                return ResFinalVars.lines[3];
            case "地铁6号线":
                iv.setBackgroundResource(R.drawable.f6low);
                return ResFinalVars.lines[4];
            case "地铁7号线":
                iv.setBackgroundResource(R.drawable.f7low);
                return ResFinalVars.lines[5];
            case "地铁8号线":
                iv.setBackgroundResource(R.drawable.f8low);
                return ResFinalVars.lines[6];
            case "地铁9号线":
                iv.setBackgroundResource(R.drawable.f9low);
                return ResFinalVars.lines[7];
            case "地铁10号线":
                iv.setBackgroundResource(R.drawable.f10low);
                return ResFinalVars.lines[8];
            case "地铁11号线":
                iv.setBackgroundResource(R.drawable.f1low);
                return ResFinalVars.lines[0];
            case "地铁12号线":
                iv.setBackgroundResource(R.drawable.f2low);
                return ResFinalVars.lines[0];
            case "地铁13号线":
                iv.setBackgroundResource(R.drawable.f13low);
                return ResFinalVars.lines[9];
            case "地铁14号线(西)":
                iv.setBackgroundResource(R.drawable.f14low);
                return ResFinalVars.lines[10];
            case "地铁14号线(东)":
                iv.setBackgroundResource(R.drawable.f14low);
                return ResFinalVars.lines[11];
            case "地铁15号线":
                iv.setBackgroundResource(R.drawable.f15low);
                return ResFinalVars.lines[12];
            case "地铁16号线":
                iv.setBackgroundResource(R.drawable.f16low);
                return ResFinalVars.lines[13];
            case "地铁八通线":
                iv.setBackgroundResource(R.drawable.f30low);
                return ResFinalVars.lines[14];
            case "地铁房山线":
                iv.setBackgroundResource(R.drawable.f25low);
                return ResFinalVars.lines[15];
            case "地铁昌平线":
                iv.setBackgroundResource(R.drawable.f27low);
                return ResFinalVars.lines[16];
            case "地铁亦庄线":
                iv.setBackgroundResource(R.drawable.f24low);
                return ResFinalVars.lines[17];
            case "机场快轨":
                iv.setBackgroundResource(R.drawable.f31low);
                return ResFinalVars.lines[18];
            default:
                return ResFinalVars.lines[0];
        }
    }
    public long fromDateStringToLong(String inVal) throws ParseException { //此方法计算时间毫秒
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date dt = df.parse(inVal);//将字符串转换为date类型
        return dt.getTime();   //返回毫秒数
    }



    private void setScreenDoor(LinearLayout layout, int preId, int thisId, int nextId){
        long a=preId*1000000+thisId*1000+nextId;
        String sR=String.valueOf(a);

        switch (sR){
            case "24023049": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2109);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2114);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "24023050": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2109);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2114);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "38023049": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2210);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2214);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "38023050": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2210);
                ly.addView(im1);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2214);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "24023190": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2104);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2119);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "38023190": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2204);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2220);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "49023190": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4005);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m4021);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m4022);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "49023024": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4005);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m4021);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m4022);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "49023038": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4005);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m4021);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m4022);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "50023190": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4005);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m4021);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m4022);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "50023024": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4005);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m4021);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m4022);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "50023038": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4005);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m4021);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m4022);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "190187024": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13206);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13207);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m13210);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m13221);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "190187038": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13206);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13207);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m13210);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m13221);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "190187049": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13206);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13207);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m13210);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m13221);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "190187050": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13206);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13207);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m13210);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m13221);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "188187191": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10008);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10017);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m10021);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "188187190": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10008);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10017);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m10021);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "186187191": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10008);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10017);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "186187190": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10008);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10017);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "190187188": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13108);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13121);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "190187186": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13108);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13121);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "191187188": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13205);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13218);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "191187186": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13205);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13218);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "189045044": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10019);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "189045046": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10019);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "188045044": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10019);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "188045046": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10019);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "46045189": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4009);
                ly.addView(im1);
                layout.addView(ly);
                break;

            }
            case "44045189": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4017);
                ly.addView(im1);
                layout.addView(ly);
                break;

            }
            case "46045188": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4018);
                ly.addView(im1);
                layout.addView(ly);
                break;

            }
            case "44045188": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4007);
                ly.addView(im1);
                layout.addView(ly);
                break;

            }
            case "158092091": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10003);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10009);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10010);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m10017);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "158092093": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10003);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10009);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10010);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m10017);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "159092091": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10008);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10015);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10016);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m10023);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "159092093": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10008);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10015);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10016);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m10023);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "93092158": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6015);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6021);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "93092159": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6015);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6021);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "91092158": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6010);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6018);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "91092159": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6010);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6018);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "159007006": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10002);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10009);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10014);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m10017);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "159007008": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10002);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10009);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10014);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m10017);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "160007006": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10008);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10011);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10016);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m10021);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "160007008": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10008);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10011);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10016);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m10021);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "160148149": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10022);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "160148147": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10003);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "161148149": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10003);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "161148147": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10022);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "149148160": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9013);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m9015);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "149148161": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9013);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m9015);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "147148160": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9013);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m9017);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "147148161": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9013);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m9017);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "147161149": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10011);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10018);
                ly.addView(im3);
                layout.addView(ly);
                break;
            }
            case "162161149": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10008);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10014);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10021);
                ly.addView(im3);
                layout.addView(ly);
                break;
            }
            case "149161147": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14015);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14018);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m14023);
                ly.addView(im3);
                layout.addView(ly);
                break;
            }
            case "149161162": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14015);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14018);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m14023);
                ly.addView(im3);
                layout.addView(ly);
                break;
            }
            case "166058059": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10007);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10018);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "166058057": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10007);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10018);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "167058059": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10007);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10018);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "167058057": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10007);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10018);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "59058167": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4011);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4015);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "59058166": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4011);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4015);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "57058167": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4010);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4014);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "57058166": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4010);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4014);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "57056218": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4008);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4015);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "55056218": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4011);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4017);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "218056057": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14013);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "218056055": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14013);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "184142143": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10009);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10016);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "184142141": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10009);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10016);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "183142143": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10009);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10016);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "183142141": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10009);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10016);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "143142184": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m8016);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "143142183": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m8016);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "141142184": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8009);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m8019);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "141142183": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8009);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m8019);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "183079080": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10011);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10014);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "183079078": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10011);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10014);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "182079080": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10011);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10014);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "182079078": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10011);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10014);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "80079183": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5016);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "80079182": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5008);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "78079183": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5009);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "78079182": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5016);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "79182198": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10007);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10015);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "79182197": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10007);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10015);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "181182198": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10009);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10018);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "181182197": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10009);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10018);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "198182079": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13218);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "198182181": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13218);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "197182079": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13123);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "197182181": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13123);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "177099176": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10012);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10022);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "177099100": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10002);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10011);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "176099100": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10003);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10013);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "176099098": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10014);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10023);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "100099177": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6020);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6021);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "100099176": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6012);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "98099177": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6012);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6005);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "98099176": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6021);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6029);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "176019018": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10003);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10018);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "176019020": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10003);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10018);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "122019018": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10007);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10011);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10021);
                ly.addView(im3);
                layout.addView(ly);
                break;
            }
            case "122019020": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10007);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10011);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10021);
                ly.addView(im3);
                layout.addView(ly);
                break;
            }
            case "173172216": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10020);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "173172215": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10020);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "171172216": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10020);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "171172215": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10020);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "216172173": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14013);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m14019);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m14025);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "216172171": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14013);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m14019);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m14025);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "215172173": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14012);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m14018);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m14026);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "215172171": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14012);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m14018);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m14026);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "217088087": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14013);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m14020);
                ly.addView(im3);
                layout.addView(ly);
                break;
            }
            case "217088089": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14013);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m14020);
                ly.addView(im3);
                layout.addView(ly);
                break;
            }
            case "216088087": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14011);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14017);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m14026);
                ly.addView(im3);
                layout.addView(ly);
                break;
            }
            case "216088089": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14011);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14017);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m14026);
                ly.addView(im3);
                layout.addView(ly);
                break;
            }
            case "89088217": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m5020);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "89088216": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m5020);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "87088217": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m5018);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "87088216": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m5018);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "170090089": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10010);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10014);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m10020);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "170090274": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10010);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m10014);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m10020);
                ly.addView(im4);
                layout.addView(ly);
                break;
            }
            case "89090274": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5010);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m5011);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m5012);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m5013);
                ly.addView(im4);
                ImageView im5 = new ImageView(this);
                im5.setImageResource(R.drawable.m5014);
                ly.addView(im5);
                layout.addView(ly);
                break;
            }
            case "131130272": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m8007);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m8008);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m8009);
                ly.addView(im4);
                ImageView im5 = new ImageView(this);
                im5.setImageResource(R.drawable.m8011);
                ly.addView(im5);
                layout.addView(ly);
                LinearLayout ly2 = new LinearLayout(this);
                ly2.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im6 = new ImageView(this);
                im6.setImageResource(R.drawable.m8016);
                ly2.addView(im6);
                ImageView im7 = new ImageView(this);
                im7.setImageResource(R.drawable.m8017);
                ly2.addView(im7);
                ImageView im8 = new ImageView(this);
                im8.setImageResource(R.drawable.m8018);
                ly2.addView(im8);
                ImageView im9 = new ImageView(this);
                im9.setImageResource(R.drawable.m8019);
                ly2.addView(im9);
                ImageView im10 = new ImageView(this);
                im10.setImageResource(R.drawable.m8023);
                ly2.addView(im10);
                layout.addView(ly2);
                break;
            }
            case "272130131": {
                TextView tv1 = new TextView(this);
                TextView tv2 = new TextView(this);
                tv1.setText("在沙河高教园、沙河、巩华城、生命科学园站上车，");
                tv2.setText("【不要】站在下列屏蔽门候车，下次换乘会更近：");
                layout.addView(tv1);
                layout.addView(tv2);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m27103);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m27104);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m27105);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m27110);
                ly.addView(im4);
                ImageView im5 = new ImageView(this);
                im5.setImageResource(R.drawable.m27111);
                ly.addView(im5);
                layout.addView(ly);
                LinearLayout ly2 = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im6 = new ImageView(this);
                im6.setImageResource(R.drawable.m27112);
                ly2.addView(im6);
                ImageView im7 = new ImageView(this);
                im7.setImageResource(R.drawable.m27113);
                ly2.addView(im7);
                ImageView im8 = new ImageView(this);
                im8.setImageResource(R.drawable.m27122);
                ly2.addView(im8);
                ImageView im9 = new ImageView(this);
                im9.setImageResource(R.drawable.m27123);
                ly2.addView(im9);
                ImageView im10 = new ImageView(this);
                im10.setImageResource(R.drawable.m27124);
                ly2.addView(im10);
                layout.addView(ly2);
                TextView tv21 = new TextView(this);
                TextView tv22 = new TextView(this);
                tv21.setText("在除以上4站的其余站上车，");
                tv22.setText("【不要】站在下列屏蔽门候车，下次换乘会更近：");
                layout.addView(tv21);
                layout.addView(tv22);
                LinearLayout ly21 = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im21 = new ImageView(this);
                im21.setImageResource(R.drawable.m27003);
                ly21.addView(im21);
                ImageView im22 = new ImageView(this);
                im22.setImageResource(R.drawable.m27004);
                ly21.addView(im22);
                ImageView im23 = new ImageView(this);
                im23.setImageResource(R.drawable.m27005);
                ly21.addView(im23);
                ImageView im24 = new ImageView(this);
                im24.setImageResource(R.drawable.m27010);
                ly21.addView(im24);
                ImageView im25 = new ImageView(this);
                im25.setImageResource(R.drawable.m27011);
                ly21.addView(im25);
                layout.addView(ly21);
                LinearLayout ly22 = new LinearLayout(this);
                ly22.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im26 = new ImageView(this);
                im26.setImageResource(R.drawable.m27012);
                ly22.addView(im26);
                ImageView im27 = new ImageView(this);
                im27.setImageResource(R.drawable.m27013);
                ly22.addView(im27);
                ImageView im28 = new ImageView(this);
                im28.setImageResource(R.drawable.m27022);
                ly22.addView(im28);
                ImageView im29 = new ImageView(this);
                im29.setImageResource(R.drawable.m27023);
                ly22.addView(im29);
                ImageView im210 = new ImageView(this);
                im210.setImageResource(R.drawable.m27024);
                ly22.addView(im210);
                layout.addView(ly22);
                break;
            }
            case "94048047": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9003);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m9004);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m9005);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m9009);
                ly.addView(im4);
                ImageView im5 = new ImageView(this);
                im5.setImageResource(R.drawable.m9010);
                ly.addView(im5);
                layout.addView(ly);
                LinearLayout ly2 = new LinearLayout(this);
                ly2.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im6 = new ImageView(this);
                im6.setImageResource(R.drawable.m9014);
                ly2.addView(im6);
                ImageView im7 = new ImageView(this);
                im7.setImageResource(R.drawable.m9015);
                ly2.addView(im7);
                ImageView im8 = new ImageView(this);
                im8.setImageResource(R.drawable.m9016);
                ly2.addView(im8);
                ImageView im9 = new ImageView(this);
                im9.setImageResource(R.drawable.m9020);
                ly2.addView(im9);
                ImageView im10 = new ImageView(this);
                im10.setImageResource(R.drawable.m9021);
                ly2.addView(im10);
                layout.addView(ly2);
                break;
            }
            case "47048094": {
                TextView tv = new TextView(this);
                tv.setText("【不要】站在下列屏蔽门候车，下次换乘会更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4001);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4005);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m4007);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m4008);
                ly.addView(im4);
                ImageView im5 = new ImageView(this);
                im5.setImageResource(R.drawable.m4010);
                ly.addView(im5);
                layout.addView(ly);
                LinearLayout ly2 = new LinearLayout(this);
                ly2.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im6 = new ImageView(this);
                im6.setImageResource(R.drawable.m4011);
                ly2.addView(im6);
                ImageView im7 = new ImageView(this);
                im7.setImageResource(R.drawable.m4012);
                ly2.addView(im7);
                ImageView im8 = new ImageView(this);
                im8.setImageResource(R.drawable.m4017);
                ly2.addView(im8);
                ImageView im9 = new ImageView(this);
                im9.setImageResource(R.drawable.m4018);
                ly2.addView(im9);
                ImageView im10 = new ImageView(this);
                im10.setImageResource(R.drawable.m4024);
                ly2.addView(im10);
                layout.addView(ly2);
                break;
            }
            case "8113114": {
                TextView tv = new TextView(this);
                tv.setText("【不要】站在下列屏蔽门候车，下次换乘会更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m9005);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m9009);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m9010);
                ly.addView(im4);
                ImageView im5 = new ImageView(this);
                im5.setImageResource(R.drawable.m9011);
                ly.addView(im5);
                layout.addView(ly);
                LinearLayout ly2 = new LinearLayout(this);
                ly2.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im6 = new ImageView(this);
                im6.setImageResource(R.drawable.m9012);
                ly2.addView(im6);
                ImageView im7 = new ImageView(this);
                im7.setImageResource(R.drawable.m9015);
                ly2.addView(im7);
                ImageView im8 = new ImageView(this);
                im8.setImageResource(R.drawable.m9016);
                ly2.addView(im8);
                ImageView im9 = new ImageView(this);
                im9.setImageResource(R.drawable.m9021);
                ly2.addView(im9);
                ImageView im10 = new ImageView(this);
                im10.setImageResource(R.drawable.m9022);
                ly2.addView(im10);
                layout.addView(ly2);
                break;
            }
            case "114113147": {
                TextView tv = new TextView(this);
                tv.setText("【不要】站在下列屏蔽门候车，下次换乘会更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m7005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m7009);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m7010);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m7011);
                ly.addView(im4);
                ImageView im5 = new ImageView(this);
                im5.setImageResource(R.drawable.m7015);
                ly.addView(im5);
                layout.addView(ly);
                LinearLayout ly2 = new LinearLayout(this);
                ly2.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im6 = new ImageView(this);
                im6.setImageResource(R.drawable.m7016);
                ly2.addView(im6);
                ImageView im7 = new ImageView(this);
                im7.setImageResource(R.drawable.m7016);
                ly2.addView(im7);
                ImageView im8 = new ImageView(this);
                im8.setImageResource(R.drawable.m7021);
                ly2.addView(im8);
                ImageView im9 = new ImageView(this);
                im9.setImageResource(R.drawable.m7022);
                ly2.addView(im9);
                ImageView im10 = new ImageView(this);
                im10.setImageResource(R.drawable.m7023);
                ly2.addView(im10);
                layout.addView(ly2);
                LinearLayout ly3 = new LinearLayout(this);
                ly3.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im11 = new ImageView(this);
                im11.setImageResource(R.drawable.m7024);
                ly3.addView(im11);
                ImageView im12 = new ImageView(this);
                im12.setImageResource(R.drawable.m7026);
                ly3.addView(im12);
                ImageView im13 = new ImageView(this);
                im13.setImageResource(R.drawable.m7028);
                ly3.addView(im13);
                layout.addView(ly3);
                break;
            }
            case "153154263": {
                TextView tv = new TextView(this);
                tv.setText("【不要】站在下列屏蔽门候车，下次换乘会更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9001);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m9007);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m9008);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m9017);
                ly.addView(im4);
                ImageView im5 = new ImageView(this);
                im5.setImageResource(R.drawable.m9018);
                ly.addView(im5);
                layout.addView(ly);
                LinearLayout ly2 = new LinearLayout(this);
                ly2.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im6 = new ImageView(this);
                im6.setImageResource(R.drawable.m9023);
                ly2.addView(im6);
                ImageView im7 = new ImageView(this);
                im7.setImageResource(R.drawable.m9024);
                ly2.addView(im7);
                layout.addView(ly2);
                break;

            }
            case "263154153": {
                TextView tv = new TextView(this);
                tv.setText("【不要】站在下列屏蔽门候车，下次换乘会更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m25001);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m25007);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m25008);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m25017);
                ly.addView(im4);
                ImageView im5 = new ImageView(this);
                im5.setImageResource(R.drawable.m25018);
                ly.addView(im5);
                layout.addView(ly);
                break;

            }
            case "169090089": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10021);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "169090274": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10021);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "89090170": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m5019);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "89090169": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m5019);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "274090170": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m24017);
                ly.addView(im1);
                break;

            }
            case "274090169": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m24017);
                ly.addView(im1);
                break;

            }
            case "192193273": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13109);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13117);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "194193273": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13210);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13215);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "135134195": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m8010);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m8014);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m8018);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "135134074": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m8010);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m8014);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m8018);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "133134195": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8007);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m8011);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m8015);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m8020);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "133134074": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8007);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m8011);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m8015);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m8020);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "139140221": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8007);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m8011);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m8015);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m8020);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "139140222": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8007);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m8011);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m8015);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m8020);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "195134135": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13103);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13104);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m13121);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "195134133": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13103);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13104);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m13121);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "141140221": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8003);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m8010);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m8021);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "141140222": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8003);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m8010);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m8021);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "74134135": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13203);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13221);
                ly.addView(im2);
                break;

            }
            case "74134133": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13203);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13221);
                ly.addView(im2);
                break;

            }
            case "75074196": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m5009);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m5010);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m5019);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "75074134": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m5009);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m5010);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m5019);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "242041040": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m16007);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m16012);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m16030);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m16034);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "242041042": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m16007);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m16012);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m16030);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m16034);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "25011010": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2208);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2209);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m2215);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m2216);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "25011012": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2208);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2209);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m2215);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m2216);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "26011010": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2108);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2109);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m2115);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m2116);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "26011012": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2108);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2109);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m2115);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m2116);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "113008007": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m9009);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m9017);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m9022);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "113008009": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m9009);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m9017);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m9022);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "146008007": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m9008);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m9016);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m9022);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "146008009": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m9008);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m9016);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m9022);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "22021020": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m30001);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m30010);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m30016);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m30024);
                ly.addView(im4);
                layout.addView(ly);
                break;

            }
            case "169074073": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13204);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13205);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m13221);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "169074075": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13204);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13205);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m13221);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "73074196": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m5015);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m5016);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "73074134": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m5015);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m5016);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "224197196": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m15106);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m15113);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m15119);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "224197182": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m15106);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m15113);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m15119);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "223197196": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m15205);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m15213);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m15218);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "223197182": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m15205);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m15213);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m15218);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "209208224": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14008);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14015);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m14022);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "209208197": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14008);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14015);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m14022);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "207208224": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14009);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14016);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m14023);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "207208197": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14009);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14016);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m14023);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "94048049":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m9010);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m9019);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m9020);
                ly.addView(im4);
                layout.addView(ly);
                break;


            }
            case "93094048": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6010);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6016);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m6026);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "93094146": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6010);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6016);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m6026);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "95094048": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6007);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6017);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m6023);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "95094146": {
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6007);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6017);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m6023);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "49048094":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4010);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m4019);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "114113008":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m7009);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m7016);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m7024);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "27054116":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4005);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m4017);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "27054117":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4005);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m4017);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "243022020":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m30008);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m30014);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m30024);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "145097036":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6006);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m6007);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "199034180":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13103);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13104);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m13105);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "199034035":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13103);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13104);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m13105);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "199034033":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13103);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13104);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m13105);
                ly.addView(im3);
                layout.addView(ly);
                break;

            }
            case "221140141":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m15119);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m15105);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "221140139":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m15119);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m15105);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "222140141":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m15220);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m15205);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "222140139":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m15220);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m15205);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "223077076":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m15106);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m15119);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "223077078":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m15106);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m15119);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "222077076":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m15206);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m15219);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "222077078":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m15206);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m15219);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "78077223":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5022);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m5005);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "78077222":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5022);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m5005);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "76077223":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5020);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m5003);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "76077222":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5020);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m5003);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "134074073":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13108);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13210);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "134074075":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13108);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m13210);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "214100099":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14009);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14016);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "214100101":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14009);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14016);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "20100099":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14015);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14022);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "20100101":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14015);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14022);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "99100214":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6014);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "99100020":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6014);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "101100214":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6019);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6027);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "101100020":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6019);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6027);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "50051024":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4021);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "50051096":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4021);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "52051024":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4020);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "52051096":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4020);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "95024023":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6014);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }case "95024025":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6014);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "51024023":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6020);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6028);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "51024025":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6020);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6028);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "25024095":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2105);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2115);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "25024051":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2105);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2115);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "23024095":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2208);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2217);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "23024051":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2208);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2217);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "161149148":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14016);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14017);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "161149150":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14016);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14017);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "215122123":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14016);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14023);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "20122175":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14008);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14015);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "215122175":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14016);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14023);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "20122123":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14008);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14015);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "123122215":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m7010);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m7016);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "123122020":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m7010);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m7016);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "175122215":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m7023);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m7016);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "175122020":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m7023);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m7016);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "119086030":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m7023);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m7018);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "119086087":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m7023);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m7018);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "120086030":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m7011);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m7014);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "120086087":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m7011);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m7014);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "54027028":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4003);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4022);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "54027026":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4003);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4022);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "12027028":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4003);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4022);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "12027026":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4003);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4022);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "55054116":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4008);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4020);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "55054117":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4008);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4020);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "117054055":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m7015);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m7023);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "117054027":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m7015);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m7023);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "116054055":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m7010);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m7016);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "116054027":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m7010);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m7016);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "122020019":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14019);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "122020021":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14019);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "100020019":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14012);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14024);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "100020021":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14012);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m14024);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "32017016":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2108);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2115);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "32017018":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2108);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2115);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "31017016":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2208);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2215);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "31017018":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2208);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2215);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "17032084":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2208);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2213);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "17032098":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2208);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2213);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "33032084":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2110);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2116);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "33032098":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2110);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2116);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "84032033":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6012);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6018);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "98032033":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6012);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6018);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "98032017":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6012);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6018);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "145097038":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6023);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m6024);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "38037144":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8008);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m8013);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "38037145":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2105);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2120);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "36037145":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2105);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2120);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "144037038":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2204);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2219);
                ly.addView(im2);
                layout.addView(ly);

                break;
            }
            case "36037144":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2204);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m2219);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "181180034":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10008);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10020);
                ly.addView(im2);
                layout.addView(ly);

                break;
            }
            case "181180286":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10008);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10020);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "179180034":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10017);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "179180286":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m10006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m10017);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "287180181":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m31001);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m31008);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "287180179":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m31001);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m31008);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "34180181":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m31001);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m31008);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "34180179":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m31001);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m31008);
                ly.addView(im2);
                layout.addView(ly);
                break;

            }
            case "131130273":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m8011);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m8023);
                ly.addView(im3);
                layout.addView(ly);
                break;
            }
            case "196197223":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13114);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "196197224":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13114);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "182197223":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13211);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "182197224":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m13211);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "224208207":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m15118);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "224208209":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m15118);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "197208207":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m15208);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "197208209":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m15208);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "24051050":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6009);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "24051052":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6009);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "96051050":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6024);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "96051052":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6024);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "42041242":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4005);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4019);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "40041242":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4004);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m4021);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "48094093":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9001);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "48094095":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9001);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "146094093":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9024);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "146094095":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9001);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "148149161":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9008);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m9018);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "150149161":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9006);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m9017);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "148149204":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9002);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "150149204":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m9022);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "204149148":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14015);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "204149150":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m14015);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "87086119":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5005);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "87086120":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5005);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "30086119":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5020);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "30086120":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5020);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "86030029":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5009);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "86030120":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5009);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "16030029":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5016);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "16030120":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5016);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "31030086":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2116);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "31030016":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2116);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "29030086":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2209);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "29030016":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2209);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "28027012":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2119);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "28027054":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2119);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "26027012":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2206);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "26027054":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2206);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "27012011":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4024);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "27012013":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4024);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "53012011":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4001);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "53012013":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m4001);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "30016017":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5020);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "30016015":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5020);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "85016017":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5004);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "85016015":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5004);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "32084083":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6023);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "32084085":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6023);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "97084083":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6010);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "97084085":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m6010);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "85084032":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5020);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "85084097":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5020);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "83084032":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5005);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "83084097":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5005);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "144097038":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8013);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "144097036":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8013);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "145037038":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8008);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "145037036":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8012);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "144037036":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m8012);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "82035036":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5013);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "82035034":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5013);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "81035036":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5010);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "81035034":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m5010);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "36035082":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2121);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "36035081":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2121);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "34035082":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2206);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "34035081":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2206);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "35034199":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2104);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "35034180":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2104);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "33034199":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2219);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "33034180":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m2219);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "180034199":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m31005);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "180034035":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m31005);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "180034033":{
                TextView tv = new TextView(this);
                tv.setText("站在以下屏蔽门处候车，下次换乘更近：");
                layout.addView(tv);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m31005);
                ly.addView(im1);
                layout.addView(ly);
                break;
            }
            case "273193192": {
                TextView tv1 = new TextView(this);
                TextView tv2 = new TextView(this);
                tv1.setText("在沙河高教园、沙河、巩华城、生命科学园站上车，");
                tv2.setText("【不要】站在下列屏蔽门候车，下次换乘会更近：");
                layout.addView(tv1);
                layout.addView(tv2);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m27104);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m27110);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m27116);
                ly.addView(im3);
                ImageView im4 = new ImageView(this);
                im4.setImageResource(R.drawable.m27122);
                ly.addView(im4);
                layout.addView(ly);
                TextView tv21 = new TextView(this);
                TextView tv22 = new TextView(this);
                tv21.setText("在除以上4站的其余站上车，");
                tv22.setText("【不要】站在下列屏蔽门候车，下次换乘会更近：");
                layout.addView(tv21);
                layout.addView(tv22);
                LinearLayout ly21 = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im21 = new ImageView(this);
                im21.setImageResource(R.drawable.m27004);
                ly21.addView(im21);
                ImageView im22 = new ImageView(this);
                im22.setImageResource(R.drawable.m27010);
                ly21.addView(im22);
                ImageView im23 = new ImageView(this);
                im23.setImageResource(R.drawable.m27016);
                ly21.addView(im23);
                ImageView im24 = new ImageView(this);
                im24.setImageResource(R.drawable.m27022);
                ly21.addView(im24);
                layout.addView(ly21);
                break;
            }
            case "273193194": {
                //TextView tv1 = new TextView(this);
                TextView tv2 = new TextView(this);
                //tv1.setText("在沙河高教园、沙河、巩华城、生命科学园站上车，");
                tv2.setText("【不要】站在下列屏蔽门候车，下次换乘会更近：");
                //layout.addView(tv1);
                layout.addView(tv2);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m27104);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m27122);
                ly.addView(im2);
                layout.addView(ly);
                break;
            }
            case "273130131": {
                //TextView tv1 = new TextView(this);
                TextView tv2 = new TextView(this);
                //tv1.setText("在沙河高教园、沙河、巩华城、生命科学园站上车，");
                tv2.setText("【不要】站在下列屏蔽门候车，下次换乘会更近：");
                //layout.addView(tv1);
                layout.addView(tv2);
                LinearLayout ly = new LinearLayout(this);
                ly.setOrientation(LinearLayout.HORIZONTAL);
                ImageView im1 = new ImageView(this);
                im1.setImageResource(R.drawable.m27204);
                ly.addView(im1);
                ImageView im2 = new ImageView(this);
                im2.setImageResource(R.drawable.m27211);
                ly.addView(im2);
                ImageView im3 = new ImageView(this);
                im3.setImageResource(R.drawable.m27223);
                ly.addView(im3);
                layout.addView(ly);
                break;
            }
            default:{
                TextView tv = new TextView(this);
                tv.setText("1号线屏蔽门仍在施工，请您选择乘客较少处上车");
                layout.addView(tv);
            }
        }

    }
  /*  public void addimagestart(int startid){
        ImageView start1,start2,start3;
        start1=(ImageView) findViewById(R.id.start1);
        start2=(ImageView) findViewById(R.id.start2);
        start3=(ImageView) findViewById(R.id.start3);
        switch (startid){
            case 0:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l1);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 1:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l1);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 2:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l1);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 3:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l1);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 4:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l1);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 5:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l1);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 6:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l1);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 7:{
                start1.setImageResource(R.drawable.l1);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l10);
                break;
            }
            case 8:{
                start1.setImageResource(R.drawable.l1);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l9);
                break;
            }
            case 9:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l1);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 10:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l1);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 11:{
                start1.setImageResource(R.drawable.l1);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l2);
                break;
            }
            case 12:{
                start1.setImageResource(R.drawable.l1);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l4);
                break;
            }
            case 13:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l1);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 14:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l1);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 15:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l1);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 16:{
                start1.setImageResource(R.drawable.l1);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l5);
                break;
            }
            case 17:{
                start1.setImageResource(R.drawable.l1);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l2);
                break;
            }
            case 18:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l1);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 19:{
                start1.setImageResource(R.drawable.l1);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l10);
                break;
            }
            case 20:{
                start1.setImageResource(R.drawable.l1);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l14);
                break;
            }
            case 21:{
                start1.setImageResource(R.drawable.l1);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l30);
                break;
            }
            case 22:{
                start1.setImageResource(R.drawable.l1);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l30);
                break;
            }

            case 23:{
                start1.setImageResource(R.drawable.l2);
                start2.setImageResource(R.drawable.l4);
                start3.setImageResource(R.drawable.l13);
                break;
            }
            case 24:{
                start1.setImageResource(R.drawable.l2);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l6);
                break;
            }

            case 25:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l2);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 26:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l2);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 27:{
                start1.setImageResource(R.drawable.l2);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l4);
                break;
            }
            case 28:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l2);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 29:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l2);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 30:{
                start1.setImageResource(R.drawable.l2);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l5);
                break;
            }
            case 31:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l2);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 32:{
                start1.setImageResource(R.drawable.l2);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l6);
                break;
            }
            case 33:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l2);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 34:{
                start1.setImageResource(R.drawable.l2);
                start2.setImageResource(R.drawable.l13);
                start3.setImageResource(R.drawable.l31);
                break;
            }
            case 35:{
                start1.setImageResource(R.drawable.l2);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l5);
                break;
            }
            case 36:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l2);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 37:{
                start1.setImageResource(R.drawable.l2);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l8);
                break;
            }
            case 38:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l2);
                //start3.setImageResource(R.drawable.l1);
                break;
            }

            case 39:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 40:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 41:{
                start1.setImageResource(R.drawable.l4);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l16);
                break;
            }
            case 42:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 43:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 44:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 45:{
                start1.setImageResource(R.drawable.l4);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l10);
                break;
            }
            case 46:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 47:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 48:{
                start1.setImageResource(R.drawable.l4);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l9);
                break;
            }
            case 49:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 50:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 51:{
                start1.setImageResource(R.drawable.l4);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l6);
                break;
            }
            case 52:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 53:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 54:{
                start1.setImageResource(R.drawable.l4);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l7);
                break;
            }
            case 55:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 56:{
                start1.setImageResource(R.drawable.l4);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l14);
                break;
            }
            case 57:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 58:{
                start1.setImageResource(R.drawable.l4);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l10);
                break;
            }
            case 59:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 60:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 61:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 62:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 63:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 64:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 65:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 66:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 67:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 68:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 69:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 70:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l4);
                //start3.setImageResource(R.drawable.l1);
                break;
            }

            case 71:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l5);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 72:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l5);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 73:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l5);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 74:{
                start1.setImageResource(R.drawable.l5);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l13);
                break;
            }
            case 75:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l5);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 76:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l5);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 77:{
                start1.setImageResource(R.drawable.l5);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l15);
                break;
            }
            case 78:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l5);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 79:{
                start1.setImageResource(R.drawable.l5);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l10);
                break;
            }
            case 80:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l5);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 81:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l5);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 82:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l5);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 83:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l5);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 84:{
                start1.setImageResource(R.drawable.l5);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l6);
                break;
            }
            case 85:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l5);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 86:{
                start1.setImageResource(R.drawable.l5);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l7);
                break;
            }
            case 87:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l5);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 88:{
                start1.setImageResource(R.drawable.l5);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l14);
                break;
            }
            case 89:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l5);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 90:{
                start1.setImageResource(R.drawable.l5);
                start2.setImageResource(R.drawable.l10);
                start3.setImageResource(R.drawable.l24);
                break;
            }

            case 91:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l6);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 92:{
                start1.setImageResource(R.drawable.l6);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l10);
                break;
            }
            case 93:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l6);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 94:{
                start1.setImageResource(R.drawable.l6);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l9);
                break;
            }
            case 95:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l6);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 96:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l6);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 97:{
                start1.setImageResource(R.drawable.l6);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l8);
                break;
            }
            case 98:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l6);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 99:{
                start1.setImageResource(R.drawable.l6);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l10);
                break;
            }
            case 100:{
                start1.setImageResource(R.drawable.l6);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l14);
                break;
            }
            case 101:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l6);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 102:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l6);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 103:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l6);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 104:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l6);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 105:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l6);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 106:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l6);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 107:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l6);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 108:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l6);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 109:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l6);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 110:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l6);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 111:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l6);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 112:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l6);
                //start3.setImageResource(R.drawable.l1);
                break;
            }

            case 113:{
                start1.setImageResource(R.drawable.l7);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l9);
                break;
            }
            case 114:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l7);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 115:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l7);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 116:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l7);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 117:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l7);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 118:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l7);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 119:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l7);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 120:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l7);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 121:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l7);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 122:{
                start1.setImageResource(R.drawable.l7);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l14);
                break;
            }
            case 123:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l7);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 124:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l7);
                //start3.setImageResource(R.drawable.l1);
                break;
            }

            case 125:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l7);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 126:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l7);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 127:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l7);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 128:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l7);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 129:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l7);
                //start3.setImageResource(R.drawable.l1);
                break;
            }

            case 130:{
                start1.setImageResource(R.drawable.l8);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l27);
                break;
            }
            case 131:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l8);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 132:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l8);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 133:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l8);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 134:{
                start1.setImageResource(R.drawable.l8);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l13);
                break;
            }
            case 135:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l8);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 136:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l8);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 137:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l8);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 138:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l8);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 139:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l8);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 140:{
                start1.setImageResource(R.drawable.l8);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l15);
                break;
            }
            case 141:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l8);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 142:{
                start1.setImageResource(R.drawable.l8);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l10);
                break;
            }
            case 143:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l8);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 144:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l8);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 145:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l8);
                //start3.setImageResource(R.drawable.l1);
                break;
            }

            case 146:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l9);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 147:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l9);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 148:{
                start1.setImageResource(R.drawable.l9);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l10);
                break;
            }
            case 149:{
                start1.setImageResource(R.drawable.l9);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l14);
                break;
            }
            case 150:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l9);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 151:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l9);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 152:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l9);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 153:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l9);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 154:{
                start1.setImageResource(R.drawable.l9);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l25);
                break;
            }

            case 155:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 156:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 157:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 158:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 159:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 160:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 161:{
                start1.setImageResource(R.drawable.l10);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l14);
                break;
            }
            case 162:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 163:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 164:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 165:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 166:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 167:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 168:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 169:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 170:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 171:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 172:{
                start1.setImageResource(R.drawable.l10);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l14);
                break;
            }
            case 173:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 174:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 175:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 176:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 177:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 178:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 179:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 180:{
                start1.setImageResource(R.drawable.l10);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l31);
                break;
            }
            case 181:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 182:{
                start1.setImageResource(R.drawable.l10);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l13);
                break;
            }
            case 183:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 184:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 185:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 186:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 187:{
                start1.setImageResource(R.drawable.l10);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l13);
                break;
            }
            case 188:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 189:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l10);
                //start3.setImageResource(R.drawable.l1);
                break;
            }

            case 190:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l13);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 191:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l13);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 192:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l13);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 193:{
                start1.setImageResource(R.drawable.l13);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l27);
                break;
            }
            case 194:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l13);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 195:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l13);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 196:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l13);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 197:{
                start1.setImageResource(R.drawable.l13);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l15);
                break;
            }
            case 198:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l13);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 199:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l13);
                //start3.setImageResource(R.drawable.l1);
                break;
            }

            case 200:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 201:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 202:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 203:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 204:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 205:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 206:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 207:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 208:{
                start1.setImageResource(R.drawable.l14);
                //start2.setImageResource(R.drawable.l1);
                start3.setImageResource(R.drawable.l15);
                break;
            }
            case 209:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 210:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 211:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 212:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 213:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 214:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 215:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 216:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 217:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 218:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l14);
                //start3.setImageResource(R.drawable.l1);
                break;
            }

            case 219:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l15);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 220:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l15);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 221:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l15);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 222:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l15);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 223:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l15);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 224:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l15);
                //start3.setImageResource(R.drawable.l1);
                break;
            }

            case 225:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l15);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 226:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l15);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 227:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l15);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 228:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l15);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 229:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l15);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 230:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l15);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 231:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l15);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 232:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l15);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 233:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l15);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 234:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l15);
                //start3.setImageResource(R.drawable.l1);
                break;
            }

            case 235:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l16);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 236:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l16);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 237:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l16);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 238:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l16);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 239:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l16);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 240:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l16);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 241:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l16);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 242:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l16);
                //start3.setImageResource(R.drawable.l1);
                break;
            }

            case 243:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l30);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 244:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l30);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 245:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l30);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 246:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l30);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 247:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l30);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 248:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l30);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 249:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l30);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 250:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l30);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 251:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l30);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 252:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l30);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 253:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l30);
                //start3.setImageResource(R.drawable.l1);
                break;
            }

            case 254:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l25);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 255:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l25);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 256:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l25);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 257:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l25);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 258:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l25);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 259:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l25);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 260:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l25);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 261:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l25);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 262:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l25);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 263:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l25);
                //start3.setImageResource(R.drawable.l1);
                break;
            }

            case 264:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l27);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 265:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l27);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 266:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l27);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 267:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l27);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 268:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l27);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 269:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l27);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 270:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l27);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 271:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l27);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 272:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l27);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 273:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l27);
                //start3.setImageResource(R.drawable.l1);
                break;
            }

            case 274:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l24);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 275:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l24);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 276:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l24);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 277:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l24);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 278:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l24);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 279:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l24);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 280:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l24);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 281:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l24);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 282:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l24);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 283:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l24);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 284:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l24);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 285:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l24);
                //start3.setImageResource(R.drawable.l1);
                break;
            }

            case 286:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l31);
                //start3.setImageResource(R.drawable.l1);
                break;
            }
            case 287:{
                //start1.setImageResource(R.drawable.l1);
                start2.setImageResource(R.drawable.l31);
                //start3.setImageResource(R.drawable.l1);
                break;
            }

        }
    }
    public void addimageend(int endid){
        ImageView end1,end2,end3;
        end1=(ImageView) findViewById(R.id.end1);
        end2=(ImageView) findViewById(R.id.end2);
        end3=(ImageView) findViewById(R.id.end3);
        switch (endid){
            case 0:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l1);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 1:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l1);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 2:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l1);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 3:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l1);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 4:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l1);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 5:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l1);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 6:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l1);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 7:{
                end1.setImageResource(R.drawable.l1);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l10);
                break;
            }
            case 8:{
                end1.setImageResource(R.drawable.l1);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l9);
                break;
            }
            case 9:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l1);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 10:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l1);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 11:{
                end1.setImageResource(R.drawable.l1);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l2);
                break;
            }
            case 12:{
                end1.setImageResource(R.drawable.l1);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l4);
                break;
            }
            case 13:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l1);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 14:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l1);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 15:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l1);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 16:{
                end1.setImageResource(R.drawable.l1);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l5);
                break;
            }
            case 17:{
                end1.setImageResource(R.drawable.l1);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l2);
                break;
            }
            case 18:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l1);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 19:{
                end1.setImageResource(R.drawable.l1);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l10);
                break;
            }
            case 20:{
                end1.setImageResource(R.drawable.l1);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l14);
                break;
            }
            case 21:{
                end1.setImageResource(R.drawable.l1);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l30);
                break;
            }
            case 22:{
                end1.setImageResource(R.drawable.l1);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l30);
                break;
            }

            case 23:{
                end1.setImageResource(R.drawable.l2);
                end2.setImageResource(R.drawable.l4);
                end3.setImageResource(R.drawable.l13);
                break;
            }
            case 24:{
                end1.setImageResource(R.drawable.l2);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l6);
                break;
            }

            case 25:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l2);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 26:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l2);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 27:{
                end1.setImageResource(R.drawable.l2);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l4);
                break;
            }
            case 28:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l2);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 29:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l2);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 30:{
                end1.setImageResource(R.drawable.l2);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l5);
                break;
            }
            case 31:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l2);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 32:{
                end1.setImageResource(R.drawable.l2);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l6);
                break;
            }
            case 33:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l2);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 34:{
                end1.setImageResource(R.drawable.l2);
                end2.setImageResource(R.drawable.l13);
                end3.setImageResource(R.drawable.l31);
                break;
            }
            case 35:{
                end1.setImageResource(R.drawable.l2);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l5);
                break;
            }
            case 36:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l2);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 37:{
                end1.setImageResource(R.drawable.l2);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l8);
                break;
            }
            case 38:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l2);
                //end3.setImageResource(R.drawable.l1);
                break;
            }

            case 39:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 40:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 41:{
                end1.setImageResource(R.drawable.l4);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l16);
                break;
            }
            case 42:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 43:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 44:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 45:{
                end1.setImageResource(R.drawable.l4);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l10);
                break;
            }
            case 46:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 47:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 48:{
                end1.setImageResource(R.drawable.l4);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l9);
                break;
            }
            case 49:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 50:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 51:{
                end1.setImageResource(R.drawable.l4);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l6);
                break;
            }
            case 52:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 53:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 54:{
                end1.setImageResource(R.drawable.l4);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l7);
                break;
            }
            case 55:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 56:{
                end1.setImageResource(R.drawable.l4);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l14);
                break;
            }
            case 57:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 58:{
                end1.setImageResource(R.drawable.l4);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l10);
                break;
            }
            case 59:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 60:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 61:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 62:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 63:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 64:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 65:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 66:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 67:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 68:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 69:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 70:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l4);
                //end3.setImageResource(R.drawable.l1);
                break;
            }

            case 71:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l5);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 72:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l5);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 73:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l5);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 74:{
                end1.setImageResource(R.drawable.l5);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l13);
                break;
            }
            case 75:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l5);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 76:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l5);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 77:{
                end1.setImageResource(R.drawable.l5);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l15);
                break;
            }
            case 78:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l5);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 79:{
                end1.setImageResource(R.drawable.l5);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l10);
                break;
            }
            case 80:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l5);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 81:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l5);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 82:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l5);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 83:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l5);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 84:{
                end1.setImageResource(R.drawable.l5);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l6);
                break;
            }
            case 85:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l5);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 86:{
                end1.setImageResource(R.drawable.l5);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l7);
                break;
            }
            case 87:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l5);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 88:{
                end1.setImageResource(R.drawable.l5);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l14);
                break;
            }
            case 89:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l5);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 90:{
                end1.setImageResource(R.drawable.l5);
                end2.setImageResource(R.drawable.l10);
                end3.setImageResource(R.drawable.l24);
                break;
            }

            case 91:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l6);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 92:{
                end1.setImageResource(R.drawable.l6);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l10);
                break;
            }
            case 93:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l6);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 94:{
                end1.setImageResource(R.drawable.l6);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l9);
                break;
            }
            case 95:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l6);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 96:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l6);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 97:{
                end1.setImageResource(R.drawable.l6);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l8);
                break;
            }
            case 98:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l6);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 99:{
                end1.setImageResource(R.drawable.l6);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l10);
                break;
            }
            case 100:{
                end1.setImageResource(R.drawable.l6);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l14);
                break;
            }
            case 101:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l6);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 102:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l6);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 103:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l6);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 104:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l6);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 105:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l6);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 106:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l6);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 107:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l6);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 108:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l6);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 109:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l6);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 110:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l6);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 111:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l6);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 112:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l6);
                //end3.setImageResource(R.drawable.l1);
                break;
            }

            case 113:{
                end1.setImageResource(R.drawable.l7);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l9);
                break;
            }
            case 114:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l7);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 115:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l7);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 116:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l7);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 117:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l7);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 118:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l7);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 119:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l7);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 120:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l7);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 121:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l7);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 122:{
                end1.setImageResource(R.drawable.l7);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l14);
                break;
            }
            case 123:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l7);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 124:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l7);
                //end3.setImageResource(R.drawable.l1);
                break;
            }

            case 125:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l7);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 126:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l7);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 127:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l7);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 128:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l7);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 129:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l7);
                //end3.setImageResource(R.drawable.l1);
                break;
            }

            case 130:{
                end1.setImageResource(R.drawable.l8);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l27);
                break;
            }
            case 131:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l8);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 132:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l8);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 133:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l8);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 134:{
                end1.setImageResource(R.drawable.l8);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l13);
                break;
            }
            case 135:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l8);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 136:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l8);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 137:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l8);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 138:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l8);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 139:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l8);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 140:{
                end1.setImageResource(R.drawable.l8);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l15);
                break;
            }
            case 141:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l8);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 142:{
                end1.setImageResource(R.drawable.l8);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l10);
                break;
            }
            case 143:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l8);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 144:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l8);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 145:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l8);
                //end3.setImageResource(R.drawable.l1);
                break;
            }

            case 146:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l9);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 147:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l9);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 148:{
                end1.setImageResource(R.drawable.l9);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l10);
                break;
            }
            case 149:{
                end1.setImageResource(R.drawable.l9);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l14);
                break;
            }
            case 150:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l9);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 151:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l9);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 152:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l9);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 153:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l9);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 154:{
                end1.setImageResource(R.drawable.l9);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l25);
                break;
            }


            case 158:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 159:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 160:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 161:{
                end1.setImageResource(R.drawable.l10);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l14);
                break;
            }
            case 162:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 163:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 164:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 165:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 166:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 167:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 168:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 169:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 170:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 171:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 172:{
                end1.setImageResource(R.drawable.l10);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l14);
                break;
            }
            case 173:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 174:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 175:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 176:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 177:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 178:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 179:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 180:{
                end1.setImageResource(R.drawable.l10);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l31);
                break;
            }
            case 181:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 182:{
                end1.setImageResource(R.drawable.l10);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l13);
                break;
            }
            case 183:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 184:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 185:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 186:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 187:{
                end1.setImageResource(R.drawable.l10);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l13);
                break;
            }
            case 188:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 189:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l10);
                //end3.setImageResource(R.drawable.l1);
                break;
            }

            case 190:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l13);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 191:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l13);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 192:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l13);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 193:{
                end1.setImageResource(R.drawable.l13);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l27);
                break;
            }
            case 194:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l13);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 195:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l13);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 196:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l13);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 197:{
                end1.setImageResource(R.drawable.l13);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l15);
                break;
            }
            case 198:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l13);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 199:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l13);
                //end3.setImageResource(R.drawable.l1);
                break;
            }

            case 200:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 201:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 202:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 203:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 204:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 205:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 206:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 207:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 208:{
                end1.setImageResource(R.drawable.l14);
                //end2.setImageResource(R.drawable.l1);
                end3.setImageResource(R.drawable.l15);
                break;
            }
            case 209:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 210:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 211:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 212:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 213:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 214:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 215:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 216:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 217:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 218:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l14);
                //end3.setImageResource(R.drawable.l1);
                break;
            }

            case 219:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l15);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 220:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l15);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 221:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l15);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 222:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l15);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 223:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l15);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 224:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l15);
                //end3.setImageResource(R.drawable.l1);
                break;
            }

            case 225:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l15);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 226:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l15);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 227:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l15);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 228:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l15);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 229:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l15);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 230:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l15);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 231:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l15);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 232:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l15);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 233:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l15);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 234:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l15);
                //end3.setImageResource(R.drawable.l1);
                break;
            }

            case 235:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l16);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 236:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l16);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 237:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l16);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 238:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l16);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 239:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l16);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 240:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l16);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 241:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l16);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 242:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l16);
                //end3.setImageResource(R.drawable.l1);
                break;
            }

            case 243:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l30);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 244:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l30);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 245:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l30);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 246:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l30);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 247:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l30);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 248:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l30);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 249:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l30);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 250:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l30);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 251:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l30);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 252:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l30);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 253:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l30);
                //end3.setImageResource(R.drawable.l1);
                break;
            }

            case 254:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l25);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 255:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l25);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 256:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l25);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 257:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l25);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 258:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l25);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 259:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l25);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 260:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l25);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 261:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l25);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 262:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l25);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 263:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l25);
                //end3.setImageResource(R.drawable.l1);
                break;
            }

            case 264:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l27);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 265:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l27);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 266:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l27);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 267:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l27);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 268:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l27);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 269:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l27);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 270:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l27);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 271:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l27);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 272:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l27);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 273:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l27);
                //end3.setImageResource(R.drawable.l1);
                break;
            }

            case 274:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l24);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 275:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l24);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 276:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l24);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 277:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l24);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 278:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l24);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 279:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l24);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 280:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l24);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 281:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l24);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 282:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l24);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 283:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l24);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 284:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l24);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 285:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l24);
                //end3.setImageResource(R.drawable.l1);
                break;
            }

            case 286:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l31);
                //end3.setImageResource(R.drawable.l1);
                break;
            }
            case 287:{
                //end1.setImageResource(R.drawable.l1);
                end2.setImageResource(R.drawable.l31);
                //end3.setImageResource(R.drawable.l1);
                break;
            }

        }
    }*/


    public void onBackPressed() {
        finish();
    }

}

