package com.techforumist.addressbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techforumist.addressbook.domain.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	public AppUser findOneByUsername(String username);
}