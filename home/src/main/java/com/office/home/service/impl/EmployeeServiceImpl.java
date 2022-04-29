package com.office.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.home.dao.EmployeeDao;
import com.office.home.model.Employee;
import com.office.home.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao dao;

	@Override
	public List<Employee> getAllEmployees() {
		return dao.getAllEmployees();
	}

	@Override
	public String addEmployee(Employee emp) throws Exception {
		return dao.addEmployee(emp);
	}

	@Override
	public Employee updateEmployee(int id, Employee emp) throws Exception {
		return dao.updateEmployee(id, emp);
	}

	@Override
	public Employee getEmployee(int id) throws Exception {
		return dao.getEmployee(id);
	}

	@Override
	public Employee deleteEmployee(int id) throws Exception {
		return dao.deteteEmployee(id);
	}

}
