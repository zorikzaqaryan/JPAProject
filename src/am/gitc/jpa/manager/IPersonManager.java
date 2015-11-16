package am.gitc.jpa.manager;


import am.gitc.jpa.data.*;

import java.util.*;

public interface IPersonManager {

	public boolean checkUsernameExists(String username);
	
	public void addPerson(Person person);
	
	public Person getPersonById(Long id);
	
	public Person getPersonByUsername(String username);
	
	public Person getPersonByUsernamePassword(String username, String password);
	
	public List<Person> getPersons();
	
	public Person editPerson(Person person);
	
	public void removePerson(Long id);

	public void addToken(PersonToken token, boolean sendEmail);

	public PersonToken getTokenByValue(String value);

	public void removeToken(PersonToken token);

	public void close();
}
