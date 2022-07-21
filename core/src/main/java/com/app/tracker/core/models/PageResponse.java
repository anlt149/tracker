package com.app.tracker.core.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PageResponse<T> {
	private List<T> data;
	private Long total;
	private int totalPage;
}
