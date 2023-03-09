package com.PASTRACK.PASTRACK.Security.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.Model.UserModel;
import com.PASTRACK.PASTRACK.Repository.UserDB;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDB userDb;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userDb.findByUsername(username);
		if (user==null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
		} else {
			Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getRole()));
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
		}
    }
}
	