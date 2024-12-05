package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

public interface IAccountService {
    public void createAccount(CustomerDto customer);
}
