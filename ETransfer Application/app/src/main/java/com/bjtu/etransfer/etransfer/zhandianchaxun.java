package com.bjtu.etransfer.etransfer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bjtu.etransfer.etransfer.SideBar.OnTouchingLetterChangedListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class zhandianchaxun extends Activity {
    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private ClearEditText mClearEditText;
    private SharedPreferences sharedPreferences;
    private int k;
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;

    private PinyinComparator pinyinComparator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhandianxuanze);

        sharedPreferences = getSharedPreferences("MassageSavers", Context.MODE_PRIVATE);
        initViews();
    }

    private void initViews() {

        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();

        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialog);

        sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {

                int position = adapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    sortListView.setSelection(position);
                }

            }
        });

        sortListView = (ListView) findViewById(R.id.country_lvcountry);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Editor editor = sharedPreferences.edit();
                k = sharedPreferences.getInt("Position", 0);
                switch(k){
                    case 1: {
                        editor.putString("beginVertex", ((SortModel)adapter.getItem(position)).getName());
                        break;
                    }
                    case 2:{
                        editor.putString("endVertex", ((SortModel)adapter.getItem(position)).getName());
                        break;
                    }
                }
                editor.commit();
                Intent intent = new Intent();
                intent.setClass(zhandianchaxun.this, MainActivity.class);
                zhandianchaxun.this.startActivity(intent);
                finish();
            }
        });

        SourceDateList = filledData(getResources().getStringArray(R.array.zhandian));

        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(this, SourceDateList);
        sortListView.setAdapter(adapter);


        mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }



    private List<SortModel> filledData(String [] zhandian){
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for(int i=0; i<zhandian.length; i++){
            SortModel sortModel = new SortModel();
            sortModel.setName(zhandian[i]);

            String pinyin = characterParser.getSelling(zhandian[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    private void filterData(String filterStr){
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = SourceDateList;
        }else{
            filterDateList.clear();
            for(SortModel sortModel : SourceDateList){
                String name = sortModel.getName();
                if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
                    filterDateList.add(sortModel);
                }
            }
        }

        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(zhandianchaxun.this, MainActivity.class);
        zhandianchaxun.this.startActivity(intent);
        finish();
    }

}
