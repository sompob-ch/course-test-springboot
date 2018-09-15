package com.lotto.lotto.controller;

import com.lotto.lotto.controller.response.AccountResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/account/{id}")
    public AccountResponse getById(
            @PathVariable int id){
        return new AccountResponse(
                "user","pass",1000);
    }

}
