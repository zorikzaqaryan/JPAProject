package am.gitc.jpa.data;

import javax.persistence.*;

/**
 * Created by Garik on 12/6/14.
 */
@Entity
@Table(name = "person_token")

@NamedQuery(name="findAllUserTokens",
	query="SELECT t FROM PersonToken t WHERE t.person.id = :personID")

public class PersonToken {

	public enum TokenType {

		EMAIL_CONFIRMATION,

		PASSWORD_RESET
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;

	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private TokenType type;

	@Column(name="value")
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public TokenType getType() {
		return type;
	}

	public void setType(TokenType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "PersonToken{" +
			"id=" + id +
			", person=" + person +
			", type=" + type +
			", value='" + value + '\'' +
			'}';
	}
}
