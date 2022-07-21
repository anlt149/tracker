package com.app.tracker.core.models.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {
	@CreationTimestamp
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdDate;

	@UpdateTimestamp
	@LastModifiedDate
	@Column
	private LocalDateTime modifiedDate;
}
