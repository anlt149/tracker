package com.app.tracker.apis.repositories;

import com.app.tracker.apis.models.account.Account;
import com.app.tracker.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends BaseRepository<Account> {
	Optional<Account> findAccountByCode(String code);

	Boolean existsAccountByCode(String code);

	long deleteAccountById(Long accountId);
}
