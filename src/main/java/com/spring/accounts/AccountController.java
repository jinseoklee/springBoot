package com.spring.accounts;

import com.spring.commons.ErrorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by David on 2016-07-26.
 */
//@ResponseBody
//@Controller
@RestController
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


    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public ResponseEntity createAccount(@RequestBody @Valid AccountDto.Create dto,
                                        BindingResult result) {
        if (result.hasErrors()) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("잘못된 요청입니다.");
            errorResponse.setCode("bad.request");
//            errorResponse.setErrors(
//                    modelMapper.map(result.getFieldErrors(),ErrorResponse.FieldError.class)
//            );
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

            Account newAccount = service.createAccout(dto);

        return new ResponseEntity<>(modelMapper.map(newAccount, AccountDto.Response.class), HttpStatus.CREATED);
    }

    @ExceptionHandler(UserDuplicateException.class)
    public ResponseEntity userDuplicateException(UserDuplicateException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage( "["+e.getUsername()+"] 중복된 username 입니다.");
        errorResponse.setCode("duplicated.username.exception");

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
