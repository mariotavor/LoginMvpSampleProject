package com.mario.example.loginsampleproject.user;

import com.mario.example.loginsampleproject.base.BaseMvpContract;

/**
 * Created by mario on 19/07/18.
 * gateway to User model handler
 */

public interface UserModelGateway extends BaseMvpContract.Model {
    rx.Observable<Boolean> insertUserDetails(UserLoginInfo info);
    rx.Observable<Boolean> deleteUserDetails(String email);
    rx.Observable<UserLoginInfo> getUserInfo(String email);
}
