package com.office.home.model;

public enum ERole{
	ROLE_USER(1),
	ROLE_MOD(2),
	ROLE_ADMIN(3);
	private int roleId;
	ERole(int roleId) {
		this.roleId = roleId;
	}
	public int getRoleId() {
		return this.roleId;
	}
}