package com.rahulit.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rahulit.model.Customer;
import com.rahulit.service.CustomerService;

/**
 * This class is used to handle user requests
 * 
 * @author Ashok
 *
 */
@RestController
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	/**
	 * This method is used to Customer records in post request
	 * @param customers
	 * @return
	 */
	@PostMapping(value = "/customer", 
			consumes = { 
					MediaType.APPLICATION_JSON, 
					MediaType.APPLICATION_XML 
			}
	)
	public String addCustomer(@RequestBody List<Customer> customers) {
		return customerService.add(customers);
	}
}
