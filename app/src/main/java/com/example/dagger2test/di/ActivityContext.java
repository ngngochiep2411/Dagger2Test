package com.example.dagger2test.di;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;
import javax.inject.Scope;
import javax.inject.Singleton;

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface ActivityContext {
}
