/**
 * 
 */
package com.pramati.sale.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author sudhirk
 *
 */
@Entity
@Table(name = "authorities")
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleid")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "authority")
	private String authority;

	public Authority() {
	}

	public Authority(String username, String authority) {
		this.username = username;
		this.authority = authority;
	}

	public Authority(Long id, String username, String authority) {
		this.id = id;
		this.username = username;
		this.authority = authority;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
