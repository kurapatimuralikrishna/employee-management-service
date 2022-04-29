package com.office.home.service;

import java.util.List;

import com.office.home.model.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();

	String addEmployee (Employee emp) throws Exception;

	Employee updateEmployee(int id, Employee emp) throws Exception;

	Employee getEmployee(int id) throws Exception;

	Employee deleteEmployee(int id) throws Exception;
}
