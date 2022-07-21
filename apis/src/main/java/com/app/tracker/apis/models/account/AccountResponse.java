package com.app.tracker.apis.models.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountResponse {
	private Long id;
	private String code;
	private String name;
	private BigDecimal amount;

	public AccountResponse(Account account) {
		this.id = account.getId();
		this.code = account.getCode();
		this.name = account.getName();
		this.amount = account.getAmount();
	}
}
