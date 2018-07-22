package com.mario.example.loginsampleproject.screens.login;

import com.mario.example.loginsampleproject.globalDependencies.GlobalObjectComponentProvider;
import com.mario.example.loginsampleproject.user.db.UserModule;

import dagger.Component;

/**
 * Created by mario on 19/07/18.
 */


@Component(modules = {LoginModule.class, UserModule.class},dependencies = {GlobalObjectComponentProvider.class})
@LoginModule.LoginScope
public interface LoginComponent {

    void inject(LoginActivity activity);

}