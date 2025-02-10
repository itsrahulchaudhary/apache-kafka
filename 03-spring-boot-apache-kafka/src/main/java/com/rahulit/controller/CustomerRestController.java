package com.rahulit.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rahulit.model.Customer;
import com.rahulit.service.CustomerService;

@RestController
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/customer", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String addCustomer(@RequestBody List<Customer> customers) {
		return customerService.add(customers);
	}
}
