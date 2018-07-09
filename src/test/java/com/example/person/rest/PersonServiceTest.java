package com.example.person.rest;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.example.rest.dao.PersonDAO;
import com.example.rest.model.domain.Person;
import com.example.rest.service.PersonService;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {
	
	@Mock
	private PersonDAO personDAO;

	@InjectMocks
	private PersonService service;
	
	@Mock
	private Person person;
	
	private Long id = 1L;
	
	@Test
	public void testCreatePerson() {
		service.createPerson(person);
		verify(personDAO).createPerson(person);		
	}
	
	@Test
	public void testFetchAllPersons() {
		service.fetchAllPersons();
		verify(personDAO).fetchAllPersons();		
	}
	
	@Test
	public void testDeletePerson() {
		service.deletePerson(id);
		verify(personDAO).deletePerson(id);		
	}
	
	@Test
	public void testFindPersonById() {
		service.findPersonById(id);
		verify(personDAO).findPersonById(id);		
	}
	
	@Test
	public void testUpdatePerson() {
		service.updatePerson(person, person);
		verify(personDAO).updatePerson(person, person);		
	}	
	
}
