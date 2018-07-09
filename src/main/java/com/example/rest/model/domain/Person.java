package com.example.rest.model.domain;

import com.example.rest.model.domain.Identifiable;

public class Person implements Identifiable{
	
	private Long id;
	
	private String firstName;
	
	private String lastName;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
