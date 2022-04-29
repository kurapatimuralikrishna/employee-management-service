package com.office.home.dao.impl;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.office.home.dao.EmployeeDao;
import com.office.home.model.Employee;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String sql_get_all = "SELECT \r\n" + "	employees.id,\r\n" + "    full_name AS name,\r\n"
			+ "    t.team_name AS team,\r\n" + "    ts.instructions AS task,\r\n"
			+ "    s.package,\r\n" + "s.payment_status AS status,\r\n"
			+ "    a.age,\r\n" + "    g.gname AS gender\r\n" + "FROM employees\r\n" + "LEFT JOIN teams t\r\n"
			+ "	ON team = t.team_id\r\n" + "LEFT JOIN tasks ts\r\n" + "	ON id = ts.employee_id\r\n"
			+ "LEFT JOIN salaries s\r\n" + "	ON id = s.employee_id\r\n" + "LEFT JOIN ages a\r\n"
			+ "	ON id = a.employee_id\r\n" + "LEFT JOIN genders g\r\n" + "	ON gender = g.id;";
	
	public String sql_get = "SELECT \r\n" + "	employees.id,\r\n" + "    full_name AS name,\r\n"
			+ "    t.team_name AS team,\r\n" + "    ts.instructions AS task,\r\n"
			+ "    s.package,\r\n" + "s.payment_status AS status,\r\n"
			+ "    a.age,\r\n" + "    g.gname AS gender\r\n" + "FROM employees\r\n" + "LEFT JOIN teams t\r\n"
			+ "	ON team = t.team_id\r\n" + "LEFT JOIN tasks ts\r\n" + "	ON id = ts.employee_id\r\n"
			+ "LEFT JOIN salaries s\r\n" + "	ON id = s.employee_id\r\n" + "LEFT JOIN ages a\r\n"
			+ "	ON id = a.employee_id\r\n" + "LEFT JOIN genders g\r\n" + "	ON gender = g.id\r\n"
			+ " WHERE employees.id= ? ;";

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> list = new ArrayList<>();

		ResultSetExtractor<List<Employee>> rse = (ResultSet rs) -> {
			List<Employee> list2 = new ArrayList<>();
			while (rs.next()) {
				list2.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getInt(7), rs.getString(5), rs.getString(6), rs.getString(3), rs.getString(8)));

			}
			return list2;
		};

		list = jdbcTemplate.query(sql_get_all, rse);
		return list;
	}


	@Override
	public Employee getEmployee(int id) throws Exception {
		ResultSetExtractor<Employee> rse = (ResultSet rs) -> {
			if(rs.next()) {
				return new Employee(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getInt(7), rs.getString(5), rs.getString(6), rs.getString(3), rs.getString(8));
			}
			else return null;
		};
		Object[] args = {id};
		int[] types = {Types.INTEGER};
		return jdbcTemplate.query(sql_get,args,types,rse);
	}
	
	@Override
	public String addEmployee(Employee emp) throws Exception {
		String sql1 ="SELECT team_id\r\n"
				+ "FROM teams\r\n"
				+ "WHERE team_name = " +'"'+ emp.getTeam()+'"';
		int teamId = jdbcTemplate.query(sql1, (ResultSet rs)->{
			List<Integer> list = new ArrayList<>();
			if(rs.next()) {
				list.add(rs.getInt(1));
			}
			return list;
		}).get(0);
		String sql2 = "SELECT id\r\n"
				+ "FROM genders\r\n"
				+ "WHERE gname = " +'"'+ emp.getGender()+'"';
		int genId = jdbcTemplate.query(sql2, (ResultSet rs)->{
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
		String sql1 ="SELECT team_id\r\n"
				+ "FROM teams\r\n"
				+ "WHERE team_name = " +'"'+ emp.getTeam()+'"';
		int teamId = jdbcTemplate.query(sql1, (ResultSet rs)->{
			List<Integer> list = new ArrayList<>();
			if(rs.next()) {
				list.add(rs.getInt(1));
			}
			return list;
		}).get(0);
		String sql2 = "SELECT id\r\n"
				+ "FROM genders\r\n"
				+ "WHERE gname = " +'"'+ emp.getGender()+'"';
		int genId = jdbcTemplate.query(sql2, (ResultSet rs)->{
			List<Integer> list = new ArrayList<>();
			if(rs.next()) {
				list.add(rs.getInt(1));
			}
			return list;
		}).get(0);
		Object[] args1 = {emp.getTask(),id};
		int[] types1= {Types.VARCHAR,Types.INTEGER};
		jdbcTemplate.update("UPDATE tasks\r\n"
				+ "SET\r\n"
				+ "	instructions = ?\r\n"
				+ "WHERE employee_id =?;",args1,types1);
		Object[] args2 = {emp.getAge(),id};
		int[] types2= {Types.INTEGER,Types.INTEGER};
		jdbcTemplate.update("UPDATE ages\r\n"
				+ "SET\r\n"
				+ "	age = ?\r\n"
				+ "WHERE employee_id = ?;",args2,types2);
		Object[] args3 = {emp.getAnnualSalary(),emp.getPaymentStatus(),id};
		int[] types3= {Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		jdbcTemplate.update("UPDATE salaries\r\n"
				+ "SET\r\n"
				+ "	package = ?,\r\n"
				+ "    payment_status = ?\r\n"
				+ "WHERE\r\n"
				+ "	employee_id=?;",args3,types3);
		Object[] args4 = {emp.getFullName(),teamId,genId,id};
		int[] types4= {Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER};
		jdbcTemplate.update("UPDATE employees\r\n"
				+ "SET\r\n"
				+ "	full_name = ?,\r\n"
				+ "    team =?,\r\n"
				+ "    gender = ?\r\n"
				+ "WHERE id=?;",args4,types4);
		return getEmployee(id);
	}


	@Override
	public Employee deteteEmployee(int id) throws Exception {
		Employee emp = getEmployee(id);
		Object[] args = {id};
		int[] types= {Types.INTEGER};
		jdbcTemplate.update("DELETE FROM tasks WHERE employee_id=?;\r\n",args,types);
		jdbcTemplate.update("DELETE FROM salaries WHERE employee_id=?;\r\n",args,types);
		jdbcTemplate.update("DELETE FROM ages WHERE employee_id=?;\r\n",args,types);
		jdbcTemplate.update("DELETE FROM employees WHERE id=?;\r\n",args,types);
		return emp;
	}
}
