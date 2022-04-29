package com.office.home.model;

public class Employee {
	private int id;
	private String fullName;
	private String task;
	private int age;
	private String annualSalary;
	private String paymentStatus;
	private String team;
	private String gender;
	public Employee(int id, String fullName, String task, int age, String annualSalary, String status, String team, String gender) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.task = task;
		this.age = age;
		this.annualSalary = annualSalary;
		this.paymentStatus = status;
		this.team = team;
		this.gender = gender;
	}
	public int getId() {
		return id;
	}
	public String getFullName() {
		return fullName;
	}
	public String getTask() {
		return task;
	}
	public int getAge() {
		return age;
	}
	public String getAnnualSalary() {
		return annualSalary;
	}
	public String getTeam() {
		return team;
	}
	public String getGender() {
		return gender;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setAnnualSalary(String annualSalary) {
		this.annualSalary = annualSalary;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", fullName=" + fullName + ", task=" + task + ", age=" + age + ", salary="
				+ annualSalary + ", team=" + team + ", gender=" + gender + "]";
	}
	
	
}
