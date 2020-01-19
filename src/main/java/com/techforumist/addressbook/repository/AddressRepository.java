package com.techforumist.addressbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techforumist.addressbook.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {}