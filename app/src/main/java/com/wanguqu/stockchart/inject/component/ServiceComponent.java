package com.wanguqu.stockchart.inject.component;

import android.app.Service;

import com.wanguqu.stockchart.inject.modules.ServiceModule;
import com.wanguqu.stockchart.inject.others.PerService;
import com.wanguqu.stockchart.service.DownLoadService;

import dagger.Component;

/**
 * author：ajiang
 * mail：1025065158@qq.com
 * blog：http://blog.csdn.net/qqyanjiang
 */
@PerService
@Component(dependencies = AppComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
    Service getService();

    void inject(DownLoadService downLoadService);
}
