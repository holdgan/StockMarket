package com.wanguqu;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

public class ShareDetailsPopwindow extends Activity {
    private Context mContext;
    private LinearLayout ll_record_dialog_main;
    private TextView tv_record_dialog_inout;
    private TextView tv_record_dialog_status;
    private TextView tv_record_dialog_number;
    private TextView tv_record_dialog_inouttext;
    private TextView tv_record_dialog_address;
    private TextView tv_record_dialog_tradeid;
    private TextView tv_record_dialog_time;
    private RelativeLayout rl_record_dialog_close;
    private Gson gson = new Gson();
    private SharedPreferences sp = null;
    private Bundle bundle;
    private Boolean isverify;
    private Boolean passwordsendeye = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.pop_sharedetails);
        mContext = ShareDetailsPopwindow.this;


        ll_record_dialog_main = (LinearLayout) findViewById(R.id.ll_record_dialog_main);
        tv_record_dialog_inout = (TextView) findViewById(R.id.tv_record_dialog_inout);
        tv_record_dialog_status = (TextView) findViewById(R.id.tv_record_dialog_status);
        tv_record_dialog_number = (TextView) findViewById(R.id.tv_record_dialog_number);
        tv_record_dialog_inouttext = (TextView) findViewById(R.id.tv_record_dialog_inouttext);
        tv_record_dialog_address = (TextView) findViewById(R.id.tv_record_dialog_address);
        tv_record_dialog_tradeid = (TextView) findViewById(R.id.tv_record_dialog_tradeid);
        tv_record_dialog_time = (TextView) findViewById(R.id.tv_record_dialog_time);
        rl_record_dialog_close = (RelativeLayout) findViewById(R.id.rl_record_dialog_close);



        ll_record_dialog_main.getBackground().setAlpha(70);



        rl_record_dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        tv_record_dialog_address.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(tv_record_dialog_address.getText());
            }
        });
        tv_record_dialog_tradeid.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(tv_record_dialog_tradeid.getText());
            }
        });

    }



}
