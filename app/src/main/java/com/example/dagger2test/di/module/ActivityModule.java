package com.example.dagger2test.di.module;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    @Provides
    public String provideName(){
        return "hiep123";
    }
}
