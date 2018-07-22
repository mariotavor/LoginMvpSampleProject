package com.mario.example.loginsampleproject.screens.home;

import com.mario.example.loginsampleproject.rx.schedulers.ImmediateSchedulerProvider;
import com.mario.example.loginsampleproject.user.UserLoginInfo;
import com.mario.example.loginsampleproject.user.UserModelGateway;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import rx.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by mario on 22/07/18.
 */
public class HomePresenterImplTest {

    private static String VALID_EMAIL = "test@mail.com";
    private static String VALID_PASSORD = "IamNotTheDude12#";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    HomeActivityMvpContract.View view;

    @Mock
    UserModelGateway model;


    HomePresenterImpl presenter;


    @Before
    public void setUp() throws Exception {

        presenter=new HomePresenterImpl(model,view,new ImmediateSchedulerProvider());
    }


    @Test
    public void initLoginInfo() throws Exception {
        setMockForValidUserReponse();
        presenter.loginInfoReceived(VALID_EMAIL);
        verify(model,times(1)).getUserInfo(VALID_EMAIL);
        verify(view,times(1)).showUserDetails((VALID_EMAIL));
    }

    @Test
    public void logoutButtonClickedHappyPath() throws Exception {
        setMockForValidUserReponse();
        presenter.loginInfoReceived(VALID_EMAIL);
        when(model.deleteUserDetails(VALID_EMAIL)).thenReturn(rx.Observable.just(true).subscribeOn(Schedulers.immediate()));
        presenter.logoutButtonClicked();

        verify(model,times(1)).deleteUserDetails(VALID_EMAIL);
        verify(view,times(1)).goBack();
    }

    @Test
    public void logoutButtonClickedNothingToDelete() throws Exception {
        setMockForValidUserReponse();
        presenter.logoutButtonClicked();
        verify(model,never()).deleteUserDetails(VALID_EMAIL);
        verify(view,times(1)).goBack();
    }


    private void setMockForValidUserReponse() {
        UserLoginInfo userLogin = new UserLoginInfo();
        userLogin.setEmail(VALID_EMAIL);
        userLogin.setPassword("");
        when(model.getUserInfo(VALID_EMAIL)).thenReturn(rx.Observable.just(userLogin).subscribeOn(Schedulers.immediate()));
    }

}