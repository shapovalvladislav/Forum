package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the "ForbiddenWords" database table.
 * 
 */
@Entity
@Table(name="\"ForbiddenWords\"")
@NamedQuery(name="ForbiddenWord.findAll", query="SELECT f FROM ForbiddenWord f")
public class ForbiddenWord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String word;

	public ForbiddenWord() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}