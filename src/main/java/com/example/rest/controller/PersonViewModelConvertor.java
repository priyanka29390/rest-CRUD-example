package com.example.rest.controller;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.rest.model.domain.Person;
import com.example.rest.model.view.PersonViewModel;

@Component
public class PersonViewModelConvertor implements Converter<Person, PersonViewModel>{

	@Override
	public PersonViewModel convert(Person source) {
		PersonViewModel viewModel = new PersonViewModel();
		viewModel.setId(source.getId());
		viewModel.setFirstName(source.getFirstName());
		viewModel.setLastName(source.getLastName());
		return viewModel;
	}

}
