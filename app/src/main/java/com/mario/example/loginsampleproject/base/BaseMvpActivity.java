package com.mario.example.loginsampleproject.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mario.example.loginsampleproject.LoginApplication;
import com.mario.example.loginsampleproject.globalDependencies.GlobalObjectComponentProvider;

/**
 * Created by mario on 19/07/18.
 * Activity that follows the {@link BaseMvpContract.View} contract
 *
 */

@SuppressLint("Registered")
public abstract class BaseMvpActivity<P extends BaseMvpContract.Presenter> extends AppCompatActivity implements BaseMvpContract.View {


    public P presenter;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.viewDestroyed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=getPresenter(LoginApplication.getInstance().getmGlobalObjectComponent());
        if(presenter==null)
            throw  new IllegalArgumentException("presenter injection for class "+this.getClass().getName());

        presenter.viewCreated();
    }

    /**
     * the child is asked to create the dependencies and provide the presenter
     * @param globalObjectComponentProvider global component that provides all global references
     * @return P which extends {@link BaseMvpContract.Presenter} of the child
     *
     * @throws IllegalArgumentException if null is returned
     */
    public abstract P getPresenter(GlobalObjectComponentProvider globalObjectComponentProvider) throws IllegalArgumentException;

}


