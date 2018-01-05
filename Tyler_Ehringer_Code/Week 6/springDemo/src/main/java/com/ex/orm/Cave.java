package com.ex.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="CAVES")
public class Cave implements Serializable{
	
	private static final long serialVersionUID = 7316255138912857163L;

	@Id
	@Column(name="CAVE_ID")
	@SequenceGenerator(name="CAVE_SEQ", sequenceName="CAVE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CAVE_SEQ")
	private int id;
	
	@Column(name="CAVE_NAME")
	private String name;

	public Cave(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Cave() {
		super();
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
	

}
