package com.spring.accounts;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by David on 2016-07-26.
 */
@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Account createAccout(AccountDto.Create dto) {
//        Account account = new Account();
//        account.setUsername(dto.getUsername());
//        account.setPassword(dto.getPassowrd());

        Account account = modelMapper.map(dto, Account.class);

        Date now = new Date();

        account.setJoined(now);
        account.setUpdate(now);
        return repository.save(account);
    }
}
