/**
 * 
 */
package org.sk.demo.crud.app.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sk.demo.crud.app.model.Person;

/**
 * @author satish sharma
 *
 *         There could be mulitple implementations conidering different ascpects
 *         like performance, but our target is to mimic the operations
 *         in-memory instead of goind to database
 * 
 */
public class InMemoryDatabase  {

	private List<Person> allPersons = new ArrayList<Person>();


	/**
	 * 
	 * @return {@link List<Person>} of all persons available
	 */
	public List<Person> getAllPersons() {
		return allPersons;
	}

	/**
	 * 
	 * @param personId Id of the person to be searched
	 * @return {@link Optional#empty()} if the person is not found with the supplied id else return the person id
	 */
	public Optional<Person> getPersonById(final int personId) {
		return allPersons.stream().filter(p -> p.getId() == personId).findFirst();
	}

	/**
	 * 
	 * @param personId The id of the person
	 * @return <code>true</code> or <code>false</code> denotig success or failur of the operation
	 */
	public boolean deletePerson(final int personId) {
		Optional<Person> personExit = allPersons.stream().filter(p -> p.getId() == personId).findFirst();
		if (personExit.isPresent()) {
			allPersons.remove(personExit.get());
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param personToBeAdded Data for person
	 * @return <code>true</code> or <code>false</code> denotig success or failur of the operation
	 */
	public boolean addPerson(Person personToBeAdded) {
		return allPersons.add(personToBeAdded);
	}

	/**
	 * 
	 * @param personDataTobBeUpdated Person data to be updated
	 * @return <code>true</code> or <code>false</code> denotig success or failur of the operation
	 */
	public boolean updatePerson(Person personDataTobBeUpdated) {
		
		Optional<Person> personInDb =allPersons.stream().filter(p -> p.getId() == personDataTobBeUpdated.getId()).findFirst();
		
				if(personInDb.isPresent()) {
					Person p = personInDb.get();
					p.setFirstName(personDataTobBeUpdated.getFirstName());
					p.setLastName(personDataTobBeUpdated.getLastName());
					return true;
				}else {
					return false;
				}
		/*allPersons.stream().filter(p -> p.getId() == personDataTobBeUpdated.getId()).map(p -> {
			p.setFirstName(personDataTobBeUpdated.getFirstName());
			p.setLastName(personDataTobBeUpdated.getLastName());
			return p;
		});
		return false;*/
	}

}
