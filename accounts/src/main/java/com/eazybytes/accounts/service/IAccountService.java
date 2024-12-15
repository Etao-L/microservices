package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Customer;

public interface IAccountService {
    public void createAccount(CustomerDto customerDto);

    public CustomerDto fetchAccount(String mobileNumber);

    public boolean updateAccount(CustomerDto customerDto);
}
