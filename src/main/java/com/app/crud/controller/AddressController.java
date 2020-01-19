package com.app.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.crud.models.AddressModels;
import com.app.crud.repository.AddressRepository;

@RestController
@RequestMapping(value = "/api")
public class AddressController {
	@Autowired
	private AddressRepository addressRepository;

	@RequestMapping(value = "/address", method = RequestMethod.GET)
	public List<AddressModels> addressModels() {
		return addressRepository.findAll();
	}

	@RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
	public ResponseEntity<AddressModels> addressById(@PathVariable Long id) {
		AddressModels addressModels = addressRepository.findOne(id);
		if (addressModels == null) {
			return new ResponseEntity<AddressModels>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<AddressModels>(addressModels, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<AddressModels> deleteAddress(@PathVariable Long id) {
		AddressModels addressModels = addressRepository.findOne(id);
		if (addressModels == null) {
			return new ResponseEntity<AddressModels>(HttpStatus.NO_CONTENT);
		} else {
			addressRepository.delete(addressModels);
			return new ResponseEntity<AddressModels>(addressModels, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/address", method = RequestMethod.POST)
	public ResponseEntity<AddressModels> createAddress(@RequestBody AddressModels addressModels) {
		return new ResponseEntity<AddressModels>(addressRepository.save(addressModels), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/address", method = RequestMethod.PUT)
	public AddressModels updateAddress(@RequestBody AddressModels addressModels) {
		return addressRepository.save(addressModels);
	}
}