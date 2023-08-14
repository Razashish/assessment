package com.example.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.assessment.model.Customer;
import com.example.assessment.service.CustomerService;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping("/customer")
	public ResponseEntity<?> getAllCustomers()
	{
		return service.getAllCustomers();
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable int id)
	{
		return service.getCustomer(id);
	}
	
	@PostMapping("/customer")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer)
	{
		return service.createCustomer(customer);
	}
	
	@PutMapping("/customer/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable int id, @RequestBody Customer customer)
	{
		return service.updateCustomer(id,customer);
	}
	
	@DeleteMapping("/customer")
	public ResponseEntity<?> deleteCustomer(@RequestParam int id)
	{
		return service.deleteCustomer(id);
	}
	
	

}
