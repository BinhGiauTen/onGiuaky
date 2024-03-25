package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Clazz {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Clazz(String name) {
		super();
		this.name = name;
	}
	@Override
	public String toString() {
		return "Clazz [id=" + id + ", name=" + name + "]";
	}
	public Clazz() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
}
