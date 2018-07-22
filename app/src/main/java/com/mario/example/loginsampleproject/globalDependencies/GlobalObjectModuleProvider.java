package com.mario.example.loginsampleproject.globalDependencies;

import com.mario.example.loginsampleproject.LoginApplication;
import com.mario.example.loginsampleproject.database.MainGreenDaoDbManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by mario on 22/07/18.
 * This is the Module in charge of creating all the global APP objects, all singletons
 */

@Module
public class GlobalObjectModuleProvider {


    private LoginApplication mApplication;


    public GlobalObjectModuleProvider(LoginApplication application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    public LoginApplication providesMainApp()
    {
        return mApplication;
    }

    @Singleton
    @Provides
    public MainGreenDaoDbManager providesMainDbManager(LoginApplication app)
    {
        return new MainGreenDaoDbManager(app);
    }


    @Provides
    @Named("android.scheduler")
    public Scheduler providesAndroidScehduler()
    {
        return AndroidSchedulers.mainThread();
    }


}
