package org.sk.demo.crud.app.controller;

import org.sk.demo.crud.app.db.InMemoryDatabase;
import org.sk.demo.crud.app.model.Person;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;

/**
 * 
 * @author satish sharma
 * 
 */
public class PersonController {

	InMemoryDatabase dbHelper = new InMemoryDatabase();
	
	public void create(Context ctx) {
		Person person = ctx.bodyAsClass(Person.class);
    	boolean isAdded = dbHelper.addPerson(person);
    	if(isAdded) {
    		ctx.status(201);
    		ctx.json(person);
    	}else {
    		ctx.status(500);
    	}
		
	}

	public void delete(Context ctx) {
		int personId = Integer.parseInt(ctx.pathParam("person-id"));
    	boolean isDeleted=	dbHelper.deletePerson(personId);
    	if(isDeleted) {
    		ctx.status(201);
    		ctx.json("Deleted Person data for Id "+personId);
    	}else {
    		ctx.status(500);
    	}
		
	}

	public void getAll(Context ctx) {
		ctx.json(dbHelper.getAllPersons());
	}

	public void getOne(Context ctx) {
		int personId = Integer.parseInt(ctx.pathParam("person-id"));
    	ctx.json(dbHelper.getPersonById(personId));
	}

	public void update(Context ctx) {
		Person person = ctx.bodyAsClass(Person.class);
    	boolean isUpdated = dbHelper.updatePerson(person);
    	if(isUpdated) {
    		ctx.status(201);
    		ctx.json(person);
    	}else {
    		ctx.status(500);
    	}
		
	}

}
