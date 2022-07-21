package com.app.tracker.apis.controllers;

import com.app.tracker.apis.models.account.Account;
import com.app.tracker.apis.models.account.AccountRequest;
import com.app.tracker.apis.models.account.AccountResponse;
import com.app.tracker.apis.services.account.AccountService;
import com.app.tracker.apis.services.account.AccountServiceImpl;
import com.app.tracker.core.models.PageRequest;
import com.app.tracker.core.models.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @ResponseBody
    ResponseEntity<PageResponse<AccountResponse>> getAccounts(PageRequest request) {
        Page<Account> page = accountService.list(request.toPageable());

        PageResponse<AccountResponse> pageResponse = new PageResponse<>(
                page.getContent()
                        .stream()
                        .map(AccountResponse::new)
                        .collect(Collectors.toList()), page.getTotalElements(),
                page.getTotalPages());

        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    ResponseEntity<AccountResponse> getAccount(@PathVariable("id") Long accountId) {
        return new ResponseEntity<>(
                new AccountResponse(accountService.getById(accountId)), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    ResponseEntity<AccountResponse> create(@RequestBody AccountRequest accountRequest) {
        return new ResponseEntity<>(
                new AccountResponse(accountService.create(accountRequest)),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseBody
    ResponseEntity<AccountResponse> update(@PathVariable("id") Long accountId, @RequestBody AccountRequest accountRequest) {
        return new ResponseEntity<>(new AccountResponse(
                accountService.updateAccount(accountRequest, accountId)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    ResponseEntity<?> delete(@PathVariable("id") Long accountId) {
        accountService.deleteById(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
