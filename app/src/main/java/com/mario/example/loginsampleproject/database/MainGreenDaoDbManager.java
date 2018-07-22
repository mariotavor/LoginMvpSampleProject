package com.mario.example.loginsampleproject.database;

import com.mario.example.loginsampleproject.LoginApplication;
import com.mario.example.loginsampleproject.user.db.DaoMaster;
import com.mario.example.loginsampleproject.user.db.DaoSession;

/**
 * Created by mario on 21/07/18.
 * Class that manages the GreenDao framework
 */


public class MainGreenDaoDbManager {

    public static final String LOGIN_USER_DEMO_DB = "login_user_demo.db";
    private final LoginApplication mContext;
    private DaoSession mDaoSession;

    public MainGreenDaoDbManager(LoginApplication app) {

        mContext = app;
    }

    public void initDb()
    {
        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(mContext, LOGIN_USER_DEMO_DB).getWritableDb()).newSession();
    }

    public DaoSession getmDaoSession() {
        return mDaoSession;
    }
}
