package com.spring.accounts;

import com.spring.commons.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;


/**
 * Created by David on 2016-07-26.
 */
//@ResponseBody
//@Controller
@RestController
@Slf4j
public class AccountController {

    @Autowired
    private AccountService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountRepository repository;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Boot 한글 테스트";
    }


    @RequestMapping(value = "/accounts", method = POST)
    public ResponseEntity createAccount(@RequestBody @Valid AccountDto.Create dto,
                                        BindingResult result) {
        if (result.hasErrors()) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("잘못된 요청입니다.");
            errorResponse.setCode("bad.request");
            log.error("BindingResult Error");
//            errorResponse.setErrors(
//                    modelMapper.map(result.getFieldErrors(),ErrorResponse.FieldError.class)
//            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

            Account newAccount = service.createAccout(dto);

        return new ResponseEntity<>(modelMapper.map(newAccount, AccountDto.Response.class), HttpStatus.CREATED);
    }

    @ExceptionHandler(AccountDuplicateException.class)
    public ResponseEntity accountDuplicateException(AccountDuplicateException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage( "["+e.getUsername()+"] 중복된 username 입니다.");
        errorResponse.setCode("duplicated.username.exception");

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // /accounts?page=0&size=20&sort=username,desc&sort=joined,desc
    @RequestMapping(value = "/accounts", method = GET)
    public ResponseEntity getAccounts(Pageable pageable){

        Page<Account> page= repository.findAll(pageable);

        List<AccountDto.Response> content =
        page.getContent().stream()
                                 .map(account -> modelMapper.map(account, AccountDto.Response.class))
                                 .collect(Collectors.toList());

        PageImpl<AccountDto.Response> result = new PageImpl<>(content, pageable, page.getTotalElements());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/{id}", method = GET)
    public ResponseEntity getAccount(@PathVariable Long id){

        Account account = service.getAccount(id);

        return new ResponseEntity<>(modelMapper.map(account, AccountDto.Response.class), HttpStatus.OK);
    }

    //전체 업데이트 PUT
    //부분 업데이트 PATCH

    @RequestMapping(value = "/accounts/{id}",method = PUT)
    public ResponseEntity updateAccount(@PathVariable Long id, @RequestBody AccountDto.Update updateDto, BindingResult result){

        if(result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Account account = service.getAccount(id);

        Account updateAccount = service.updateAccount(account, updateDto);

        return new ResponseEntity<>(modelMapper.map(updateAccount, AccountDto.Response.class), HttpStatus.OK);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse accountNotFountException(AccountNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage( "["+e.getId()+"] 존재하지 않습니다..");
        errorResponse.setCode("AccountNotFoundException.id.exception");

        return errorResponse;
    }


}
