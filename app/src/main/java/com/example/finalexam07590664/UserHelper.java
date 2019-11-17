package com.example.finalexam07590664;

import com.example.finalexam07590664.model.User;

public interface UserHelper {


    public static final String DATABASE_NAME = "login";
    public static final int DATABASE_VERSION = 1;

    public long registerUser(User user);

    public User checkUserLogin(User user);

}
