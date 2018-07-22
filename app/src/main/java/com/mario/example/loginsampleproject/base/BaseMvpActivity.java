package com.mario.example.loginsampleproject.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mario.example.loginsampleproject.LoginApplication;
import com.mario.example.loginsampleproject.globalDependencies.GlobalObjectComponentProvider;

import javax.inject.Inject;

/**
 * Created by mario on 19/07/18.
 * Activity that follows the {@link BaseMvpContract.View} contract
 *
 */

@SuppressLint("Registered")
public abstract class BaseMvpActivity<P extends BaseMvpContract.Presenter> extends AppCompatActivity implements BaseMvpContract.View {

    @Inject
    public P presenter;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.viewDestroyed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectAllDaggerDependencies(LoginApplication.getInstance().getmGlobalObjectComponent());
        presenter.viewCreated();
    }

    /**
     * Created by mario on 21/07/18.
     * all injection including to {@link BaseMvpActivity#presenter} should be done here
     * We could have tried a solution using this base class but it may be overkill for this scenario
     */
    public abstract void injectAllDaggerDependencies(GlobalObjectComponentProvider globalObjectComponentProvider);

}


