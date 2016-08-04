package com.spring.accounts;

/**
 * Created by David on 2016-07-27.
 */
public class AccountDuplicateException extends RuntimeException{
    private String username;

    public AccountDuplicateException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
