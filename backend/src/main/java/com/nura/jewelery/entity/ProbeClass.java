package com.nura.jewelery.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Embeddable
@Data
public class ProbeClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6404492976673081498L;

	@Column(name = "created_by")
	private String createdBy;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on")
	private Date createdOn;
	@Column(name = "modified_by")
	private String modifiedBy;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_on")
	private Date modifiedOn;

	@PrePersist
	public void onPrepersist() {
		this.createdOn = new Date();
		this.modifiedOn = new Date();
	}

	@PreUpdate
	public void onPreUpdate() {
		this.modifiedOn = new Date();
	}

}