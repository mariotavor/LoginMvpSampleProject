package com.mario.example.loginsampleproject.screens.home;

import com.mario.example.loginsampleproject.globalDependencies.GlobalObjectComponentProvider;
import com.mario.example.loginsampleproject.user.db.UserModule;

import dagger.Component;

/**
 * Created by mario on 19/07/18.
 */


@Component(modules = {HomeModule.class, UserModule.class}, dependencies = {GlobalObjectComponentProvider.class})
@HomeModule.HomeScope
public interface HomeComponent {

    void inject(HomeActivity activity);

}

