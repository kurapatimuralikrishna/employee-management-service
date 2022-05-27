package com.office.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.office.home.annotations.IsAdmin;
import com.office.home.annotations.IsUser;
import com.office.home.payload.request.ClaimForm;
import com.office.home.service.UserRequestService;

@CrossOrigin(origins = "*", maxAge=3600)
@RestController
@RequestMapping("/office/user")
public class UserRequestController {
	@Autowired
	UserRequestService service;
	/**
	 * takes incentive form as input
	 * processes and adds claim
	 * updates total incentives earned by that employee
	 * returns true if success
	 */
	@PostMapping("/addClaim")
	@IsUser
	public boolean addClaim(ClaimForm claimForm) {
		return service.addClaim();
	}
	/**
	 * clear all incentives
	 */
	@PostMapping
	@IsAdmin
	public void clearAllIncentives(){
		
	}
	
}
