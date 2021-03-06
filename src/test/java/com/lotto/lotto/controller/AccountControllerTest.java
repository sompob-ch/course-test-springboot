package com.lotto.lotto.controller;

import com.lotto.lotto.controller.response.AccountResponse;
import com.lotto.lotto.model.Account;
import com.lotto.lotto.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.springframework.boot.test.context.SpringBootTest.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void getById() {

        Account account = new Account();
        account.setId(1);
        account.setUserName("fakeuser");
        account.setPassword("fakepaass");
        accountRepository.save(account);

        ResponseEntity<AccountResponse> result =
            testRestTemplate
                    .getForEntity("/account/1"
                            , AccountResponse.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        AccountResponse expected = new AccountResponse("fakeuser","fakepaass",0);
        assertEquals(expected, result.getBody());

    }
}