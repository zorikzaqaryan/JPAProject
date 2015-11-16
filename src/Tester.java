import am.gitc.jpa.data.*;
import am.gitc.jpa.manager.*;
import am.gitc.jpa.manager.impl.*;
import am.gitc.jpa.util.*;

import java.util.*;

/**
 * Created by Garik on 12/5/14.
 */
public class Tester {

	private IPersonManager manager;

	public Tester() {
		manager = new PersonManagerImpl();
	}

	public Person add() {
		Person person = getPerson();
		manager.addPerson(person);
		return person;
	}

	public List<Person> getPersons() {
		return manager.getPersons();
	}

	public Person edit(Person person) {
		return manager.editPerson(person);
	}

	public Person getByID(long id) {
		return manager.getPersonById(id);
	}

	public static void main(String[] args) {

		Tester tester = new Tester();

		// adds person data
		//Person person = tester.add();
		//System.out.println(person);

		// retrieves list of persons
		List<Person> persons = tester.getPersons();
		for (Person person : persons) {
			System.out.println(person);
		}

		// adds and edits person data
		//Person person = tester.getPerson();
		//person.setEmail("edited-" + person.getEmail());
		//person = tester.edit(person);
		//System.out.println(person);
		//
		//Person samePerson = tester.getByID(person.getId());
		//System.out.println(samePerson);

		tester.manipulateToken();
	}

	public void manipulateToken() {

		// adds person data
		Person person = add();

		// adds person's email token data
		PersonToken emailToken = new PersonToken();
		emailToken.setPerson(person);
		emailToken.setType(PersonToken.TokenType.EMAIL_CONFIRMATION);
		emailToken.setValue(Generator.doToken());

		manager.addToken(emailToken, false);

		// adds person's password token data
		PersonToken passwordToken = new PersonToken();
		passwordToken.setPerson(person);
		passwordToken.setType(PersonToken.TokenType.PASSWORD_RESET);
		passwordToken.setValue(Generator.doToken());

		manager.addToken(passwordToken, false);


		PersonToken sameEmailToken = manager.getTokenByValue(emailToken.getValue());
		System.out.println(sameEmailToken);

		PersonToken samePasswordToken = manager.getTokenByValue(passwordToken.getValue());
		System.out.println(samePasswordToken);
	}

	public Person getPerson() {
		long unique = System.currentTimeMillis();
		Person person = new Person();
		person.setStatus(Person.PersonStatus.ACTIVE);
		person.setName("name-" + unique);
		person.setSurname("surname-" + unique);
		person.setEmail(unique + "@mail.com");
		person.setUsername("username-" + unique);
		person.setPassword("user");
		return person;
	}
}
