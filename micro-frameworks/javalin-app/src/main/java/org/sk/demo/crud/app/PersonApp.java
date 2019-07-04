package org.sk.demo.crud.app;

import org.sk.demo.crud.app.db.InMemoryDatabase;
import org.sk.demo.crud.app.model.Person;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.core.validation.JavalinValidation;
/**
 * 
 * @author satish sharma
 * 
 * REST interface offering CRUD opeartion on {@link Person} object.
 * 
 * The purpose of this application is to demonstrate a CRUD operation. 
 * So we are not implmenting some of the features that you will consider while writing code for production. 
 * some of which are as follows
 * <ol>
 * 	<li> We are not adding any validation. This can be implemented using Validator</li>
 * <li>No adding error handlers. Which can be done using {@link JavalinValidation#validate()}</li>
 * <li> Each of the method could be packaged as separate handler and then can be used as reference</li>
 * <li>Some other points that are supported by Javalin and you may consider</li>
 * <ul>
 * <li> <a href="https://javalin.io/documentation#validation">validation</li>
 * <li><a href= "https://javalin.io/documentation#before-handlers">Before handler</a></li> 
 * <li><a href="https://javalin.io/documentation#after-handlers"> After handler </a></li> 
 * <li><a href="https://javalin.io/documentation#handler-groups">Handler Groups</a></li>
 * <li><a href="https://javalin.io/documentation#server-sent-events"> Server Sent Event</a></li>
 * <li><a href="https://javalin.io/documentation#websockets>Web Sockets</a></li>
 * </ui>
 * </ol>
 *
 */
//@formatter:on 


public class PersonApp {
	 public static void main(String[] args) {
		 InMemoryDatabase dbHelper = new InMemoryDatabase();
		 
	        Javalin app = Javalin.create().start(7000);	//define the port
	        
	        app.get("/persons", ctx -> {
	        	ctx.json(dbHelper.getAllPersons());
	        });
	        
	        app.get("/persons/:person-id", ctx -> {
	        	int personId = Integer.parseInt(ctx.pathParam("person-id"));
	        	ctx.json(dbHelper.getPersonById(personId));
	        });
	        
	        app.post("/persons", ctx -> {
	        	Person person = ctx.bodyAsClass(Person.class);
	        	boolean isAdded = dbHelper.addPerson(person);
	        	if(isAdded) {
	        		ctx.status(201);
	        		ctx.json(person);
	        	}else {
	        		ctx.status(500);
	        	}
	        });
	        
	        app.put("/persons", ctx -> {
	        	Person person = ctx.bodyAsClass(Person.class);
	        	boolean isUpdated = dbHelper.updatePerson(person);
	        	if(isUpdated) {
	        		ctx.status(201);
	        		ctx.json(person);
	        	}else {
	        		ctx.status(404);
	        	}
	        });
	        
	        app.delete("/persons/:person-id", ctx ->{
	        	int personId = Integer.parseInt(ctx.pathParam("person-id"));
	        	boolean isDeleted=	dbHelper.deletePerson(personId);
	        	if(isDeleted) {
	        		ctx.status(201);
	        		ctx.json("Deleted Person data for Id "+personId);
	        	}else {
	        		ctx.status(500);
	        	}
	        });
	    }
}
