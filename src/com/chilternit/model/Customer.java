package com.chilternit.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@XmlType(name = "customer", 
propOrder = { "id", "name", "address"})
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue @XmlElement
	Long id;
	
	@Size(min = nameMinlength, max = nameMaxlength, 
		message = "Must be between " + nameMinlength + " and " + nameMaxlength + " characters long")
	@NotNull
	@XmlElement
	@FormParam("name")
	private String name;
	
	@Size(max = addressMaxLength, message = "Must be less than " + addressMaxLength + " characters long")
	@XmlElement
	@FormParam("address")
	private String address;
	
	public Customer() {
		
	}
	
	public Customer(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return name + (address.length() > 0 ? " - " + address : "");
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	static final int nameMinlength = 3;
	static final int nameMaxlength = 15;	
	static final int addressMaxLength = 30;
	
}
