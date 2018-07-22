package com.mario.example.loginsampleproject.screens.login;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.mario.example.loginsampleproject.LoginApplication;
import com.mario.example.loginsampleproject.R;
import com.mario.example.loginsampleproject.base.BaseMvpActivity;
import com.mario.example.loginsampleproject.globalDependencies.GlobalObjectComponentProvider;
import com.mario.example.loginsampleproject.screens.home.HomeActivity;
import com.mario.example.loginsampleproject.user.UserLoginInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseMvpActivity<LoginActivityMvpContract.Presenter> implements LoginActivityMvpContract.View {


    // UI references.
    @BindView(R.id.email)
    EditText mEmailView;
    @BindView(R.id.password)
    EditText mPasswordView;

    private Unbinder unbinder;

    @BindView(R.id.email_sign_in_button)
    View mEmailSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        unbinder =  ButterKnife.bind(this);

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }


    @OnTextChanged(value = R.id.email, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterEmailInput(Editable editable) {
        presenter.onEmailEntered(editable.toString());
    }

    @OnTextChanged(value = R.id.password, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterPasswordInput(Editable editable) {
        presenter.onPasswordEntered(editable.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();

    }


    private void attemptLogin() {
        presenter.onLoginButtonClicked();
    }



    @Override
    public void setEmailErrorMessage(String msg) {
        mEmailView.setError(msg);
    }

    @Override
    public void setPasswordErrorMessage(String msg) {
        mPasswordView.setError(msg);
    }


    @Override
    public void goToHomeScreen(UserLoginInfo info) {
        startActivity(HomeActivity.getIntentToOpenMe(this,info));
    }

    @Override
    public void disableLoginButton() {
        mEmailSignInButton.setEnabled(false);
    }

    @Override
    public void enableLoginButton() {
        mEmailSignInButton.setEnabled(true);
    }


    @Override
    public void injectAllDaggerDependencies(GlobalObjectComponentProvider globalObjectComponentProvider) {
        LoginComponent loginComponent = DaggerLoginComponent.builder().globalObjectComponentProvider(globalObjectComponentProvider).loginModule(new LoginModule(this)).build();
        loginComponent.inject(this);

    }
}

