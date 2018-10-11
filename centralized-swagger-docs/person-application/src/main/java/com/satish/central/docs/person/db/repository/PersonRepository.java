package com.satish.central.docs.person.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satish.central.docs.person.db.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
