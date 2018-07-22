package com.mario.example.loginsampleproject.screens.login;

import android.content.Context;

import com.mario.example.loginsampleproject.rx.schedulers.SchedulerProvider;
import com.mario.example.loginsampleproject.user.UserModelGateway;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by mario on 19/07/18.
 */

@Module
class LoginModule
{
    @Scope
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LoginScope {}

    private final LoginActivityMvpContract.View mLoginView;

    public LoginModule(LoginActivityMvpContract.View loginView) {
        this.mLoginView = loginView;
    }

    @Provides
    @LoginScope
    LoginActivityMvpContract.Presenter provideLoginPresenter(UserModelGateway model,LoginActivityMvpContract.View view) {
        return new LoginPresenterImpl(model,view, SchedulerProvider.getInstance());
    }

    @Provides
    @LoginScope
    LoginActivityMvpContract.View provideLoginView() {
        return mLoginView;
    }




}
