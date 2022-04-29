package com.office.home.constants;

public class UserConstants {

	public static final String LOAD_BY_USERNAME = 
			"SELECT u.username,u.user_password,u.email,r.role_name,u.employee_id "
			+ "FROM users u "
			+ "LEFT JOIN user_roles ur ON u.username=ur.username "
			+ "JOIN roles r ON ur.role_id = r.id "
			+ "WHERE u.username = ?";
	public static final String LOAD_BY_EMAIL = 
			"SELECT u.username,u.user_password,u.email,r.role_name,u.employee_id "
					+ "FROM users u "
					+ "LEFT JOIN user_roles ur ON u.username=ur.username "
					+ "JOIN roles r ON ur.role_id = r.id "
					+ "WHERE u.email = ?";
}
