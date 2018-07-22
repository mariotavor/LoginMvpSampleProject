package com.mario.example.loginsampleproject.screens.login;

import android.text.TextUtils;
import android.util.Log;

import com.mario.example.loginsampleproject.base.BasePresenter;
import com.mario.example.loginsampleproject.rx.schedulers.ISchedulerProvider;
import com.mario.example.loginsampleproject.user.UserLoginInfo;
import com.mario.example.loginsampleproject.user.UserModelGateway;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by mario on 19/07/18.
 */

class LoginPresenterImpl extends BasePresenter<UserModelGateway, LoginActivityMvpContract.View> implements LoginActivityMvpContract.Presenter
{

    private final UserCredentialsVerifier userCredentialsVerifier = new UserCredentialsVerifier();
    private final ISchedulerProvider mObserveOnScheduler;
    private UserLoginInfo userInfoState=new UserLoginInfo();
    private Subscription insertUserDetailsSubscription;

    LoginPresenterImpl(UserModelGateway model, LoginActivityMvpContract.View view, ISchedulerProvider observeOnScheduler) {
        super(model, view);
        mObserveOnScheduler=observeOnScheduler;

    }

    @Override
    public void viewCreated() {

    }

    @Override
    public void viewDestroyed() {
        userInfoState=new UserLoginInfo();
        if(insertUserDetailsSubscription!=null && !insertUserDetailsSubscription.isUnsubscribed())
            insertUserDetailsSubscription.unsubscribe();

    }

    @Override
    public void viewResumed() {

    }

    @Override
    public void viewPaused() {

    }

    @Override
    public void onEmailEntered(String email) {
        userInfoState.setEmail(email);
        if(!TextUtils.isEmpty(email) && !userCredentialsVerifier.verifyEmail(email))
            showEmailErrorMessage();
    }


    private void showEmailErrorMessage() {
        if(viewWeakRef.get()!=null)
            viewWeakRef.get().setEmailErrorMessage(LoginConsts.EMAIL_VALIDATION_ERROR_FORMAT);

    }



    @Override
    public void onPasswordEntered(String password) {
        userInfoState.setPassword(password);
        if(!TextUtils.isEmpty(password) && !userCredentialsVerifier.verifyPassword(password))
            showPasswordError();
    }


    private void showPasswordError() {
        if(viewWeakRef.get()!=null)
            viewWeakRef.get().setPasswordErrorMessage(LoginConsts.PASSWORD_VALIDATION_ERROR_TOO_SHORT);
    }



    @Override
    public void onLoginButtonClicked() {
        boolean successPassword = userCredentialsVerifier.verifyPassword(userInfoState.getPassword());
        if(!successPassword)
            showPasswordError();


        boolean successEmail = userCredentialsVerifier.verifyEmail(userInfoState.getEmail());
        if(!successEmail)
            showEmailErrorMessage();



        if(successEmail && successPassword)
        {
            if(viewWeakRef.get()!=null)
                viewWeakRef.get().disableLoginButton();

            if(insertUserDetailsSubscription!=null && !insertUserDetailsSubscription.isUnsubscribed())
                insertUserDetailsSubscription.unsubscribe();

            insertUserDetailsSubscription = model.insertUserDetails(userInfoState).doOnUnsubscribe(new Action0() {
                @Override
                public void call() {

                    if(viewWeakRef.get()!=null)
                        viewWeakRef.get().enableLoginButton();

                }
            }).observeOn(mObserveOnScheduler.ui()).subscribe(new Action1<Boolean>() {
                @Override
                public void call(Boolean success) {

                    if(success && viewWeakRef.get()!=null)
                        viewWeakRef.get().goToHomeScreen(userInfoState);

                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    Log.e("TAG","exception"+throwable.getCause());
                }
            });


        }
    }
}
