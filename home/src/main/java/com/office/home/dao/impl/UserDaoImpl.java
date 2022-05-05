package com.office.home.dao.impl;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.office.home.constants.AuthConstants;
import com.office.home.dao.UserDao;
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
		String sql = AuthConstants.LOAD_BY_USERNAME;
		Object[] args = { username };
		int[] types = { Types.INTEGER };
		User user = jdbcTemplate.query(sql, args, types, 
				(ResultSet rs) -> {
					Set<String> emptyRoles = new HashSet<>();
					User userBuilder;
					if(rs.next()) {
						userBuilder = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),emptyRoles);
					}
					else return null;
					return userBuilder;
		});
		String sql2 = AuthConstants.LOAD_ROLES_BY_USERNAME;
		Set<String> roles = jdbcTemplate.query(sql2,args,types,
				(ResultSet rs)->{
					Set<String> roleBuilder = new HashSet<>();
					while(rs.next()) {
						roleBuilder.add(rs.getString(2));
					}
					return roleBuilder;
				});
		user.setRoles(roles);
		return user;
	}

	@Override
	public boolean existsByEmployeeId(int employeeId) {
		String sql = AuthConstants.LOAD_BY_USERNAME;
		Object[] args = { employeeId };
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
		String sql = AuthConstants.LOAD_BY_EMAIL;
		Object[] args = { email };
		int[] types = { Types.VARCHAR };
		boolean emailExists = jdbcTemplate.query(sql, args, types, 
				(ResultSet rs) -> {
					if(rs.next()) return true;
					else return false;
		});
		return emailExists;
	}

	@Override
	public void registerUser(User user) {
		String sql = AuthConstants.REGISTER_USER;
		Object args[] = {user.getUsername(),user.getUserPassword(),user.getEmail(),user.getEmployeeId()};
		int[] types = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		jdbcTemplate.update(sql,args,types);
		
		int userRoleId = 1;
		String sql2 = AuthConstants.ADD_ROLE;
		Object[] args2 = {user.getUsername(),userRoleId};
		int[] types2 = {Types.INTEGER,Types.INTEGER};
		jdbcTemplate.update(sql2,args2,types2);
	}

	@Override
	public String getSecretQuestion(String username) {
		String sql = AuthConstants.LOAD_SECRET_QUESTION_BY_USERNAME;
		Object[] args = { username };
		int[] types = { Types.VARCHAR };
		String secretQuestion = jdbcTemplate.query(sql, args, types, 
				(ResultSet rs) -> {
					if(rs.next()) return rs.getString(1);
					else return null;
		});
		return secretQuestion;
	}

	@Override
	public String getSecretAnswer(String username) {
		String sql = AuthConstants.LOAD_SECRET_ANSWER_BY_USERNAME;
		Object[] args = { username };
		int[] types = { Types.VARCHAR };
		String secretAnswer = jdbcTemplate.query(sql, args, types, 
				(ResultSet rs) -> {
					if(rs.next()) return rs.getString(1);
					else return null;
		});
		return secretAnswer;
	}

	@Override
	public void changePassword(String username, String encodedPassword) {
		String sql = AuthConstants.CHANGE_PASSWORD_BY_USERNAME;
		Object[] args = { encodedPassword,username };
		int[] types = { Types.VARCHAR,Types.VARCHAR };
		jdbcTemplate.update(sql,args,types);
	}
}
