package com.nura.jewelery.entity.profit;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nura.jewelery.utils.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "tx_profit", schema = Constants.SCHEMA_JEWEL)
public class Profit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -216159367702156935L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq_id")
	private long txnID;

	@Column(name = "profit_amt")
	private double profitAmt;
	
	
}
