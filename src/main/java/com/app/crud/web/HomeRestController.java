package com.app.crud.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.crud.models.UserModels;
import com.app.crud.repository.AppUserRepository;

@RestController
public class HomeRestController {
	@Autowired
	private AppUserRepository appUserRepository;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<UserModels> createUser(@RequestBody UserModels userModels) {
		if (appUserRepository.findOneByUsername(userModels.getUsername()) != null) {
			throw new RuntimeException("Username already exist");
		}
		userModels.setRole("USER");
		return new ResponseEntity<UserModels>(appUserRepository.save(userModels), HttpStatus.CREATED);
	}

	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}
}