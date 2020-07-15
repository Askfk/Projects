package com.bjtu.etransfer.etransfer;

import android.content.Context;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BusShowPath extends AppCompatActivity {
    private ListView lv ;
    private BusPathSearch bps = new BusPathSearch();
    private ArrayList<Integer> mResult;
    private ArrayList<String> mResultString;
    private SharedPreferences sharedPreferences;
    private String busStart, busEnd;
    private TextView startbus, endbus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_show_path);
        sharedPreferences = getSharedPreferences("MassageSavers", Context.MODE_PRIVATE);
        startbus = (TextView)findViewById(R.id.startbus);
        endbus = (TextView)findViewById(R.id.endbus);
        busStart = sharedPreferences.getString("StartBusStation", null);
        busEnd = sharedPreferences.getString("EndBusStation", null);
        startbus.setText(busStart);
        endbus.setText(busEnd);
        bps.setBeginVertex(busStart);
        bps.setEndVertex(busEnd);
        bps.startSearch();
        bps.makePathPrompt();
        mResultString = new ArrayList<String>();
        mResult = new ArrayList<Integer>();
        mResultString = bps.getResult();
        mResult = bps.mPath;
        lv = (ListView)findViewById(R.id.buslist) ;
        MySimpleAdapter adapter = new MySimpleAdapter(this, getData(), R.layout.itembus,
                new String[]{"img","tv","tv2"},
                new int[]{R.id.iv_bus1,R.id.tv_bus1,R.id.tv_bus2});
        lv.setAdapter(adapter);
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


        for(int i = 0;i < mResultString.size();i++) {
            String line = "";
            for(int j = 0;j < bps.graph[mResult.get(i)].arrLinesize();j++)
            {
                line = line + bps.graph[mResult.get(i)].getArrline(j) + "　";
            }

            item = new HashMap<String, Object>();
            if(i == 0)
            {
                item.put("img", R.drawable.bushead);
            }
            else if(i == mResultString.size() - 1)
            {
                item.put("img", R.drawable.busfoot);
            }
            else
            {
                item.put("img", R.drawable.busbody);
            }
            item.put("tv", mResultString.get(i));
            item.put("tv2", line);
            data.add(item);
        }


        return data ;
    }

    public void onBackPressed() {
        finish();
    }
}
