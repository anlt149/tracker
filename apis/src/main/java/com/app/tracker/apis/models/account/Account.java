package com.app.tracker.apis.models.account;

import com.app.tracker.core.models.entities.BaseEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@Table(name = "tbl_account")
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(nullable = false, unique = true)
	private String code;

	@Column(nullable = false)
	private String name;

	@ColumnDefault(value = "0")
	private BigDecimal amount;
}
