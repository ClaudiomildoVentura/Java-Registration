package com.app.crud.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class AddressModels {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column( name ="address_name",  nullable = true)
	private String name;
	
	@Column( name ="address",  nullable = true)
	private String address;

	@ManyToOne
	@JoinColumn(name = "owner")
	private UserModels owner;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserModels getOwner() {
		return owner;
	}

	public void setOwner(UserModels owner) {
		this.owner = owner;
	}
}