package com.primaryDI.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.primaryDI.Beans.SuperClass;
import com.primaryDI.repository.SuperClassRepository;

@RestController
@RequestMapping(path = "/superclass")
public class SuperClassController {

	@Autowired
	private SuperClassRepository superClassRepository;

	@RequestMapping(path = "/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createSuperClassResource(@Valid @RequestBody SuperClass superClass) {

		SuperClass superClassFromDB = superClassRepository.save(superClass);
		return new ResponseEntity<SuperClass>(superClassFromDB, HttpStatus.CREATED);
	}
}
