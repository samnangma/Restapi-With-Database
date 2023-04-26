package com.demo.dataanalyticrestfulapi.controller;

import com.demo.dataanalyticrestfulapi.mapper.AutoAccountMapper;
import com.demo.dataanalyticrestfulapi.model.Account;
import com.demo.dataanalyticrestfulapi.model.response.AccountResponse;
import com.demo.dataanalyticrestfulapi.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/account")
public class AccountController {
    // inject Account Service

    final private AccountService accountService;
    final private AutoAccountMapper autoAccountMapper;

    AccountController(AccountService accountService , AutoAccountMapper autoAccountMapper){
        this.accountService = accountService;
        this.autoAccountMapper = autoAccountMapper;
    }

    @GetMapping("/all-accounts")
    public ResponseEntity<?> getAllAccounts(){
        try{
            List<Account> allAccount = accountService.getAllAccounts();
            List<AccountResponse> accountResponses = autoAccountMapper.mapToAccountResponse(allAccount);
            HashMap<String, Object > response = new HashMap<>();
            response.put("payload", accountResponses);
            response.put("message","Successfully retrieve all accounts info!");
            response.put("Status", HttpStatus.OK);


            return ResponseEntity.ok().body(response);

        } catch (Exception exception){
            System.out.println("Something Wrong: "+exception.getMessage());
            return ResponseEntity.ok().body("Failed to retrevied the accounts");
        }
    }
}
