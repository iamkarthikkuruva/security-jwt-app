package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.model.User;
import com.security.model.UserDetailsImpl;
import com.security.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)

			throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)

				.orElseThrow(() -> new UsernameNotFoundException("User Not exist: " +

						username));

		return UserDetailsImpl.build(user);
	}
}