package com.app.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.crud.models.UserModels;
import com.app.crud.repository.UserRepository;

@RestController
@RequestMapping(value = "/api")
public class AppUserRestController {
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserModels> userModels() {
		return userRepository.findAll();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserModels> userById(@PathVariable Long id) {
		UserModels userModels = userRepository.findOne(id);
		if (userModels == null) {
			return new ResponseEntity<UserModels>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<UserModels>(userModels, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserModels> deleteUser(@PathVariable Long id) {
		UserModels userModels = userRepository.findOne(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		if (userModels == null) {
			return new ResponseEntity<UserModels>(HttpStatus.NO_CONTENT);
		} else if (userModels.getUsername().equalsIgnoreCase(loggedUsername)) {
			throw new RuntimeException("You cannot delete your account");
		} else {
			userRepository.delete(userModels);
			return new ResponseEntity<UserModels>(userModels, HttpStatus.OK);
		}

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<UserModels> createUser(@RequestBody UserModels userModels) {
		if (userRepository.findOneByUsername(userModels.getUsername()) != null) {
			throw new RuntimeException("Username already exist");
		}
		return new ResponseEntity<UserModels>(userRepository.save(userModels), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public UserModels updateUser(@RequestBody UserModels userModels) {
		if (userRepository.findOneByUsername(userModels.getUsername()) != null
				&& userRepository.findOneByUsername(userModels.getUsername()).getId() != userModels.getId()) {
			throw new RuntimeException("Username already exist");
		}
		return userRepository.save(userModels);
	}
}