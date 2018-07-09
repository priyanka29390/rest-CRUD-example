package com.example.rest.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import com.example.rest.dao.IdGenerator;
import com.example.rest.model.domain.Person;

@Repository
public class PersonDAOImpl implements PersonDAO{
	
	private IdGenerator idGenerator;
	
	private List<Person> persons = Collections.synchronizedList(new ArrayList<>());
	
	@Inject
	public PersonDAOImpl(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

	@Override
	public Person createPerson(Person person) {
		person.setId(idGenerator.getNextId());
		persons.add(person);
		return person;
	}

	@Override
	public List<Person> fetchAllPersons() {
		return persons;
	}

	@Override
	public Boolean deletePerson(Long id) {
		return persons.removeIf(p -> p.getId().equals(id));
	}

	@Override
	public Optional<Person> findPersonById(Long id) {
		return persons.stream().filter(p -> p.getId().equals(id)).findFirst();
	}

	@Override
	public Person updatePerson(Person personOld, Person personNew) {
		personOld.setFirstName(personNew.getFirstName());
		personOld.setLastName(personNew.getLastName());
		return personOld;
	}
		
	public void clear() {
		persons.clear();
	}
	

}
