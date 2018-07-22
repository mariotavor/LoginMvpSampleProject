package com.mario.example.loginsampleproject.screens.home;

import com.mario.example.loginsampleproject.rx.schedulers.SchedulerProvider;
import com.mario.example.loginsampleproject.user.UserModelGateway;

import javax.inject.Scope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mario on 19/07/18.
 */

@Module
class HomeModule
{
    @Scope
    public @interface HomeScope {}

    private final HomeActivityMvpContract.View mHomeView;

    public HomeModule(HomeActivityMvpContract.View homeView) {
        this.mHomeView = homeView;
    }

    @Provides
    @HomeScope
    HomeActivityMvpContract.Presenter provideHomePresenter(UserModelGateway model, HomeActivityMvpContract.View view) {
        return new HomePresenterImpl(model,view, SchedulerProvider.getInstance());
    }

    @Provides
    @HomeScope
    HomeActivityMvpContract.View provideHomeView() {
        return mHomeView;
    }




}


