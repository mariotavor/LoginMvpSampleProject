package com.mario.example.loginsampleproject;

import android.app.Application;

import com.mario.example.loginsampleproject.database.MainGreenDaoDbManager;
import com.mario.example.loginsampleproject.globalDependencies.DaggerGlobalObjectComponentProvider;
import com.mario.example.loginsampleproject.globalDependencies.GlobalObjectComponentProvider;
import com.mario.example.loginsampleproject.globalDependencies.GlobalObjectModuleProvider;

import javax.inject.Inject;

/**
 * Created by mario on 18/07/18.
 */



public class LoginApplication extends Application {

    private GlobalObjectComponentProvider mGlobalObjectComponent;
    private static LoginApplication instance;

    @Inject
    MainGreenDaoDbManager mainDb;

    @Override
    public void onCreate() {
        super.onCreate();
        mGlobalObjectComponent = DaggerGlobalObjectComponentProvider.builder().globalObjectModuleProvider(new GlobalObjectModuleProvider(this)).build();
        mGlobalObjectComponent.inject(this);
        instance = this;
        mainDb.initDb();

    }


    public static LoginApplication getInstance() {
        return instance;
    }

    public GlobalObjectComponentProvider getmGlobalObjectComponent() {
        return mGlobalObjectComponent;
    }
}
