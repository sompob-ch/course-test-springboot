package com.lotto.lotto.controller;

import com.lotto.lotto.controller.response.AccountResponse;
import com.lotto.lotto.exception.MyAccountNotFoundException;
import com.lotto.lotto.model.Account;
import com.lotto.lotto.repository.AccountRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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

    @Test
    public void getByIdWithExceptionWithRule() {

        given(accountRepository.findById(2))
                .willReturn(Optional.empty());
//                .willThrow(new MyAccountNotFoundException(("Fail na"));

        accountController = new AccountController(accountRepository);

        thrown.expect(MyAccountNotFoundException.class);
        thrown.expectMessage("Account id=[2] not found");
        AccountResponse response = accountController.getById(2);

    }
}