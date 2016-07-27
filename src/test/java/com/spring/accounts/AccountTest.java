package com.spring.accounts;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by David on 2016-07-26.
 */
public class AccountTest {

    @Test
    public void getterSetter(){

        Account testAcct = new Account();

        testAcct.setUsername("David Lee");
        testAcct.setPassword("4860ss");
        assertThat(testAcct.getUsername(), is("David Lee"));

    }

    @Test
    public void timestampTest(){
        Account testAcct = new Account();

        Date date = new Date();

        testAcct.setUpdate(date);
        System.out.println(testAcct.getUpdate());

    }

}