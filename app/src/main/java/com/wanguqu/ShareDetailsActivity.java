package com.wanguqu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShareDetailsActivity extends Activity implements View.OnClickListener {
    //    private AutoCompleteTextView ui_autocomplete;
//    private ArrayAdapter<String> arrayAdapter;
    private Context mContext;
    private String shareCode = "";

    private TextView tv_top_share_follow;
    private LinearLayout ll_top_share_nofollow;
    private TextView tv_share_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedetails);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = ShareDetailsActivity.this;

        shareCode = getIntent().getStringExtra("stock");

        initView();

        follow();


    }


    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()) {
            case R.id.ll_top_left:
                finish();
                break;
            case R.id.tv_top_share_follow:
                follow();
                break;
            case R.id.ll_top_share_nofollow:
                noFollow();
                break;
            case R.id.tv_share_more:
                intent.setClass(mContext,ShareDetailsPopwindow.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void initView() {
        LinearLayout ll_top_left = (LinearLayout) findViewById(R.id.ll_top_left);
        TextView tv_top_share_name = (TextView) findViewById(R.id.tv_top_share_name);
        TextView tv_top_share_code = (TextView) findViewById(R.id.tv_top_share_code);
        tv_share_more = (TextView) findViewById(R.id.tv_share_more);

        tv_top_share_follow = (TextView) findViewById(R.id.tv_top_share_follow);
        ll_top_share_nofollow = (LinearLayout) findViewById(R.id.ll_top_share_nofollow);

        ll_top_left.setOnClickListener(this);
        tv_top_share_follow.setOnClickListener(this);
        ll_top_share_nofollow.setOnClickListener(this);
        tv_share_more.setOnClickListener(this);

        tv_top_share_code.setText(shareCode);
    }

    private void follow() {
        tv_top_share_follow.setVisibility(View.GONE);
        ll_top_share_nofollow.setVisibility(View.VISIBLE);
    }

    private void noFollow() {
        tv_top_share_follow.setVisibility(View.VISIBLE);
        ll_top_share_nofollow.setVisibility(View.GONE);
    }
}
