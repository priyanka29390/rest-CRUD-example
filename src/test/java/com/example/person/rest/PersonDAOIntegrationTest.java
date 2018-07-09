package com.example.person.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.boot.test.IntegrationTest;
import com.example.rest.dao.IdGenerator;
import com.example.rest.dao.PersonDAOImpl;
import com.example.rest.model.domain.Person;

@Category(IntegrationTest.class)
public class PersonDAOIntegrationTest {
	
	private PersonDAOImpl personDAOImpl;
	
	private IdGenerator idGenerator;
	
	private Person person;
	
	private Person personNew;
	
	private Long invalid_id = 1234L;
	
	@Before
	public void setUp() {
		idGenerator= new IdGenerator();
		personDAOImpl = new PersonDAOImpl(idGenerator);	
		personDAOImpl.clear();
	}
	
	public Person injectPerson() {
		person = new Person();
		person.setFirstName("testFirstName");
		person.setLastName("testLastName");
		return personDAOImpl.createPerson(person);
	}
	
	public Person createTobeUpdatedPerson() {
		personNew = new Person();
		personNew.setFirstName("newFirstName");
		personNew.setLastName("newtLastName");
		return personNew;
	}
	
	@Test
	public void testCreatePerson() throws Exception{
		Person createdPerson = injectPerson();
		assertNotNull(createdPerson);	
		assertEquals(person.getFirstName(), createdPerson.getFirstName());
		assertEquals(person.getLastName(), createdPerson.getLastName());
	}
	
	@Test	
	public void testFetchAllPersonsWhenTwoPersonsInjected() {
		injectPerson();
		injectPerson();
		List<Person> persons = personDAOImpl.fetchAllPersons();
		assertEquals(2, persons.size());
	}
	
	@Test
	public void testFindPersonByValidId() {
		injectPerson();
		assertTrue(personDAOImpl.findPersonById(1L).isPresent());
	}
	
	@Test
	public void testFindPersonByInvalidId() {
		injectPerson();
		assertFalse(personDAOImpl.findPersonById(invalid_id).isPresent());
	}
	
	@Test
	public void testDeletePerson() {
		injectPerson();
		assertTrue(personDAOImpl.deletePerson(1L));
	}
	
	@Test
	public void testUpdatePerson() {
		injectPerson();
		createTobeUpdatedPerson();
		Person updatedPerson = personDAOImpl.updatePerson(person, personNew);
		assertEquals(personNew.getFirstName(), updatedPerson.getFirstName());
		assertEquals(personNew.getLastName(), updatedPerson.getLastName());		
	}
	
	

}
