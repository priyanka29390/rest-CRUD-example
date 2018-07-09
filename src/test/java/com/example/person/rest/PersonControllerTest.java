package com.example.person.rest;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.rest.controller.PersonController;
import com.example.rest.controller.PersonViewModelConvertor;
import com.example.rest.model.domain.Person;
import com.example.rest.model.view.PersonViewModel;
import com.example.rest.service.PersonService;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {

	@Mock
	private PersonViewModelConvertor personViewModelConverter;
	
	@Mock
	private PersonService personService;
	
	@InjectMocks
	private PersonController controller;
    
    private Person person;
    
    private PersonViewModel viewModel;
    
    private List<Person> persons = new ArrayList<>();
    
    private Long id = 1L;
    	
	@Test
	public void testCreatePerson() throws Exception {
	   givenAPersonObject();
	   givenAPersonViewModel();
	   whenICallService();
	   whenICallConvertor();
	   whenICheckCreatePerson();
	}
	
	@Test
	public void testFetchAllPersons(){
		givenAPersonObject();
		givenPersonsList();	
		whenIcallServiceToGetPersons();
		whenICallConvertor();
		whenICheckFetchAllPersons();
	}
	
	@Test
	public void testDeletePersonWhenPersonIsPresent() {
		when(personService.deletePerson(id)).thenReturn(true);
		ResponseEntity<Void> responseEntity = controller.deletePerson(id);
		assertThat(responseEntity.getStatusCode(),is(HttpStatus.NO_CONTENT));		
	}
	
	@Test
	public void testDeletePersonWhenPersonIsNotPresent() {
		when(personService.deletePerson(id)).thenReturn(false);
		ResponseEntity<Void> responseEntity = controller.deletePerson(id);
		assertThat(responseEntity.getStatusCode(),is(HttpStatus.NOT_FOUND));		
	}
	
	@Test
	public void testFindPersonWhenSucessful() {
		givenAPersonObject();
	    givenAPersonViewModel();
		whenICallConvertor();
		when(personService.findPersonById(id)).thenReturn(Optional.of(person));
		ResponseEntity<PersonViewModel> responseEntity= controller.findPersonById(id);
		assertThat(responseEntity.getStatusCode(),is(HttpStatus.OK));	
	}
	
	@Test
	public void testFindPersonWhenUnsuccessful() {
		givenAPersonObject();
	    givenAPersonViewModel();
		whenICallConvertor();
		when(personService.findPersonById(id)).thenReturn(Optional.empty());
		ResponseEntity<PersonViewModel> responseEntity= controller.findPersonById(id);
		assertThat(responseEntity.getStatusCode(),is(HttpStatus.NOT_FOUND));		
	}
	
	@Test
	public void testUpdatePersonWhenSucessful() {
		givenAPersonObject();
	    givenAPersonViewModel();
		whenICallConvertor();
		when(personService.findPersonById(id)).thenReturn(Optional.of(person));
		when(personService.updatePerson(person, person)).thenReturn(person);
		ResponseEntity<PersonViewModel> responseEntity= controller.updatePerson(person, id);
		assertThat(responseEntity.getStatusCode(),is(HttpStatus.OK));	
	}
	
	@Test
	public void testUpdatePersonWhenUnsuccessful() {
		givenAPersonObject();
	    givenAPersonViewModel();
		whenICallConvertor();
		when(personService.findPersonById(id)).thenReturn(Optional.empty());
		ResponseEntity<PersonViewModel> responseEntity= controller.updatePerson(person, id);
		assertThat(responseEntity.getStatusCode(),is(HttpStatus.NOT_FOUND));		
	}

	private void whenICheckFetchAllPersons() {
		ResponseEntity<List<PersonViewModel>> responseEntity = controller.fetchAllPersons();
		assertEquals(responseEntity.getBody().size(), 1);
		assertThat(responseEntity.getStatusCode(),is(HttpStatus.OK));
	}

	private void whenIcallServiceToGetPersons() {
		when(personService.fetchAllPersons()).thenReturn(persons);		
	}

	private void givenPersonsList() {
		persons.add(person);
	}

	private void whenICallConvertor() {
		when(personViewModelConverter.convert(person)).thenReturn(viewModel);		
	}

	private void givenAPersonViewModel() {
		PersonViewModel viewModel = new PersonViewModel();
		viewModel.setId(1L);
		viewModel.setFirstName("priya");
		viewModel.setLastName("raje");		
	}

	private void whenICheckCreatePerson() {
		ResponseEntity<PersonViewModel> responseEntity = controller.createPerson(person);
		assertThat(responseEntity.getBody(),is(viewModel));
		assertThat(responseEntity.getStatusCode(),is(HttpStatus.CREATED));		
	}

	private void givenAPersonObject() {
		person = new Person();
		person.setId((1L));
		person.setFirstName("priya");
		person.setLastName("raje");	
	}

	private void whenICallService() {
		when(personService.createPerson(person)).thenReturn(person);	
	}
	
	
	
	
}
