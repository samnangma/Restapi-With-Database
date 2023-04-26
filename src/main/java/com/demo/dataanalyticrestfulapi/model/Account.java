package com.demo.dataanalyticrestfulapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer id;
    private String accountName;
    private String accountNumber;
    private String profile;
    private Integer pin;
    private String password;
    private String phoneNumber;
    private Integer transferLimit;
    private AccountType accountType;
}
