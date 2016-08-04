package com.spring.accounts;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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


        // controller ExceptionsHandler 에서 처리
        String username = dto.getUsername();

        if ( repository.findByUsername(username) != null ){
            log.error("UserDuplicateException 에러");
            throw new AccountDuplicateException(username);
        }

        Date now = new Date();

        account.setJoined(now);
        account.setUpdate(now);
        return repository.save(account);
    }

    public Account updateAccount(Account account, AccountDto.Update updateDto) {

        account.setUsername(updateDto.getUsername());
        account.setPassword(updateDto.getPassword());

        return repository.save(account);
    }

    public Account getAccount(Long id) {

        Account account = repository.findOne(id);
        if(account == null){
            throw new AccountNotFoundException(id);
        }

        return null;
    }
}
