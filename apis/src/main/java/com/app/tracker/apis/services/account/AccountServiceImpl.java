package com.app.tracker.apis.services.account;

import com.app.tracker.apis.models.account.Account;
import com.app.tracker.apis.models.account.AccountRequest;
import com.app.tracker.apis.repositories.AccountRepository;
import com.app.tracker.core.exceptions.BaseError;
import com.app.tracker.core.exceptions.BaseException;
import com.app.tracker.core.service.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AccountServiceImpl extends BaseServiceImpl<AccountRepository, Account> implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Account create(AccountRequest accountRequest) {
        if (accountRepository.existsAccountByCode(accountRequest.getCode())) {
            throw new BaseException(new BaseError(HttpStatus.BAD_REQUEST,
                                                  "Existing account with code: " + accountRequest.getCode()));
        }

        return accountRepository.save(Account.builder()
                                              .amount(accountRequest.getAmount())
                                              .code(accountRequest.getCode())
                                              .name(accountRequest.getName())
                                              .build());
    }

    @Override
    @Transactional
    public Account updateAccount(AccountRequest accountRequest, Long accountId) {
        if (accountRequest.getCode() == null
                || accountRequest.getName() == null
                || accountRequest.getAmount() == null) {
            throw new BaseException(new BaseError(HttpStatus.BAD_REQUEST,
                                                  "Request can't have null value"));
        }
        // Can't find account id
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new BaseException(new BaseError(HttpStatus.BAD_REQUEST,
                                                                   "Can't find account with id: " + accountId)));

        BeanUtils.copyProperties(accountRequest, account);

        return accountRepository.save(account);
    }

}
