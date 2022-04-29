package com.office.home.dao;

import com.office.home.model.User;

public interface UserDao {

	User loadByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}
