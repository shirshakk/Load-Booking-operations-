package com.load_booking.load_booking.Model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailImplementation implements UserDetails {
    private User user;
	public UserDetailImplementation(User user) {
		this.user=user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String role = "ROLE_" + user.getRole().name().toUpperCase();
    	System.out.println("Authority: " + role);
   		return List.of(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getPassword() {
		return user.getPassword(); 
	}

	@Override
	public String getUsername() {
		return user.getUserName(); 
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
}