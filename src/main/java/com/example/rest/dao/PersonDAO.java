package com.example.rest.dao;

import java.util.List;
import java.util.Optional;

import com.example.rest.model.domain.Person;

public interface PersonDAO {
	
	Person createPerson(Person person);

	List<Person> fetchAllPersons();

	Boolean deletePerson(Long id);

	Optional<Person> findPersonById(Long id);

	Person updatePerson(Person personOld, Person personNew);
	

}
