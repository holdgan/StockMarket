//package com.wanguqu;
//
//import android.annotation.TargetApi;
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.content.pm.ActivityInfo;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.IBinder;
//import android.text.TextUtils;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.google.gson.Gson;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
//import com.nulll.todaysafrica.bean.MessageListResult;
//import com.nulll.todaysafrica.bean.UpdataResult;
//import com.nulll.todaysafrica.utils.DialogUtils;
//import com.nulll.todaysafrica.utils.HttpUtil;
//import com.nulll.todaysafrica.utils.OkHttpClientManager;
//import com.nulll.todaysafrica.utils.SystemStatusManager;
//import com.nulll.todaysafrica.utils.ToastUtils;
//import com.nulll.todaysafrica.widget.DownloadService;
//import com.nulll.todaysafrica.widget.MyAdapter;
//import com.nulll.todaysafrica.widget.MyApp;
//import com.nulll.todaysafrica.widget.PullToRefreshBase;
//import com.nulll.todaysafrica.widget.PullToRefreshListView;
//import com.nulll.todaysafrica.widget.ViewHolder;
//import com.purplebrain.adbuddiz.sdk.AdBuddiz;
//import com.squareup.okhttp.Request;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AllListActivity extends Activity {
//    private Context mContext;
//    private PullToRefreshListView lv_message_list;
//    private MyAdapter<MessageListResult.messagelist> adapter = null;
//    private List<MessageListResult.messagelist> mList = new ArrayList<MessageListResult.messagelist>();
//    private ProgressBar progressBar;
//    private TextView top_text;
//    private static final int UP = 0;
//    private static final int DOWN = 1;
//    private MessageListResult mlr;
//    private int pageNo = 0;
//    private int PageNum = 10;
//    private long mExitTime;
//    private ImageView btn_top_right;
//    private LinearLayout ll_top_right;
//    private Dialog dialog;
//    private DownloadService.DownloadBinder binder;
//    private boolean isBinded;
//    private MyApp app;
//    private boolean isDestroy = true;
//    private UpdataResult ur;
//    private Gson gson = new Gson();
//    private String updateurl = "";
//    private String updatemessage = "";
//    private ProgressBar progress;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_message_list);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        mContext = MessageListActivity.this;
//        initHead();
//
//        ImageLoader.getInstance().init(
//                ImageLoaderConfiguration.createDefault(mContext));
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//            SystemStatusManager tintManager = new SystemStatusManager(this);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(R.color.colorTop);
//        }
//
//        app = (MyApp) getApplication();
//        lv_message_list = (PullToRefreshListView) findViewById(R.id.lv_message_list);
//        progressBar = (ProgressBar) findViewById(R.id.progressBar);
//
//        updata();
//
//        lv_message_list.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                if ((System.currentTimeMillis() - mExitTime) > 1000) {
//                    Intent intent = new Intent(mContext, BannerDesActivity.class);
//                    intent.putExtra("LinkUrl", mList.get(position).url);
//                    startActivity(intent);
//                    mExitTime = System.currentTimeMillis();
//                } else {
//
//                }
//            }
//        });
//        lv_message_list
//                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
//
//                    @Override
//                    public void onPullDownToRefresh(
//                            PullToRefreshBase<ListView> refreshView) {
//                        pageNo = 0;
//
//                        getHttpData(UP);
//                    }
//
//                    @Override
//                    public void onPullUpToRefresh(
//                            PullToRefreshBase<ListView> refreshView) {
//                        pageNo = pageNo + PageNum;
//
//                        getHttpData(DOWN);
//                    }
//                });
//        getHttpData(UP);
//
//
//
//    }
//
//    private void initHead() {
//        top_text = (TextView) findViewById(R.id.top_text);
//        btn_top_right = (ImageView) findViewById(R.id.btn_top_right);
//        ll_top_right = (LinearLayout) findViewById(R.id.ll_top_right);
//
//        btn_top_right.setImageResource(R.drawable.icon_about);
//        top_text.setText(getResources().getString(R.string.app_name));
//        ll_top_right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, AboutActivity.class);
//                startActivity(intent);
//
//            }
//        });
//    }
//
//
//    public void getHttpData(final int state) {
//        String url = HttpUtil.BASE_URL + "/data/" + pageNo + "/" + PageNum;
//        OkHttpClientManager.getAsyn(url,
//                new OkHttpClientManager.ResultCallback<String>() {
//
//                    @Override
//                    public void onError(Request request, Exception e) {
//                        // TODO Auto-generated method stub
//
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//                        if (!TextUtils.isEmpty(response)) {
//                            progressBar.setVisibility(View.GONE);
//                            mlr = gson.fromJson(response,
//                                    MessageListResult.class);
//                            if (mlr.success.equals("true")) {
//                                if (mlr.data != null
//                                        && mlr.data.size() > 0) {
//                                    final List<MessageListResult.messagelist> list = mlr.data;
//                                    mList.addAll(list);
//                                    if (adapter == null) {
//                                        adapter = new MyAdapter<MessageListResult.messagelist>(
//                                                mContext, list,
//                                                R.layout.item_message_list) {
//
//                                            @Override
//                                            public void convert(
//                                                    ViewHolder helper,
//                                                    int position,
//                                                    MessageListResult.messagelist item) {
//                                                if (mList.get(position).image.equals("")) {
//                                                    helper.setVisibility(
//                                                            R.id.content_im,
//                                                            View.GONE);
//                                                } else {
//                                                    helper.setImageBitmap(
//                                                            R.id.content_im,
//                                                            mList.get(position).image);
//                                                }
//
//                                                helper.setText(
//                                                        R.id.title,
//                                                        mList.get(position).title);
//                                                helper.setText(
//                                                        R.id.author,
//                                                        mList.get(position).from);
//                                                helper.setText(
//                                                        R.id.date,
//                                                        mList.get(position).date.substring(0, 10));
//
//                                            }
//
//                                        };
//                                        lv_message_list.setAdapter(adapter);
//
//                                    } else {
//                                        if (state == UP) {
//                                            mList.clear();
//                                            adapter.upData(list);
//                                            mList.addAll(list);
//
//                                        } else if (state == DOWN) {
//                                            adapter.AddData(list);
//                                        }
//                                        lv_message_list.onRefreshComplete();
//                                        adapter.notifyDataSetChanged();
//                                    }
//                                } else {
//                                    lv_message_list.onRefreshComplete();
//                                }
//                            } else {
//                                lv_message_list.onRefreshComplete();
//                            }
//                        }
//                    }
//                });
//
//    }
//
//
//    @TargetApi(19)
//    private void setTranslucentStatus(boolean on) {
//        Window win = getWindow();
//        WindowManager.LayoutParams winParams = win.getAttributes();
//        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//        if (on) {
//            winParams.flags |= bits;
//        } else {
//            winParams.flags &= ~bits;
//        }
//        win.setAttributes(winParams);
//    }
//
//
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if ((System.currentTimeMillis() - mExitTime) > 2000) {
//                ToastUtils.makeText(this, "Press another exit", ToastUtils.LENGTH_SHORT)
//                        .show();
//                mExitTime = System.currentTimeMillis();
//            } else {
//                finish();
//            }
//            return true;
//        }
//        if (keyCode == KeyEvent.KEYCODE_MENU) {
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//}