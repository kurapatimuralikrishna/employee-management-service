package com.office.home.dao;

import com.office.home.model.User;

public interface UserDao {

	User loadByUsername(String username);

	boolean existsByEmployeeId(int employeeId);

	boolean existsByEmail(String email);

	void registerUser(User user);

	String getSecretQuestion(String username);

	String getSecretAnswer(String username);

	void changePassword(String username, String encodedPassword);

}
