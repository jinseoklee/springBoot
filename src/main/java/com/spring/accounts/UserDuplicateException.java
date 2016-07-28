package com.spring.accounts;

/**
 * Created by David on 2016-07-27.
 */
public class UserDuplicateException extends RuntimeException{
    private String username;

    public UserDuplicateException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
