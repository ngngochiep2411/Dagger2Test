package com.example.dagger2test.di.module;

import android.app.Activity;

import com.example.dagger2test.MyApplication;
import com.example.dagger2test.data.model.DataManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MyApplication application);

    DataManager getDataManager();
}
