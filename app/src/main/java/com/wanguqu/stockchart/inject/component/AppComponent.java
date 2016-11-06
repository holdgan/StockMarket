package com.wanguqu.stockchart.inject.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.wanguqu.stockchart.api.ClientApi;
import com.wanguqu.stockchart.api.DownLoadApi;
import com.wanguqu.stockchart.application.App;
import com.wanguqu.stockchart.inject.modules.AppModule;
import com.wanguqu.stockchart.inject.modules.ClientApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ClientApiModule.class})
public interface AppComponent {
    Context context();

    ClientApi clientApi();

    DownLoadApi downLoadApi();
    SharedPreferences sharedPreferences();

    void inject(App application);
}
