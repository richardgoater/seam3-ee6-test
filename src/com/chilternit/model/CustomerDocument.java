package com.chilternit.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "customers")
public class CustomerDocument {

	public CustomerDocument() {}
	
	public CustomerDocument(List<Customer> customers) {
		this.customers = customers;
	}
	
	@XmlElement(name = "customer")
	public List<Customer> customers;
	
	public List<Customer> asList() {
		return customers;
	}
	
}
