package com.mario.example.loginsampleproject.user.db;

import com.mario.example.loginsampleproject.database.MainGreenDaoDbManager;
import com.mario.example.loginsampleproject.user.UserModelGateway;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mario on 19/07/18.
 */

@Module
public class UserModule {
    @Provides
    public UserModelGateway provideUserModelGateway(MainGreenDaoDbManager dbManager) {
        return new UserModelImpl(dbManager);
    }
}