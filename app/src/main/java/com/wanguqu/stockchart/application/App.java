package com.wanguqu.stockchart.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.wanguqu.BuildConfig;
import com.wanguqu.stockchart.inject.component.AppComponent;
import com.wanguqu.stockchart.inject.component.DaggerAppComponent;
import com.wanguqu.stockchart.inject.modules.AppModule;

import org.greenrobot.eventbus.EventBus;


public class App extends Application {
    private static final int SHOW_TIME_MIN = 1000;
    private static App mApp;
    private static EventBus sBus;
    private AppComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
        initComponent();
        mApp = this;
        sBus = EventBus.getDefault();
    }

    public static App getApp() {
        return mApp;
    }

    private void initComponent() {
        applicationComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        applicationComponent.inject(this);
    }

    public AppComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static EventBus getBus() {
        return sBus;
    }

}
