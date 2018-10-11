package com.satish.central.docs.person.web.rest.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satish.central.docs.person.db.entities.Person;
import com.satish.central.docs.person.db.repository.PersonRepository;

@RestController
@RequestMapping(value="/person")
public class PersonResource {

	
	//Ideally you shall be using Service classes
	@Autowired
	PersonRepository personRepo;
	
	
	@GetMapping
	public ResponseEntity< List<Person>> getAllPersons(){
		return ResponseEntity.ok(personRepo.findAll());
	}
	
	@GetMapping("/{personid}")
	public ResponseEntity<Person> getPersonById(@PathVariable("personId") int personID){
		Optional<Person> personInDB = personRepo.findById(personID);
		if(personInDB.isPresent()){
			return ResponseEntity.ok(personInDB.get());
		}else{
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Person> storePerson(@RequestBody Person person ){
		Person personInDB = personRepo.save(person);
			return new ResponseEntity<Person>(personInDB,HttpStatus.CREATED);
	}
	
	@PutMapping("/{personId}")
	public ResponseEntity<Person> updatePersonDetails(@PathVariable("personId") int personID,@RequestBody(required=true) Person personDataToBeUpdated ){
		
		 if(personID != personDataToBeUpdated.getId() ){	//Just to make sure we have same person_id in path param and body.
			 return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
		 }
		 
		Optional<Person> personInDB = personRepo.findById(personID);
		if(personInDB.isPresent()){
			Person person = personRepo.saveAndFlush(personDataToBeUpdated);
			return ResponseEntity.ok(person);
		}else{
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
	}
}
