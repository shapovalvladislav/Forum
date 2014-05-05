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
public class ForbiddenWord extends DomainSuperClass implements Serializable {
	private static final long serialVersionUID = 1L;


	private String word;

	public ForbiddenWord() {
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}