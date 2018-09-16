package com.lotto.lotto.controller;

import com.lotto.lotto.controller.response.AccountResponse;
import com.lotto.lotto.model.Account;
import com.lotto.lotto.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

//@RunWith(MockitoJUnitRunner.class)
public class AccountControllerUnitTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountController accountController;

    @Before
    public void initial(){
        initMocks(this);
    }

    @Test
    public void getById() {

        //Stub
        Account account = new Account();
        account.setUserName("user");
        account.setPassword("pass");
        account.setSalary(1000);
        given(accountRepository.findById(1))
                .willReturn(Optional.of(account));

        accountController = new AccountController(accountRepository);
        AccountResponse response = accountController.getById(1);
        AccountResponse expected
                = new AccountResponse("user","pass",1000);
        assertEquals(expected, response);

    }
}