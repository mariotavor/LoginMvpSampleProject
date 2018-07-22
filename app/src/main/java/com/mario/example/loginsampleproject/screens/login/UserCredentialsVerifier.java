package com.mario.example.loginsampleproject.screens.login;

import android.text.TextUtils;

class UserCredentialsVerifier {
    UserCredentialsVerifier() {
    }

    boolean verifyEmail(String email) {
        return (!TextUtils.isEmpty(email) && email.length() > 4 && email.contains("@"));

    }

    boolean verifyPassword(String password) {
        return (!TextUtils.isEmpty(password) && password.length() > 4);
    }
}