package com.mario.example.loginsampleproject.user.db;


import android.util.Log;

import com.mario.example.loginsampleproject.database.MainGreenDaoDbManager;
import com.mario.example.loginsampleproject.user.UserLoginInfo;
import com.mario.example.loginsampleproject.user.UserModelGateway;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by mario on 19/07/18.
 */

class UserModelImpl implements UserModelGateway {

    private final DaoSession dbSession;
    private final Scheduler myObjectScheduler;
    private static ExecutorService myStaticClassExecutor = Executors.newSingleThreadExecutor();


    public UserModelImpl(MainGreenDaoDbManager mainDbManager) {
        myObjectScheduler = Schedulers.from(myStaticClassExecutor);
        dbSession = mainDbManager.getmDaoSession();
    }

    @Override
    public rx.Observable<Boolean> insertUserDetails(final UserLoginInfo info) {
        return rx.Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {

                Log.e("TAG","executing in thread "+Thread.currentThread().getId());
                UserGreenDaoDao userDao = dbSession.getUserGreenDaoDao();
                UserGreenDao user = getUserFromDbByEmail(info.getEmail());

                if(user!=null)
                {
                    user.setPassword(info.getPassword());
                    userDao.update(user);
                }else
                {
                    UserGreenDao newUser = new UserGreenDao();
                    newUser.setEmail(info.getEmail());
                    newUser.setPassword(info.getPassword());
                    userDao.insert(newUser);

                }

                return true;
            }
        }).subscribeOn(myObjectScheduler);
    }

    @Override
    public rx.Observable<Boolean> deleteUserDetails(final String email) {
        return rx.Observable.fromCallable(new Callable<Boolean>()
        {
            @Override
            public Boolean call() throws Exception
            {
                Log.e("TAG", "executing in thread " + Thread.currentThread().getId());
                final UserGreenDaoDao userDao = dbSession.getUserGreenDaoDao();
                UserGreenDao user = getUserFromDbByEmail(email);
                if(user!=null)
                {
                    userDao.delete(user);
                    return true;
                }

                return false;
            }
        }).subscribeOn(myObjectScheduler);
    }

    @Override
    public rx.Observable<UserLoginInfo> getUserInfo(final String email) {
        return rx.Observable.fromCallable(new Callable<UserLoginInfo>() {
            @Override
            public UserLoginInfo call() throws Exception {
                UserGreenDao user = getUserFromDbByEmail(email);

                if(user==null)
                    return null;

                UserLoginInfo userLoginInfo =new UserLoginInfo();
                userLoginInfo.setEmail(user.getEmail());
                userLoginInfo.setPassword(user.getPassword());
                return userLoginInfo;
            }
        }).subscribeOn(myObjectScheduler);
    }




    private UserGreenDao getUserFromDbByEmail(String email) {
        final UserGreenDaoDao userDao = dbSession.getUserGreenDaoDao();
        return userDao.queryBuilder()
                .where(UserGreenDaoDao.Properties.Email.eq(email))
                .unique();
    }


}
