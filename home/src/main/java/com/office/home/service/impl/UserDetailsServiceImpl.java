package com.office.home.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.office.home.dao.UserDao;
import com.office.home.model.User;
import com.office.home.model.UserDetailsImpl;

public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UserDao dao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = dao.loadByUsername(username);
		if(user==null) throw new UsernameNotFoundException("No such user");
		return UserDetailsImpl.build(user);
	}

}
