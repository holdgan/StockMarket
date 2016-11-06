package com.wanguqu.stockchart.inject.component;

import android.app.Activity;

import com.wanguqu.stockchart.inject.modules.FragmentModule;
import com.wanguqu.stockchart.inject.others.PerFragment;

import dagger.Component;

@PerFragment
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {

    Activity getActivity();

  //  void inject(BaseFragment mBaseFragment);


}
