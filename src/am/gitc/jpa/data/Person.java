package am.gitc.jpa.data;

import javax.persistence.*;
import java.io.*;

@Entity
@Table(name="person")
public class Person implements Serializable {

	public enum PersonStatus {
		ACTIVE,
		EMAIL_NOT_CONFIRMED
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private PersonStatus status;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@Column(name="email")
	private String email;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonStatus getStatus() {
		return status;
	}

	public void setStatus(PersonStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Person{" +
			"id=" + id +
			", name='" + name + '\'' +
			", surname='" + surname + '\'' +
			", email='" + email + '\'' +
			", username='" + username + '\'' +
			", password='" + password + '\'' +
			'}';
	}
}