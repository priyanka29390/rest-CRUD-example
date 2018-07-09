package com.example.rest.service;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.example.rest.dao.PersonDAO;
import com.example.rest.model.domain.Person;

@Service
public class PersonService {
	
	private PersonDAO personDAO;
	
	@Inject
	public PersonService(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public Person createPerson(Person person) {
		return personDAO.createPerson(person);
	}

	public List<Person> fetchAllPersons() {		
		return personDAO.fetchAllPersons();
	}

	public Boolean deletePerson(Long id) {
		return personDAO.deletePerson(id);
	}

	public Optional<Person> findPersonById(Long id) {
		return personDAO.findPersonById(id);
	}

	public Person updatePerson(Person personOld, Person personNew) {
		return personDAO.updatePerson(personOld, personNew);
	}
	
}
