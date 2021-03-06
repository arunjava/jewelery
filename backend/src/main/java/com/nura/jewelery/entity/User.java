package com.nura.jewelery.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nura.jewelery.entity.address.Address;
import com.nura.jewelery.utils.Constants;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user", schema = Constants.SCHEMA_JEWEL)
@Data
@NoArgsConstructor
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1728130525730047321L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long userId;
	@Column(name = "first_name_v")
	private String firstName;
	@Column(name = "middle_name_v")
	private String middleName;
	@Column(name = "last_name_v")
	private String lastName;

	@Embedded
	@JsonIgnore
	private ProbeClass probeClass = new ProbeClass();

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "user_addr", schema = Constants.SCHEMA_JEWEL, joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "addr_id"))
//	private Address address;
	
	@JsonIgnore
	@Column(name = "is_active")
	private boolean isActive;
	@Column(name = "user_name")
	private String username;
	@JsonIgnore
	@Column(name = "user_pwd")
	private String password;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users_role_map", schema = Constants.SCHEMA_JEWEL, joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<UserRole> roles = new HashSet<>();
}
