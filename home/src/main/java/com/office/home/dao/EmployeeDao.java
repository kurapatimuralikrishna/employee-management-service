package com.office.home.dao;

import java.util.List;

import com.office.home.model.Employee;

public interface EmployeeDao {
	List<Employee> getAllEmployees();

	String addEmployee(Employee emp) throws Exception;

	Employee updateEmployee(int id, Employee emp) throws Exception;

	Employee getEmployee(int id) throws Exception;

	Employee deteteEmployee(int id) throws Exception;
}
