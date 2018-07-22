package com.mario.example.loginsampleproject.screens.login;

import com.mario.example.loginsampleproject.rx.schedulers.ISchedulerProvider;
import com.mario.example.loginsampleproject.rx.schedulers.ImmediateSchedulerProvider;
import com.mario.example.loginsampleproject.user.UserLoginInfo;
import com.mario.example.loginsampleproject.user.UserModelGateway;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import rx.internal.schedulers.ImmediateScheduler;
import rx.schedulers.Schedulers;

import static com.mario.example.loginsampleproject.screens.login.LoginConsts.EMAIL_VALIDATION_ERROR_FORMAT;
import static com.mario.example.loginsampleproject.screens.login.LoginConsts.PASSWORD_VALIDATION_ERROR_TOO_SHORT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by mario on 19/07/18.
 */
public class LoginPresenterImplTest {


    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    LoginActivityMvpContract.View view;

    @Mock
    UserModelGateway model;


    LoginPresenterImpl loginPresenter;

    private static String VALID_EMAIL = "test@mail.com";
    private static String VALID_PASSORD = "IamNotTheDude12#";
    private static String NONE_VALID_PASSORD = "Q#";
    private static String NONE_VALID_EMAIL = "i dont have an email !!!!";


    @Before
    public void setUp() throws Exception {
        loginPresenter = new LoginPresenterImpl(model,view, new ImmediateSchedulerProvider());
        loginPresenter.viewCreated();
    }

    @After
    public void tearDown() throws Exception {
        //should reset all fields
        verifyNoMoreInteractions(view);
        loginPresenter.viewDestroyed();
    }


    @Test
    public void onEmailEnteredError() throws Exception {
        loginPresenter.onEmailEntered("");
        loginPresenter.onEmailEntered(NONE_VALID_EMAIL);
        verify(view,times(2)).setEmailErrorMessage(EMAIL_VALIDATION_ERROR_FORMAT);
    }


    @Test
    public void onPasswordEnteredError() throws Exception {
        loginPresenter.onPasswordEntered(NONE_VALID_PASSORD);
        verify(view).setPasswordErrorMessage(PASSWORD_VALIDATION_ERROR_TOO_SHORT);

    }

    @Test
    public void onPasswordEnteredOk() throws Exception {
        loginPresenter.onEmailEntered(VALID_EMAIL);
        verify(view, never()).setEmailErrorMessage(Mockito.<String>any());

    }

    @Test
    public void onLoginButtonClickedVerificationPasses() throws Exception {

        when(model.insertUserDetails(any(UserLoginInfo.class))).thenReturn(rx.Observable.just(true).subscribeOn(Schedulers.immediate()));
        loginPresenter.onEmailEntered(VALID_EMAIL);
        loginPresenter.onPasswordEntered(VALID_PASSORD);
        loginPresenter.onLoginButtonClicked();

        ArgumentCaptor<UserLoginInfo> argumentCaptor = ArgumentCaptor.forClass(UserLoginInfo.class);

        verify(model).insertUserDetails(argumentCaptor.capture());
        UserLoginInfo capturedArgument = argumentCaptor.<UserLoginInfo> getValue();


        assertThat(capturedArgument.getEmail(),is(VALID_EMAIL));
        assertThat(capturedArgument.getPassword(),is(VALID_PASSORD));

        verify(view).disableLoginButton();
        verify(view).enableLoginButton();
        verify(view).goToHomeScreen(capturedArgument);
    }


    @Test
    public void onLoginButtonClickedVerificationError() throws Exception {
        loginPresenter.onEmailEntered(NONE_VALID_EMAIL);
        loginPresenter.onLoginButtonClicked();

        verify(view).setPasswordErrorMessage(LoginConsts.PASSWORD_VALIDATION_ERROR_TOO_SHORT);
        verify(view,atLeast(1)).setEmailErrorMessage(LoginConsts.EMAIL_VALIDATION_ERROR_FORMAT);


        verify(model,never()).insertUserDetails(any(UserLoginInfo.class));
        verify(view,never()).goToHomeScreen(any(UserLoginInfo.class));
    }

}
