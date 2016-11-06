package com.wanguqu;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity implements View.OnClickListener {
    //    private AutoCompleteTextView ui_autocomplete;
//    private ArrayAdapter<String> arrayAdapter;
    private Context mContext;
    List<MarketSelectData> mList;
    private AutoCompleteTextView ui_autocomplete;
    private GridView myGridView;


    private MarketArrayData[] mMarketArrayData = new MarketArrayData[]{
            new MarketArrayData(Color.LTGRAY, "复星国际", "11.89", "0.8", "+5.2%", true),
            new MarketArrayData(Color.LTGRAY, "复星国际", "11.89", "0.8", "+5.2%", false),
            new MarketArrayData(Color.LTGRAY, "复星国际", "11.89", "0.8", "+5.2%", true),
            new MarketArrayData(Color.LTGRAY, "复星国际", "11.89", "0.8", "+5.2%", false),
            new MarketArrayData(Color.LTGRAY, "复星国际", "11.89", "0.8", "+5.2%", true),
            new MarketArrayData(Color.LTGRAY, "复星国际", "11.89", "0.8", "+5.2%", true),
            new MarketArrayData(Color.LTGRAY, "复星国际", "11.89", "0.8", "+5.2%", false),
            new MarketArrayData(Color.LTGRAY, "复星国际", "11.89", "0.8", "+5.2%", true),
            new MarketArrayData(Color.LTGRAY, "复星国际", "11.89", "0.8", "+5.2%", true),
            new MarketArrayData(Color.LTGRAY, "复星国际", "11.89", "0.8", "+5.2%", true)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = SearchActivity.this;

        initView();

        buildAppData();
        findView();

        myGridView = (GridView) findViewById(R.id.gv_search);
        GrideAdapter adapter = new GrideAdapter(this);
        myGridView.setAdapter(adapter);
        //添加监听器
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, position + "", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void buildAppData() {
        String[] names = {"abc", "allen", "bird", "bike", "book", "cray",
                "david", "demon", "eclipse", "felling", "frank", "google",
                "green", "hill", "hook", "jin zhiwen", "jack", "jay", "king", "kevin", "kobe",
                "lily", "lucy", "mike", "nike", "nail", "open", "open cv",
                "panda", "pp", "queue", "ray allen", "risk", "tim cook", "T-MAC", "tony allen",
                "x man", "x phone", "yy", "world", "w3c", "zoom", "zhu ziqing"};

        mList = new ArrayList<MarketSelectData>();

        for (int i = 0; i < names.length; i++) {
            MarketSelectData pc = new MarketSelectData(100 + i, names[i], "1861234567"
                    + i, names[i].concat("@gmail.com"));
            mList.add(pc);
        }

    }

    private void findView() {
        ui_autocomplete = (AutoCompleteTextView) findViewById(R.id.ui_autocomplete);
        MarketSelectAdapter mAdapter = new MarketSelectAdapter(mList, getApplicationContext());
        ui_autocomplete.setAdapter(mAdapter);
        ui_autocomplete.setThreshold(1);  //设置输入一个字符 提示，默认为2

    }


    private void initView() {
        LinearLayout ll_top_left = (LinearLayout) findViewById(R.id.ll_top_left);
        ll_top_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_top_left:
                finish();
                break;
            default:
                break;
        }
    }


}
