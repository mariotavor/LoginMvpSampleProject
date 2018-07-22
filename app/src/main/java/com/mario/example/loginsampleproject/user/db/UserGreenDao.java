package com.mario.example.loginsampleproject.user.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by mario on 21/07/18.
 * Entity that is for the usage of green dao and therefor SHOULD NOT be accessed from outside
 */


@Entity
 class UserGreenDao {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;




    @Generated(hash = 1045293572)
    public UserGreenDao(Long id, @NotNull String email, @NotNull String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    @Generated(hash = 1822745106)
    public UserGreenDao() {
    }


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

