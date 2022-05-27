package com.office.home.model;

public class ErrorEmployee extends Employee {

	public ErrorEmployee(Exception e) {
		super(0, e.getMessage(), "Error", 0, "Error", "Error", "Error", "Error",0);
	}
	
}
