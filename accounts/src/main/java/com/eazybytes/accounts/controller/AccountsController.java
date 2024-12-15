package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.constants.Constants;
import com.eazybytes.accounts.dto.AccountDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;



import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {
    IAccountService accountService;

    @PostMapping("/account")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customer) {
        accountService.createAccount(customer);
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(new ResponseDto(Constants.STATUS_CODE_CREATED, AccountConstants.ACCOUNT_CREATED_MESSAGE));
    }

    @GetMapping("/account")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber){
        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.FOUND).
                body(customerDto);
    }

    @PutMapping("/account")
    public ResponseEntity<ResponseDto> updateAccountDetails(@RequestBody CustomerDto customerDto){
        boolean isUpdated = accountService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
        }
    }
}
