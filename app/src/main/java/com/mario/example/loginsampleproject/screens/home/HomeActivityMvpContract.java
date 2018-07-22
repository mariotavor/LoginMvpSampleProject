package com.mario.example.loginsampleproject.screens.home;

import com.mario.example.loginsampleproject.base.BaseMvpContract;

/**
 * Created by mario on 18/07/18.
 */

public interface HomeActivityMvpContract {
    interface View extends BaseMvpContract.View
    {
        void showUserDetails(String user);
        void goBack();
    }

    interface Presenter extends BaseMvpContract.Presenter
    {
        void loginInfoReceived(String email);
        void logoutButtonClicked();
    }

}
