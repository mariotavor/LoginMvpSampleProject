package com.mario.example.loginsampleproject.screens.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.mario.example.loginsampleproject.R;
import com.mario.example.loginsampleproject.base.BaseMvpActivity;
import com.mario.example.loginsampleproject.globalDependencies.GlobalObjectComponentProvider;
import com.mario.example.loginsampleproject.user.UserLoginInfo;

public class HomeActivity extends BaseMvpActivity<HomeActivityMvpContract.Presenter> implements HomeActivityMvpContract.View {

    private static final String KEY_LOGIN_INFO = "KEY_LOGIN_INFO_EMAIL";
    private TextView mHomeScreenText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent myIntent = getIntent();
        String email =  myIntent.getStringExtra(KEY_LOGIN_INFO);
        if(TextUtils.isEmpty(email))
            finish();

        mHomeScreenText=findViewById(R.id.home_screen_user_info_text);


       findViewById(R.id.home_screen_logout_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.logoutButtonClicked();

            }
        });

        presenter.loginInfoReceived(email);
    }



    @Override
    public void showUserDetails(String user) {
        mHomeScreenText.setText(user);
    }


    @Override
    public void goBack() {
        onBackPressed();

    }

    @Override
    public void injectAllDaggerDependencies(GlobalObjectComponentProvider globalObjectComponentProvider) {
        HomeComponent homeComponent = DaggerHomeComponent.builder().globalObjectComponentProvider(globalObjectComponentProvider).homeModule(new HomeModule(this)).build();
        homeComponent.inject(this);
    }

    public static Intent getIntentToOpenMe(Context context, UserLoginInfo info) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra(KEY_LOGIN_INFO,info.getEmail());
       return intent;
    }
}
