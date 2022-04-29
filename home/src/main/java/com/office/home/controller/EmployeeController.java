package com.office.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.office.home.model.Employee;
import com.office.home.model.ErrorEmployee;
import com.office.home.service.EmployeeService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("office")
public class EmployeeController {
	@Autowired
	EmployeeService service;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return service.getAllEmployees();
	}
	@GetMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable int id) {
		try {
			Employee emp =service.getEmployee(id);
			if(emp!=null) return emp;
			else throw new Exception("No such Employee exists");
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ErrorEmployee(e);
		}
	}
	@PostMapping("/employees/add")
	public String addEmployee(@RequestBody Employee emp) {
		try {
		return service.addEmployee(emp);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	@PostMapping("employees/{id}/update")
	public Employee updateEmployee(@PathVariable("id") int id, @RequestBody Employee emp) {
		try {
			return service.updateEmployee(id,emp);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ErrorEmployee(e);
		}
	}
	@DeleteMapping("employees/{id}/delete")
	public Employee deleteEmployee(@PathVariable int id) {
		try {
			return service.deleteEmployee(id);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ErrorEmployee(e);
		}
	}
}
