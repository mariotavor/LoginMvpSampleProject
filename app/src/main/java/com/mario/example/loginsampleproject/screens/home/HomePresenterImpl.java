package com.mario.example.loginsampleproject.screens.home;

import android.widget.Toast;

import com.mario.example.loginsampleproject.LoginApplication;
import com.mario.example.loginsampleproject.base.BasePresenter;
import com.mario.example.loginsampleproject.rx.schedulers.ISchedulerProvider;
import com.mario.example.loginsampleproject.user.UserLoginInfo;
import com.mario.example.loginsampleproject.user.UserModelGateway;

import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mario on 19/07/18.
 * Presenter to the HOME screen
 */


class HomePresenterImpl extends BasePresenter<UserModelGateway, HomeActivityMvpContract.View> implements HomeActivityMvpContract.Presenter {


    private final Scheduler myObserverScheduler;
    private UserLoginInfo mSavedUserInfoFromModel;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    public HomePresenterImpl(UserModelGateway model, HomeActivityMvpContract.View view, ISchedulerProvider observeOnScheduler) {
        super(model, view);
        myObserverScheduler =observeOnScheduler.ui();
    }


    @Override
    public void loginInfoReceived(final String email) {

        final Subscription userInfoSubscription = model.getUserInfo(email).observeOn(myObserverScheduler).subscribe(new Action1<UserLoginInfo>() {
            @Override
            public void call(UserLoginInfo userLoginInfo) {
                if (userLoginInfo == null && viewWeakRef.get()!=null) {
                    viewWeakRef.get().goBack();
                }

                mSavedUserInfoFromModel = userLoginInfo;
                if( viewWeakRef.get()!=null)
                    viewWeakRef.get().showUserDetails(mSavedUserInfoFromModel.getEmail());

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if(viewWeakRef.get()!=null)
                    viewWeakRef.get().goBack();
            }
        });
        compositeSubscription.add(userInfoSubscription);


    }



    @Override
    public void viewCreated() {

    }

    @Override
    public void viewDestroyed() {
        compositeSubscription.clear();
    }

    @Override
    public void viewResumed() {

    }

    @Override
    public void viewPaused() {

    }

    @Override
    public void logoutButtonClicked() {
        if(mSavedUserInfoFromModel !=null)
        {
            Subscription deleteSubscription = model.deleteUserDetails(mSavedUserInfoFromModel.getEmail()).observeOn(myObserverScheduler).subscribe(new Action1<Boolean>() {
                @Override
                public void call(Boolean aBoolean) {
                    Toast.makeText(LoginApplication.getInstance(),"delete action result was: "+aBoolean,Toast.LENGTH_SHORT).show();

                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {

                }
            });
            compositeSubscription.add(deleteSubscription);
        }
        if(viewWeakRef.get()!=null)
            viewWeakRef.get().goBack();
    }
}
