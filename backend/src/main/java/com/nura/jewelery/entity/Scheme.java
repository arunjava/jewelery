package com.nura.jewelery.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nura.jewelery.utils.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "ms_scheme", schema = Constants.SCHEMA_JEWEL)
public class Scheme implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2092277740545850303L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "scheme_id")
	private long id;
	@Column(name = "scheme_name")
	private String schemeName;
	@Column(name = "description")
	private String description;
	@Column(name = "is_active")
	private boolean isActive;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date beginDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;

	@JsonIgnore
	@Embedded
	private ProbeClass probe = new ProbeClass();

	@ManyToMany(mappedBy = "schemes")
	@JsonIgnoreProperties("schemes")
	private Set<Customer> customers;

	@PrePersist
	private void prePersist() {
		this.setActive(true);
	}
}
