package com.krvikash.example.api;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.krvikash.example.api.entity.UserInfo;
import com.krvikash.example.api.repository.UserInfoRepository;

@SpringBootApplication
public class SpringSecurityJwtApplication {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@PostConstruct
	public void insertUserInfo() {
		List<UserInfo> users = Arrays.asList(
				new UserInfo(1, "user1", "password1", "user1@gmail.com"),
				new UserInfo(2, "user2", "password2", "user2@gmail.com"),
				new UserInfo(3, "user3", "password3", "user3@gmail.com"),
				new UserInfo(4, "user4", "password4", "user4@gmail.com"));
		userInfoRepository.saveAll(users);
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}

}
