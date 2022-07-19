package com.nura.jewelery.entity.scheme;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nura.jewelery.entity.ProbeClass;
import com.nura.jewelery.entity.offers.Offer;
import com.nura.jewelery.entity.uom.UOM;
import com.nura.jewelery.utils.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ms_scheme", schema = Constants.SCHEMA_JEWEL)
@Inheritance(strategy = InheritanceType.JOINED)
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

	@ManyToOne
	@JoinColumn(name = "uom_id")
	private UOM uom;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "mp_scheme_offers", schema = Constants.SCHEMA_JEWEL, joinColumns = {
			@JoinColumn(name = "scheme_id", referencedColumnName = "scheme_id") }, inverseJoinColumns = {
					@JoinColumn(name = "offer_id", referencedColumnName = "offer_id") })
	private List<Offer> offers;

	@Column(name = "uom_val")
	private int duration;

	@PrePersist
	private void prePersist() {
		this.setActive(true);
	}
}
