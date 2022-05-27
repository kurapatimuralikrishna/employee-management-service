package com.office.home.constants;

public class EmployeeConstants {

	public static final String LOAD_ALL_EMPLOYEES = "SELECT \r\n" + "	employees.id,\r\n" + "    full_name AS name,\r\n"
	+ "    t.team_name AS team,\r\n" + "    ts.instructions AS task,\r\n"
	+ "    s.package,\r\n" + "s.payment_status AS status,\r\n"
	+ "    a.age,\r\n" + "    g.gname AS gender\r\n" + "FROM employees\r\n" + "LEFT JOIN teams t\r\n"
	+ "	ON team = t.team_id\r\n" + "LEFT JOIN tasks ts\r\n" + "	ON id = ts.employee_id\r\n"
	+ "LEFT JOIN salaries s\r\n" + "	ON id = s.employee_id\r\n" + "LEFT JOIN ages a\r\n"
	+ "	ON id = a.employee_id\r\n" + "LEFT JOIN genders g\r\n" + "	ON gender = g.id;"
	+ "LEFT JOIN employee_incentives ein ON id = ein.employee_id";
	public static final String LOAD_EMPLOYEE = "SELECT \r\n" + "	employees.id,\r\n" + "    full_name AS name,\r\n"
	+ "    t.team_name AS team,\r\n" + "    ts.instructions AS task,\r\n"
	+ "    s.package,\r\n" + "s.payment_status AS status,\r\n"
	+ "    a.age,\r\n" + "    g.gname AS gender\r\n" + "FROM employees\r\n" + "LEFT JOIN teams t\r\n"
	+ "	ON team = t.team_id\r\n" + "LEFT JOIN tasks ts\r\n" + "	ON id = ts.employee_id\r\n"
	+ "LEFT JOIN salaries s\r\n" + "	ON id = s.employee_id\r\n" + "LEFT JOIN ages a\r\n"
	+ "	ON id = a.employee_id\r\n" + "LEFT JOIN genders g\r\n" + "	ON gender = g.id\r\n"
	+ "LEFT JOIN employee_incentives ein ON id = ein.employee_id"
	+ " WHERE employees.id= ? ;";
	public static String LOAD_TEAM_ID_BY_NAME = "SELECT team_id\r\n"
	+ "FROM teams\r\n"
	+ "WHERE team_name = ?";
	public static final String LOAD_GENDER_ID_BY_NAME = "SELECT id\r\n"
	+ "FROM genders\r\n"
	+ "WHERE gname = ?";

}
