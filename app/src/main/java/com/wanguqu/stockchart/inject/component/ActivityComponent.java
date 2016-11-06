package com.wanguqu.stockchart.inject.component;

import android.app.Activity;

import com.wanguqu.stockchart.BaseActivity;
import com.wanguqu.stockchart.inject.modules.ActivityModule;
import com.wanguqu.stockchart.inject.others.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivityContext();

    void inject(BaseActivity mBaseActivity);



}
