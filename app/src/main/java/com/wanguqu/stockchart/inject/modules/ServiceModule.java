package com.wanguqu.stockchart.inject.modules;

import android.app.Service;

import com.wanguqu.stockchart.inject.others.PerService;
import com.wanguqu.stockchart.service.DownLoadService;

import dagger.Module;
import dagger.Provides;

/**
 * author：ajiang
 * mail：1025065158@qq.com
 * blog：http://blog.csdn.net/qqyanjiang
 */
@Module
public class ServiceModule {
    private DownLoadService m_service;

    public ServiceModule(DownLoadService service) {
        m_service = service;
    }

    @Provides
    @PerService
    public Service provideService() {
        return m_service;
    }
}
