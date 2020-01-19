package com.app.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.crud.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {}