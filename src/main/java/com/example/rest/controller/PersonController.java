package com.example.rest.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.rest.model.domain.Person;
import com.example.rest.model.view.PersonViewModel;
import com.example.rest.service.PersonService;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(value = "/person", produces = "application/json")
public class PersonController {
		
	private PersonViewModelConvertor personViewModelConverter;
	
	private PersonService personService;
		
	@Inject
	public PersonController(PersonViewModelConvertor personViewModelConverter, PersonService personService) {
		this.personViewModelConverter = personViewModelConverter;
		this.personService = personService;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<PersonViewModel> createPerson(@RequestBody Person person){
		Person personCreated = personService.createPerson(person);
		return new ResponseEntity<>(personViewModelConverter.convert(personCreated),HttpStatus.CREATED);		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PersonViewModel>> fetchAllPersons(){
		List<Person> persons = personService.fetchAllPersons();
		List<PersonViewModel> personsViewModel = persons.stream().map(p -> personViewModelConverter.convert(p)).collect(Collectors.toList());
		return new ResponseEntity<>(personsViewModel ,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}" ,method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePerson(@PathVariable("id")Long id) {
		Boolean isDeleted = personService.deletePerson(id);
		if(isDeleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<PersonViewModel> updatePerson(@RequestBody Person person, @PathVariable("id")Long id) {
		Optional<Person> personFound = personService.findPersonById(id);
		if(personFound.isPresent()) {
			Person personUpdated = personService.updatePerson(personFound.get(),person);
			return new ResponseEntity<>(personViewModelConverter.convert(personUpdated),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PersonViewModel> findPersonById(@PathVariable("id")Long id){
	   Optional<Person> personFound = personService.findPersonById(id);
  	   if(personFound.isPresent()) {
 			return new ResponseEntity<>(personViewModelConverter.convert(personFound.get()),HttpStatus.OK);
 	    }else {
 			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
 		}
	}
	
	
	
}
