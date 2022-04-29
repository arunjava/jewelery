package com.nura.jewelery.entity.address;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;

import com.nura.jewelery.utils.Constants;

import lombok.Data;

@Data
@Entity
@Table(name = "ms_country", schema = Constants.SCHEMA_JEWEL)
public class Country implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3187962338208447856L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id")
	private long id;
	@Column(name = "country_name")
	private String countryName;
	@Column(name = "country_code" , length = 3)
	private String countryCode;

}
