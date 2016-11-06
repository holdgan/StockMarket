package com.wanguqu.leftrightscrool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.wanguqu.SearchActivity;
import com.wanguqu.SettingActivity;
import com.wanguqu.R;
import com.wanguqu.ShareDetailsActivity;
import com.wanguqu.leftrightscrool.model.RightModel;
import com.wanguqu.leftrightscrool.tool.ObservableScrollView;
import com.wanguqu.leftrightscrool.tool.ScrollViewListener;
import com.wanguqu.leftrightscrool.tool.UtilTools;
import com.wanguqu.leftrightscrool.view.SyncHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

public class TableActivity extends Activity implements
//        AbsListView.OnScrollListener,
        ScrollViewListener, View.OnClickListener {
    private LinearLayout leftContainerView;
    private ListView leftListView;
    //    private List<String> leftlList;
    private LinearLayout rightContainerView;
    private ListView rightListView;
    private List<RightModel> models;
    private SyncHorizontalScrollView titleHorsv;
    private SyncHorizontalScrollView contentHorsv;
    private ObservableScrollView ui_1;
    private int scrollHeight = 0;
    private int eachscreenHeight = 0;
    private RightAdapter mRightAdapter = null;
    private LeftAdapter mLeftAdapter = null;
    private int page = -1;
    private int newpage = 0;
    private int screenHeight = 0;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tab_view);
        mContext = TableActivity.this;

        headBar();

        leftContainerView = (LinearLayout) findViewById(R.id.left_container);
        leftListView = (ListView) findViewById(R.id.left_container_listview);
        rightContainerView = (LinearLayout) findViewById(R.id.right_container);
        rightListView = (ListView) findViewById(R.id.right_container_listview);
        titleHorsv = (SyncHorizontalScrollView) findViewById(R.id.title_horsv);
        contentHorsv = (SyncHorizontalScrollView) findViewById(R.id.content_horsv);
        titleHorsv.setScrollView(contentHorsv);
        contentHorsv.setScrollView(titleHorsv);

        ui_1 = (ObservableScrollView) findViewById(R.id.ui_1);
        ui_1.setScrollViewListener(this);
        WindowManager wm = this.getWindowManager();
        screenHeight = wm.getDefaultDisplay().getHeight();

        models = new ArrayList<RightModel>();
        initRightData();

        leftContainerView.setBackgroundColor(Color.YELLOW);
//        leftlList = new ArrayList<String>();
        mLeftAdapter = new LeftAdapter();
        leftListView.setAdapter(mLeftAdapter);
        UtilTools.setListViewHeightBasedOnChildren(leftListView);


        rightContainerView.setBackgroundColor(Color.GRAY);
