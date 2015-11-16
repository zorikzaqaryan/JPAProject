package am.gitc.jpa.data;

import javax.persistence.*;

/**
 * Created by Garik on 12/6/14.
 */
@Entity
@Table(name = "lecture")
public class Lecture {

	public enum Gender {
		MALE,
		FEMALE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name="name")
	private String name;

	@Column(name="surname")
	private String surname;

	@Column(name="gender")
	private Gender gender;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
