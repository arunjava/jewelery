package com.nura.jewelery.entity.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nura.jewelery.entity.ProbeClass;
import com.nura.jewelery.utils.Constants;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_role", schema = Constants.SCHEMA_TEXTILES)
@Getter
@Setter
@ToString
public class UserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5154759053757386899L;

	@Id
	@GeneratedValue
	@Column(name = "role_id_n")
	private long roleId;
	@Column(name = "role_v")
	private String role;
	@Column(name = "desc_v")
	private String desc;
	@Column(name = "hierarchy_n")
	private int hierarchy;

	@Embedded
	private ProbeClass probeClass;

//	@ManyToOne
//	private User user;
}
