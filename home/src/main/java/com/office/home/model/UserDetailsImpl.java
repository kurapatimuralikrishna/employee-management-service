package com.office.home.model;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {

	/**
	 * Uses Integer.toString() method to convert employee Id to string username.
	 */
	private static final long serialVersionUID = -3567625991964713504L;
	private String username;
	@JsonIgnore
	private String password;
	private String email;
	private Integer employeeId;
	private List<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String username, String password, String email, Integer employeeId,
			List<? extends GrantedAuthority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.employeeId = employeeId;
		this.authorities = authorities;
	}
	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
		return new UserDetailsImpl(user.getUsername(),user.getUserPassword(),user.getEmail(),user.getEmployeeId(),authorities);
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public int getEmployeeId() {
		return employeeId;
	}
	
	public String getEmail() {
		return email;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) obj;
		return Objects.equals(username, user.username);
	}

	
}
