package com.office.home.constants;

public class UserRequestConstants {
	//Add an incentive claim
	//clear a bunch of incentives when payment happens
	//refresh total incentives earned by each employee every time incentives table amounts are modified.
	public static final String ADD_INCENTIVE_CLAIM = "INSERT INTO incentives VALUES (?,?,?,?,?)";
	public static final String LOAD_AMOUNTS = "SELECT id, amount FROM incentives";
	public static final String CLEAR_INCENTIVES = "DELETE FROM incentives";
	public static final String LOAD_UPDATED_CLAIMS = 
			"SELECT employee_id,sum(amount) FROM incentives GROUP BY employee_id";
	public static final String SET_UPDATED_CLAIMS_BY_USERNAME = 
			"UPDATE employee_incentives SET total_incentives = ? WHERE employee_id =  ?";
}