//        models = new ArrayList<RightModel>();
//        initRightData();
        mRightAdapter = new RightAdapter();
        rightListView.setAdapter(mRightAdapter);
        UtilTools.setListViewHeightBasedOnChildren(rightListView);


        rightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position + "right");

                Intent intent = new Intent();
                intent.setClass(mContext, ShareDetailsActivity.class);
                intent.putExtra("stock", models.get(position).getText1());
                startActivity(intent);
            }
        });
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position + "left");

                Intent intent = new Intent();
                intent.setClass(mContext, ShareDetailsActivity.class);
                intent.putExtra("stock", models.get(position).getText1());
                startActivity(intent);
            }
        });

        scrollHeight();

    }

    private void initRightData() {
        for (int i = 0; i < 5; i++) {
            models.add(new RightModel("复星国际", "600237", "1.25", "5.12%", "11.8", "23232", "313", "3.3%", "123%"));
            models.add(new RightModel("腾讯控股", "132323", "545", "7.12%", "4.8", "4343", "313", "3.3%", "123%"));
            models.add(new RightModel("中国电影", "209090", "67", "9.12%", "54.8", "545", "313", "3.3%", "123%"));
            models.add(new RightModel("东方财富", "100323", "2", "0.12%", "90", "65", "313", "3.3%", "123%"));
            models.add(new RightModel("海通证券", "765656", "434", "223.12%", "323", "767", "313", "3.3%", "123%"));
        }
    }


    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y + screenHeight > eachscreenHeight * newpage) {
            if (newpage > page) {
//                Toast.makeText(TableActivity.this, "加载第"+newpage+"页" , Toast.LENGTH_SHORT).show();
                initRightData();

                mRightAdapter.notifyDataSetChanged();
                mLeftAdapter.notifyDataSetChanged();

                UtilTools.setListViewHeightBasedOnChildren(rightListView);
                UtilTools.setListViewHeightBasedOnChildren(leftListView);

                scrollHeight();
            }
        }
    }

    private void scrollHeight() {
        ui_1.measure(0, 0);
        int newscrollHeight = ui_1.getMeasuredHeight();
        if (newpage == 0) {
            eachscreenHeight = newscrollHeight;
        }
//        System.out.println(ui_1.getMeasuredHeight() + "   " + screenHeight);
        if (newscrollHeight > scrollHeight) {
            page++;
            newpage = page + 1;
            scrollHeight = newscrollHeight;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ll_top_left:
                finish();
                break;
            case R.id.ll_top_right_next:
                intent.setClass(mContext, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_top_right:
                intent.setClass(mContext, SearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    class LeftAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (models != null) {
                return models.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (models != null) {
                return models.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LeftViewHold hold;
            if (convertView == null) {
                hold = new TableActivity.LeftViewHold();
                convertView = View.inflate(TableActivity.this, R.layout.layout_left_item, null);
                hold.textView = (TextView) convertView.findViewById(R.id.left_container_textview0);
                hold.textView1 = (TextView) convertView.findViewById(R.id.left_container_textview1);
                convertView.setTag(hold);
            } else {
                hold = (TableActivity.LeftViewHold) convertView.getTag();
            }
            hold.textView.setText(models.get(position).getText0());
            hold.textView1.setText(models.get(position).getText1());
            return convertView;
        }
    }

    static class LeftViewHold {
        TextView textView;
        TextView textView1;
    }

    class RightAdapter extends BaseAdapter {
//        private Context context;
//        List<RightModel> list;
//
//        public RightAdapter(Context context, List<RightModel> models) {
//            super();
//            this.context = context;
//            this.list = models;
//        }

        @Override
        public int getCount() {
            if (models != null) {
                return models.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (models != null) {
                return models.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TableActivity.RightViewHold viewHold;
            if (convertView == null) {
                viewHold = new TableActivity.RightViewHold();
                convertView = View.inflate(TableActivity.this, R.layout.layout_right_item, null);
                viewHold.textView0 = (TextView) convertView.findViewById(R.id.right_item_textview0);
                viewHold.textView1 = (TextView) convertView.findViewById(R.id.right_item_textview1);
                viewHold.textView2 = (TextView) convertView.findViewById(R.id.right_item_textview2);
                viewHold.textView3 = (TextView) convertView.findViewById(R.id.right_item_textview3);
                viewHold.textView4 = (TextView) convertView.findViewById(R.id.right_item_textview4);
                viewHold.textView5 = (TextView) convertView.findViewById(R.id.right_item_textview5);
                viewHold.textView6 = (TextView) convertView.findViewById(R.id.right_item_textview6);
                convertView.setTag(viewHold);
            } else {
                viewHold = (TableActivity.RightViewHold) convertView.getTag();
            }
            viewHold.textView0.setText(models.get(position).getText2());
            viewHold.textView1.setText(models.get(position).getText3());
            viewHold.textView2.setText(models.get(position).getText4());
            viewHold.textView3.setText(models.get(position).getText5());
            viewHold.textView4.setText(models.get(position).getText6());
            viewHold.textView5.setText(models.get(position).getText7());
            viewHold.textView6.setText(models.get(position).getText8());
            return convertView;
        }
    }

    static class RightViewHold {
        TextView textView0, textView1, textView2, textView3, textView4, textView5, textView6;
    }

    private void headBar() {
        LinearLayout ll_top_left = (LinearLayout) findViewById(R.id.ll_top_left);
        LinearLayout ll_top_right = (LinearLayout) findViewById(R.id.ll_top_right);
        LinearLayout ll_top_right_next = (LinearLayout) findViewById(R.id.ll_top_right_next);
        TextView tv_top = (TextView) findViewById(R.id.tv_top);
//        ImageView im_top_left = (ImageView) findViewById(R.id.im_top_left);
        ImageView im_top_right = (ImageView) findViewById(R.id.im_top_right);


        ll_top_left.setOnClickListener(this);
        ll_top_right.setOnClickListener(this);
        ll_top_right_next.setOnClickListener(this);

        tv_top.setText(getIntent().getStringExtra("category"));
        if (getIntent().getStringExtra("category").equals(getResources().getString(R.string.market_optional))) {
            ll_top_right_next.setVisibility(View.VISIBLE);
        }
        im_top_right.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
    }

}