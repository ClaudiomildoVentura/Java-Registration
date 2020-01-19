package com.app.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.crud.models.AddressModels;

public interface AddressRepository extends JpaRepository<AddressModels, Long> {}