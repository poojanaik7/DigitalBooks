package com.kafka.demo;
import java.util.Arrays;

public class User {

	public int getId() {
		return id;
	}
	
	public User() {
		super();
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
	public String[] getAddress() {
		return address;
	}
	public void setAddress(String[] address) {
		this.address = address;
	}
	@Override
	public String toString() {
	return "User [id=" + id + ", name=" + name + ", address=" + Arrays.toString(address) + "]";
	}
	
	public User(int id, String name, String[] address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		}
	private int id;
	private String name;
	private String[] address;

}
