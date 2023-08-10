package com.krvikash.example.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.krvikash.example.api.entity.UserInfo;
import com.krvikash.example.api.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userinfo = userInfoRepository.findByName(username);
		return userinfo != null ? new User(username, userinfo.getPassword(), new ArrayList<>()) : null;
	}
}
