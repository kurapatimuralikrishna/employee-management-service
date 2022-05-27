package com.office.home.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.home.dao.UserRequestDao;
import com.office.home.service.UserRequestService;

@Service
public class UserRequestServiceImpl implements UserRequestService {

	@Autowired
	UserRequestDao dao;
	@Override
	public boolean addClaim() {
		boolean result = dao.addClaim();
		if(result) dao.refreshIncentives();
		return result;
	}

}
