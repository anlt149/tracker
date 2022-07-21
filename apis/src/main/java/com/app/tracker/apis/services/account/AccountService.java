package com.app.tracker.apis.services.account;

import com.app.tracker.apis.models.account.Account;
import com.app.tracker.apis.models.account.AccountRequest;
import com.app.tracker.core.service.BaseService;

public interface AccountService extends BaseService<Account> {

    Account create(AccountRequest accountRequest);

    Account updateAccount(AccountRequest accountRequest, Long accountId);

}
