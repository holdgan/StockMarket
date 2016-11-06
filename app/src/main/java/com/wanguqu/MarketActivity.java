package com.wanguqu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.meetme.android.horizontallistview.HorizontalListView;
import com.wanguqu.leftrightscrool.TableActivity;

import java.util.List;

public class MarketActivity extends Activity implements View.OnClickListener {
    //    private AutoCompleteTextView ui_autocomplete;
//    private ArrayAdapter<String> arrayAdapter;
    private Context mContext;
    private HorizontalListView hlv_market_optional;
    private HorizontalListView hlv_market_grail;
    private HorizontalListView hlv_market_plate;
    private HorizontalListView hlv_market_hotspot;
    private HorizontalListView hlv_market_stock;
    private TextView tv_market_optional_all;
    private TextView tv_market_grail_all;
    private TextView tv_market_plate_all;
    private TextView tv_market_hotspot_all;
    private TextView tv_market_stock_all;
    List<MarketSelectData> mList;
//    private AutoCompleteTextView ui_autocomplete;
    private View ui_search;


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
        setContentView(R.layout.activity_market);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = MarketActivity.this;

        initView();

        MarketArrayAdapter adapter = new MarketArrayAdapter(this, mMarketArrayData);

        hlv_market_optional.setAdapter(adapter);
        hlv_market_grail.setAdapter(adapter);
        hlv_market_plate.setAdapter(adapter);
        hlv_market_hotspot.setAdapter(adapter);
        hlv_market_stock.setAdapter(adapter);

//        buildAppData();
//        findView();

        tv_market_optional_all.setOnClickListener(this);
        tv_market_grail_all.setOnClickListener(this);
        tv_market_plate_all.setOnClickListener(this);
        tv_market_hotspot_all.setOnClickListener(this);
        tv_market_stock_all.setOnClickListener(this);

    }

//    private void buildAppData() {
//        String[] names = {"abc", "allen", "bird", "bike", "book", "cray",
//                "david", "demon", "eclipse", "felling", "frank", "google",
//                "green", "hill", "hook", "jin zhiwen", "jack", "jay", "king", "kevin", "kobe",
//                "lily", "lucy", "mike", "nike", "nail", "open", "open cv",
//                "panda", "pp", "queue", "ray allen", "risk", "tim cook", "T-MAC", "tony allen",
//                "x man", "x phone", "yy", "world", "w3c", "zoom", "zhu ziqing"};
//
//        mList = new ArrayList<MarketSelectData>();
//
//        for (int i = 0; i < names.length; i++) {
//            MarketSelectData pc = new MarketSelectData(100 + i, names[i], "1861234567"
//                    + i, names[i].concat("@gmail.com"));
//            mList.add(pc);
//        }
//
//    }

//    private void findView() {
//        ui_autocomplete = (AutoCompleteTextView) findViewById(R.id.ui_autocomplete);
//        MarketSelectAdapter mAdapter = new MarketSelectAdapter(mList, getApplicationContext());
//        ui_autocomplete.setAdapter(mAdapter);
//        ui_autocomplete.setThreshold(1);  //设置输入一个字符 提示，默认为2
//
//    }


    private void initView() {
        hlv_market_optional = (HorizontalListView) findViewById(R.id.hlv_market_optional);
        hlv_market_grail = (HorizontalListView) findViewById(R.id.hlv_market_grail);
        hlv_market_plate = (HorizontalListView) findViewById(R.id.hlv_market_plate);
        hlv_market_hotspot = (HorizontalListView) findViewById(R.id.hlv_market_hotspot);
        hlv_market_stock = (HorizontalListView) findViewById(R.id.hlv_market_stock);

        tv_market_optional_all = (TextView) findViewById(R.id.tv_market_optional_all);
        tv_market_grail_all = (TextView) findViewById(R.id.tv_market_grail_all);
        tv_market_plate_all = (TextView) findViewById(R.id.tv_market_plate_all);
        tv_market_hotspot_all = (TextView) findViewById(R.id.tv_market_hotspot_all);
        tv_market_stock_all = (TextView) findViewById(R.id.tv_market_stock_all);

        ui_search = (View) findViewById(R.id.ui_search);
        ui_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.tv_market_optional_all:
                intent.setClass(mContext, TableActivity.class);
                intent.putExtra("category", getResources().getString(R.string.market_optional));
                startActivity(intent);
                break;
            case R.id.tv_market_grail_all:
                intent.setClass(mContext, TableActivity.class);
                intent.putExtra("category", getResources().getString(R.string.market_grail));
                startActivity(intent);
                break;
            case R.id.tv_market_plate_all:
                intent.setClass(mContext, TableActivity.class);
                intent.putExtra("category", getResources().getString(R.string.market_plate));
                startActivity(intent);
                break;
            case R.id.tv_market_hotspot_all:
                intent.setClass(mContext, TableActivity.class);
                intent.putExtra("category", getResources().getString(R.string.market_hotspot));
                startActivity(intent);
                break;
            case R.id.tv_market_stock_all:
                intent.setClass(mContext, TableActivity.class);
                intent.putExtra("category", getResources().getString(R.string.market_stock));
                startActivity(intent);
                break;
            case R.id.ui_search:
                intent.setClass(mContext, SearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
