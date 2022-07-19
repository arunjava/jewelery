package com.nura.jewelery.entity.scheme;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nura.jewelery.entity.Customer;
import com.nura.jewelery.entity.uom.UOM;
import com.nura.jewelery.utils.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ms_cust_scheme", schema = Constants.SCHEMA_JEWEL)
public class CustomerScheme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@OneToOne
	@JoinColumn(name = "cust_id")
	private Customer customer;

	@OneToOne
	@JoinColumn(name = "scheme_id")
	private Scheme scheme;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_dt")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "end_dt")
	private Date endDate;

	@OneToOne
	@JoinColumn(name = "uom_id")
	private UOM uom;

	@Column(name = "uom_val")
	private String uomVal;

	@Column(name = "exchange_val")
	private String exchangeVal;

	@OneToOne
	@JoinColumn(name = "exchange_uom_id")
	private UOM exchangeUOM;

	@Column(name = "is_active")
	private boolean isActive;

	@PrePersist
	public void prePersist() {
		isActive = true;
	}

}
