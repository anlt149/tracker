package com.app.tracker.core.models;

import lombok.*;
import org.springframework.data.domain.Pageable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {
	@Builder.Default
	private int page = 1;

	@Builder.Default
	private int size = 10;

	public Pageable toPageable() {
		return org.springframework.data.domain.PageRequest.of(page - 1, size);
	}
}
