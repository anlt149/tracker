package com.app.tracker.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
	ADMIN("admin"),
	USER("user");

	private final String code;

	public static Role fromCode(String code) {
		for (Role value : Role.values()) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}

		// No match found.
		throw new IllegalArgumentException();
	}
}
