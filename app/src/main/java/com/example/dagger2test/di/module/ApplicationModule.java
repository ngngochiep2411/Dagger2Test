package com.example.dagger2test.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dagger2test.MyApplication;
import com.example.dagger2test.di.ApplicationContext;
import com.example.dagger2test.di.DatabaseInfo;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    MyApplication application;

    public ApplicationModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    public Context provideContext() {
        return application;
    }

    @Provides
    @DatabaseInfo
    public String provideDBName() {
        return "DaggerExample-DB";
    }

    @Provides
    @DatabaseInfo
    public Integer provideDBVersion() {
        return 1;
    }

    @Provides
    public SharedPreferences provideSharedPreference() {
        return application.
                getSharedPreferences("DaggerExample-SharedPrefs", Context.MODE_PRIVATE);
    }

}
