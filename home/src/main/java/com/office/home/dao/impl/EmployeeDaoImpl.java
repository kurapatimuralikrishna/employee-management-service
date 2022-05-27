package com.office.home.dao.impl;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.office.home.constants.EmployeeConstants;
import com.office.home.dao.EmployeeDao;
import com.office.home.model.Employee;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

	private final JdbcTemplate jdbcTemplate;
	@Autowired
	public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> list = new ArrayList<>();

		ResultSetExtractor<List<Employee>> rse = (ResultSet rs) -> {
			List<Employee> list2 = new ArrayList<>();
			while (rs.next()) {
				list2.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getInt(7), rs.getString(5), rs.getString(6), rs.getString(3), rs.getString(8), rs.getInt(9)));

			}
			return list2;
		};

		list = jdbcTemplate.query(EmployeeConstants.LOAD_ALL_EMPLOYEES, rse);
		return list;
	}


	@Override
	public Employee getEmployee(int id) throws Exception {
		ResultSetExtractor<Employee> rse = (ResultSet rs) -> {
			if(rs.next()) {
				return new Employee(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getInt(7), rs.getString(5), rs.getString(6), rs.getString(3), rs.getString(8), rs.getInt(9));
			}
			else return null;
		};
		Object[] args = {id};
		int[] types = {Types.INTEGER};
		return jdbcTemplate.query(EmployeeConstants.LOAD_EMPLOYEE,args,types,rse);
	}
	
	@Override
	public String addEmployee(Employee emp) throws Exception {
		Object[] args = {emp.getTeam()};
		int[] types = { Types.VARCHAR };
		int teamId = jdbcTemplate.query(EmployeeConstants.LOAD_TEAM_ID_BY_NAME, args, types, (ResultSet rs) -> {
			List<Integer> list = new ArrayList<>();
			if (rs.next()) {
				list.add(rs.getInt(1));
			}
			return list;
		}).get(0);
		Object[] args2 = {emp.getGender()};
		int[] types2 = {Types.VARCHAR};
		int genId = jdbcTemplate.query(EmployeeConstants.LOAD_GENDER_ID_BY_NAME,args2,types2, (ResultSet rs)->{
			List<Integer> list = new ArrayList<>();
			if(rs.next()) {
				list.add(rs.getInt(1));
			}
			return list;
		}).get(0);
		jdbcTemplate.update("INSERT INTO employees\r\n"
				+ "VALUES\r\n"
				+ "	("
				+ emp.getId() +","
				+'"'+ emp.getFullName()+'"' +","
				+ teamId +","
				+ genId
				+ ");");
		jdbcTemplate.update("INSERT INTO employee_incentives VALUES ("+emp.getId()+",0)");
		jdbcTemplate.update("INSERT INTO tasks(employee_id,instructions)\r\n"
				+ "VALUES\r\n"
				+ "	("
				+ emp.getId() +","
				+'"'+ emp.getTask()+'"'
				+ ");");
		jdbcTemplate.update("INSERT INTO ages(employee_id,age)\r\n"
				+ "VALUES\r\n"
				+ "	("
				+ emp.getId() +","
				+ emp.getAge()
				+ ");");
		jdbcTemplate.update("INSERT INTO salaries(employee_id,package,payment_status)\r\n"
				+ "VALUES\r\n"
				+ "	("
				+ emp.getId() +","
				+ emp.getAnnualSalary() +","
				+'"'+ emp.getPaymentStatus()+'"'
				+ ");");
		return "Done";
		
	}

	@Override
	public Employee updateEmployee(int id, Employee emp) throws Exception {
		if(getEmployee(id)==null) throw new Exception("No such employee exists");
		Object[] args1 = { emp.getTeam() };
		int[] types1 = { Types.VARCHAR };
		int teamId = jdbcTemplate.query(EmployeeConstants.LOAD_TEAM_ID_BY_NAME, args1, types1, (ResultSet rs) -> {
			List<Integer> list = new ArrayList<>();
			if (rs.next()) {
				list.add(rs.getInt(1));
			}
			return list;
		}).get(0);
		Object[] args2 = { emp.getGender() };
		int[] types2 = { Types.VARCHAR };
		int genId = jdbcTemplate
				.query(EmployeeConstants.LOAD_GENDER_ID_BY_NAME, args2, types2, (ResultSet rs) -> {
					List<Integer> list = new ArrayList<>();
					if (rs.next()) {
						list.add(rs.getInt(1));
					}
					return list;
				}).get(0);
		Object[] args3 = {emp.getTask(),id};
		int[] types3= {Types.VARCHAR,Types.INTEGER};
		jdbcTemplate.update("UPDATE tasks\r\n"
				+ "SET\r\n"
				+ "	instructions = ?\r\n"
				+ "WHERE employee_id =?;",args3,types3);
		Object[] args4 = {emp.getAge(),id};
		int[] types4 = {Types.INTEGER,Types.INTEGER};
		jdbcTemplate.update("UPDATE ages\r\n"
				+ "SET\r\n"
				+ "	age = ?\r\n"
				+ "WHERE employee_id = ?;",args4,types4);
		Object[] args5 = {emp.getAnnualSalary(),emp.getPaymentStatus(),id};
		int[] types5= {Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		jdbcTemplate.update("UPDATE salaries\r\n"
				+ "SET\r\n"
				+ "	package = ?,\r\n"
				+ "    payment_status = ?\r\n"
				+ "WHERE\r\n"
				+ "	employee_id=?;",args5,types5);
		Object[] args6 = {emp.getFullName(),teamId,genId,id};
		int[] types6 = {Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER};
		jdbcTemplate.update("UPDATE employees\r\n"
				+ "SET\r\n"
				+ "		full_name = ?,\r\n"
				+ "    team =?,\r\n"
				+ "    gender = ?\r\n"
				+ "WHERE id=?;",args6,types6);
		return getEmployee(id);
	}


	@Override
	public Employee deteteEmployee(int id) throws Exception {
		Employee emp = getEmployee(id);
		Object[] args = {id};
		int[] types= {Types.INTEGER};
		Object[] args2 = {""+id};
		int[] types2 = {Types.VARCHAR};
		jdbcTemplate.update("DELETE FROM incentives WHERE employee_id=?;",args,types);
		jdbcTemplate.update("DELETE FROM employee_incentives WHERE employee_id=?;",args,types);
		
		jdbcTemplate.update("DELETE FROM user_secrets WHERE username=?;",args2,types2);
		jdbcTemplate.update("DELETE FROM user_roles WHERE username=?;",args2,types2);
		jdbcTemplate.update("DELETE FROM users WHERE username=?;",args2,types2);
		
		jdbcTemplate.update("DELETE FROM tasks WHERE employee_id=?;",args,types);
		jdbcTemplate.update("DELETE FROM salaries WHERE employee_id=?;",args,types);
		jdbcTemplate.update("DELETE FROM ages WHERE employee_id=?;",args,types);
		jdbcTemplate.update("DELETE FROM employees WHERE id=?;",args,types);
		return emp;
	}
}
