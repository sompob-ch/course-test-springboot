package com.lotto.lotto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotto.lotto.controller.response.AccountResponse;
import com.lotto.lotto.model.Account;
import com.lotto.lotto.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountRepository accountRepository;

    private JacksonTester<AccountResponse> jsonTester;

    @Test
    public void getById() throws Exception {

        JacksonTester.initFields(this, new ObjectMapper());

        //Stub
        Account account = new Account();
        account.setUserName("user");
        account.setPassword("pass");
        account.setSalary(1000);
        given(accountRepository.findById(1))
                .willReturn(Optional.of(account));

        MockHttpServletResponse response
                = mockMvc.perform(
                get("/account/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals("For message error EX. Status should 200", HttpStatus.OK.value(), response.getStatus());

        AccountResponse expected
                = new AccountResponse("user","pass",1000);
        assertEquals("XXX", jsonTester.write(expected).getJson(),
                response.getContentAsString());
    }
}