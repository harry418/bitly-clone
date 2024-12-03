package com.project.URLSortner.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.URLSortner.entity.User;
import com.project.URLSortner.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepo userRepo;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		// Build UserDetails object using the User entity
        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getUserName());
        builder.password(user.getPassword());
        builder.roles("USER"); // Assign ROLE_USER to every authenticated user

        return builder.build();
	}

}
