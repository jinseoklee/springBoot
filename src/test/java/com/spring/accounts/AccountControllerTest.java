package com.spring.accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by David on 2016-07-26.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class AccountControllerTest {

    @Autowired
    WebApplicationContext wac;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void createAccount() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();

        AccountDto.Create create = new AccountDto.Create();

        create.setUsername("dsfsrewerse");
        create.setPassword("sfwer234234234");

        ResultActions result =
        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(create))
        );

        result.andDo(print());
        result.andExpect(status().isCreated());
    }

}