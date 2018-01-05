package com.ex.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "ANIMALS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Animal implements Serializable {

	private static final long serialVersionUID = -8746888018176206311L;

	@Id
	@Column(name = "ANIMAL_ID")
	@SequenceGenerator(name = "ANIMAL_SEQ", sequenceName = "ANIMAL_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANIMAL_SEQ")
	private int id;

	@Column(name = "ANIMAL_NAME")
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ANIMAL_CAVE")
	private Cave cave;

	public Animal() {
		super();
	}

	public Animal(int id, String name, Cave cave) {
		super();
		this.id = id;
		this.name = name;
		this.cave = cave;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Cave getCave() {
		return cave;
	}

	public void setCave(Cave cave) {
		this.cave = cave;
	}

}
