package com.mario.example.loginsampleproject.base;

import java.lang.ref.WeakReference;

/**
 * Created by mario on 19/07/18.
 * BasePresenter that ties the view and model to the presenter
 */

public abstract class BasePresenter<M extends BaseMvpContract.Model, V extends BaseMvpContract.View> implements BaseMvpContract.Presenter
{
    protected final WeakReference<V> viewWeakRef;
    public M model;


    public BasePresenter(M model, V view) {
        this.viewWeakRef = new WeakReference<>(view);
        this.model = model;
    }
}
