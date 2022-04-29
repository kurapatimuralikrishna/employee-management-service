package com.office.home.dao.impl;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.office.home.constants.UserConstants;
import com.office.home.dao.UserDao;
import com.office.home.model.ERole;
import com.office.home.model.User;

@Component
public class UserDaoImpl implements UserDao {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public UserDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public User loadByUsername(String username) {
		String sql = UserConstants.LOAD_BY_USERNAME;
		Object[] args = { username };
		int[] types = { Types.VARCHAR };
		User user = jdbcTemplate.query(sql, args, types, 
				(ResultSet rs) -> {
					Set<ERole> roles = new HashSet<>();
					User userBuilder;
					if(rs.next()) {
						userBuilder = new User(rs.getString(1),rs.getString(2),rs.getString(3),roles,rs.getInt(5));
					}
					else return null;
					do {
						ERole erole = ERole.valueOf(rs.getString(3));
						userBuilder.getRoles().add(erole);
					} while(rs.next());
					return userBuilder;
		});
		return user;
	}

	@Override
	public boolean existsByUsername(String username) {
		String sql = UserConstants.LOAD_BY_USERNAME;
		Object[] args = { username };
		int[] types = { Types.VARCHAR };
		boolean userExists = jdbcTemplate.query(sql, args, types, 
				(ResultSet rs) -> {
					if(rs.next()) return true;
					else return false;
		});
		return userExists;
	}

	@Override
	public boolean existsByEmail(String email) {
		String sql = UserConstants.LOAD_BY_EMAIL;
		Object[] args = { email };
		int[] types = { Types.VARCHAR };
		boolean emailExists = jdbcTemplate.query(sql, args, types, 
				(ResultSet rs) -> {
					if(rs.next()) return true;
					else return false;
		});
		return emailExists;
	}
}
