package am.gitc.jpa.manager.impl;

import am.gitc.jpa.data.*;
import am.gitc.jpa.manager.*;
import am.gitc.jpa.manager.util.*;

import javax.persistence.*;
import java.math.*;
import java.util.*;

public class PersonManagerImpl implements IPersonManager {

	private EntityManager entityManager;

	public PersonManagerImpl() {
		entityManager = EntityManagerUtil.getEntityManager();
	}

	@Override
	public boolean checkUsernameExists(String username) {
		BigInteger count;
		String strQuery = "SELECT count(*) FROM person WHERE username=:username";
		try {
			Query query = entityManager.createNativeQuery(strQuery);
			query.setParameter("username", username);			
			count = (BigInteger) query.getSingleResult();
		} catch (Exception e) {
			throw new RuntimeException("UNABLE TO GET PERSON BY ID", e);
		}
		return (count != null && count.intValue() > 0);
	}

	@Override
	public void addPerson(Person person) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(person);			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			try {
				entityManager.getTransaction().rollback();
			} catch (Exception ex) {}
			throw new RuntimeException("UNABLE TO ADD PERSON", e);
		}
	}

	@Override
	public Person getPersonById(Long id) {
		Person person = null;
		try {
			person = entityManager.find(Person.class, id);
		} catch (Exception e) {
			throw new RuntimeException("UNABLE TO GET PERSON BY ID", e);
		}
		return person;
	}

	@Override
	public Person getPersonByUsername(String username) {
		Person person = null;
		String strQuery = "SELECT * FROM person WHERE username=:username";
		try {
			Query query = entityManager.createNativeQuery(strQuery, Person.class);
			query.setParameter("username", username);
			try {
				person = (Person) query.getSingleResult();
			} catch (EntityNotFoundException e) {}
		} catch (Exception e) {
			throw new RuntimeException("UNABLE TO GET PERSON BY USERNAME", e);
		}
		return person;
	}
	
	@Override
	public Person getPersonByUsernamePassword(String username, String password) {
		Person person = null;
		String strQuery = "SELECT * FROM person WHERE username=:username AND password=:password";
		try {
			Query query = entityManager.createNativeQuery(strQuery, Person.class);
			query.setParameter("username", username);
			query.setParameter("password", password);
			try {
				person = (Person) query.getSingleResult();
			} catch (EntityNotFoundException e) {}
		} catch (Exception e) {
			throw new RuntimeException("UNABLE TO GET PERSON BY USERNAME/PASSWORD", e);
		}
		return person;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getPersons() {
		List<Person> persons = null;
		try {
			Query query = entityManager.createQuery("SELECT p FROM Person p");
			persons = query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("UNABLE TO GET PERSONS", e);
		}
		return persons;
	}

	@Override
	public Person editPerson(Person person) {
		try {
			entityManager.getTransaction().begin();
			person = entityManager.merge(person);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			try {
				entityManager.getTransaction().rollback();
			} catch (Exception ex) {}
			throw new RuntimeException("UNABLE TO EDIT PERSON", e);
		}
		return person;
	}

	@Override
	public void removePerson(Long id) {
		try {
			Person person = entityManager.find(Person.class, id);
			if(person != null){
				entityManager.getTransaction().begin();
				entityManager.remove(person);
				entityManager.getTransaction().commit();
			}
		} catch (Exception e) {
			try {
				entityManager.getTransaction().rollback();
			} catch (Exception ex) {}
			throw new RuntimeException("UNABLE TO GET PERSON BY ID", e);
		}
	}

	@Override
	public void addToken(PersonToken token, boolean sendEmail) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(token);
			if (sendEmail) {
				// TODO send corresponding email
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			try {
				entityManager.getTransaction().rollback();
			} catch (Exception ex) {}
			throw new RuntimeException("Failed to add person token", e);
		}
	}

	@Override
	public PersonToken getTokenByValue(String value) {
		try {
			Query query = entityManager.createQuery("SELECT t FROM PersonToken t WHERE t.value = :value");
			query.setParameter("value", value);
			try {
				return (PersonToken) query.getSingleResult();
			} catch (EntityNotFoundException e) {}
		} catch (Exception e) {
			throw new RuntimeException("Failed to retrieve person's token", e);
		}
		return null;
	}

	public List<PersonToken> getPersonToken(long personID) {

		try {
			Query query = entityManager.createNamedQuery("findAllUserTokens");
			query.setParameter("personID", personID);
			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void removeToken(PersonToken token) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(token);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			try {
				entityManager.getTransaction().rollback();
			} catch (Exception ex) {}
			throw new RuntimeException("Failed to remove person token", e);
		}
	}

	public void close() {
		if (entityManager != null) {
			entityManager.close();
		}
	}
}
