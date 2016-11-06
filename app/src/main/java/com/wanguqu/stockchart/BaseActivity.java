package com.wanguqu.stockchart;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.wanguqu.stockchart.api.ClientApi;
import com.wanguqu.stockchart.application.App;
import com.wanguqu.stockchart.inject.component.ActivityComponent;
import com.wanguqu.stockchart.inject.component.DaggerActivityComponent;
import com.wanguqu.stockchart.inject.modules.ActivityModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

public class BaseActivity extends Activity {
    public final String TAG =this.getClass().getSimpleName();
    protected CompositeSubscription mCompositeSubscription;
    protected Activity activity;
    protected Toast mToast = null;
    protected ActivityComponent activityComponent;
    @Inject
    public ClientApi clientApi;
    @Inject
    public SharedPreferences sharedPreferences;
    /*@Inject
    Activity activity;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        mCompositeSubscription = new CompositeSubscription();
        activityComponent = DaggerActivityComponent.builder()
                .appComponent(((App) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
        activityComponent.inject(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);

    }

    public void showToast(String content) {
        if (mToast == null) {
            mToast = Toast.makeText(this, content, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(content);
        }
        mToast.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(BaseActivity.this);
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
