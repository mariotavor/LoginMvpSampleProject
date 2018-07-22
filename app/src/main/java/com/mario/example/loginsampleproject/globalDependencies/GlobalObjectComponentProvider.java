package com.mario.example.loginsampleproject.globalDependencies;

import com.mario.example.loginsampleproject.LoginApplication;
import com.mario.example.loginsampleproject.database.MainGreenDaoDbManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mario on 22/07/18.
 * This is the component in charge of all the global APP SCOPE components
 */

@Singleton
@Component(modules = {GlobalObjectModuleProvider.class})
public interface GlobalObjectComponentProvider
{

 LoginApplication provideLoginApplication();

 MainGreenDaoDbManager providerMainDbManager();


 void inject(LoginApplication loginApplication);
}

