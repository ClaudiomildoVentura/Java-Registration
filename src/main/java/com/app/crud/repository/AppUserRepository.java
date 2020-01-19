package com.app.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.crud.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	public AppUser findOneByUsername(String username);
}