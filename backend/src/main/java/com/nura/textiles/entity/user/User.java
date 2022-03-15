package com.nura.textiles.entity.user;

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
import javax.persistence.Table;

import com.nura.textiles.entity.ProbeClass;
import com.nura.textiles.utils.Constants;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user", schema = Constants.SCHEMA_TEXTILES)
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
	@Column(name = "is_active")
	private boolean isActive;
	@Column(name = "user_name")
	private String username;
	@Column(name = "user_pwd")
	private String password;

	@Embedded
	private ProbeClass probeClass;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users_role_map", schema = Constants.SCHEMA_TEXTILES, joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<UserRole> roles = new HashSet<>();

}
