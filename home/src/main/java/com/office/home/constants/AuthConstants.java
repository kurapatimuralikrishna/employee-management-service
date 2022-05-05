package com.office.home.constants;

public class AuthConstants {

	public static final String LOAD_BY_USERNAME = "SELECT username,user_password,email,employee_id " 
			+ "FROM users "
			+ "WHERE username = ?";
	public static final String LOAD_BY_EMAIL = "SELECT username,user_password,email,employee_id " 
			+ "FROM users "
			+ "WHERE email = ?";
	public static final String LOAD_ROLES_BY_USERNAME = "SELECT ur.username,r.role_name " 
			+ "FROM user_roles ur "
			+ "JOIN roles r ON ur.role_id=r.id "
			+ "WHERE username = ?";
	public static final String LOAD_ROLE_BY_ID = "SELECT id FROM roles WHERE role_name = ?";
	public static final String REGISTER_USER = "INSERT INTO users VALUES (?,?,?,?);";
	public static final String ADD_ROLE = "INSERT INTO user_roles VALUES (?,?)";
	public static final String ADD_SECRET_QUESTION = "INSERT INTO user_secrets VALUES (?,?,?)";
	public static final String LOAD_SECRET_QUESTION_BY_USERNAME = "SELECT secret_question FROM user_secrets WHERE username = ?";
	public static final String LOAD_SECRET_ANSWER_BY_USERNAME = "SELECT secret_answer FROM user_secrets WHERE username = ?";
	public static final String CHANGE_PASSWORD_BY_USERNAME = "UPDATE users SET user_password = ? WHERE username = ?";
}
