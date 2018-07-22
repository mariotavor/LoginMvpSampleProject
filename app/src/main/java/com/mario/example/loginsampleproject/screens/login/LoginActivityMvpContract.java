package com.mario.example.loginsampleproject.screens.login;

import com.mario.example.loginsampleproject.base.BaseMvpContract;
import com.mario.example.loginsampleproject.user.UserLoginInfo;

/**
 * Created by mario on 18/07/18.
 */

public interface LoginActivityMvpContract {

    interface View extends BaseMvpContract.View
    {

        void setEmailErrorMessage(String msg);
        void setPasswordErrorMessage(String msg);

        void goToHomeScreen(UserLoginInfo info);

        void disableLoginButton();
        void enableLoginButton();
    }

    interface Presenter extends BaseMvpContract.Presenter
    {
        void onEmailEntered(String email);
        void onPasswordEntered(String password);
        void onLoginButtonClicked();
    }

}
