package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Account;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with mobile number " + customerDto.getMobileNumber() + " already exists");
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Admin");
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    private Account createNewAccount(Customer customer) {
        Account account = new Account();
        account.setCustomerId(customer.getCustomerId());
        long accountNumber = 1000000000L + new Random().nextInt(900000000);

        account.setAccountNumber(accountNumber);
        account.setAccountType(AccountConstants.ACCOUNT_TYPE_SAVINGS);
        account.setBranchAddress(AccountConstants.BRANCH_ADDRESS);
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("Admin");
        return account;
    }

}