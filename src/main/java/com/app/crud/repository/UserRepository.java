package com.app.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.crud.models.UserModels;

public interface UserRepository extends JpaRepository<UserModels, Long> {
	public UserModels findOneByUsername(String username);
}