package com.lotto.lotto.controller;

import com.lotto.lotto.controller.response.AccountResponse;
import com.lotto.lotto.exception.MyAccountNotFoundException;
import com.lotto.lotto.model.Account;
import com.lotto.lotto.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

//@RunWith(MockitoJUnitRunner.class)
public class AccountControllerErrorUnitTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountController accountController;

    @Before
    public void initial(){
        initMocks(this);
    }

    @Test(expected = MyAccountNotFoundException.class)
    public void getByIdWithException() {

        given(accountRepository.findById(2))
                .willReturn(Optional.empty());
//                .willThrow(new MyAccountNotFoundException(("Fail na"));

        accountController = new AccountController(accountRepository);
        AccountResponse response = accountController.getById(2);

    }
}