package com.mario.example.loginsampleproject.base;

/**
 * Created by mario on 18/07/18.
 */

public interface BaseMvpContract {

    interface View
    {

    }

    interface Presenter
    {

        void viewCreated();
        void viewDestroyed();
        void viewResumed();
        void viewPaused();
    }

    interface Model
    {

    }


}
