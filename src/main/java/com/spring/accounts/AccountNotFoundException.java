package com.spring.accounts;

/**
 * Created by David on 2016-08-02.
 */
public class AccountNotFoundException extends RuntimeException {

    public  Long id;

    public AccountNotFoundException() {
    }

    public AccountNotFoundException(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
