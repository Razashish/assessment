package com.example.assessment.service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.assessment.exception.CustomerNotFoundException;
import com.example.assessment.exception.InvalidEmailException;
import com.example.assessment.model.Customer;
import com.example.assessment.model.Item;
import com.example.assessment.repository.CustomerRepository;

@Service
public class CustomerService {

	public static final String REGEX = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

	@Autowired
	private CustomerRepository repository;

	public ResponseEntity<?> createCustomer(Customer customer) {

		if (!validateEmail(customer.getEmail())) {
			throw new InvalidEmailException();
		}
		customer = repository.save(customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
	}

	public ResponseEntity<?> updateCustomer(int id, Customer customer) {
		if (!validateEmail(customer.getEmail())) {
			throw new InvalidEmailException();
		}
		Customer existingCustomer = repository.findById(id).orElseThrow(() -> new CustomerNotFoundException());
		existingCustomer.setEmail(customer.getEmail());
		existingCustomer.setNumber(customer.getNumber());
		existingCustomer.setName(customer.getName());
		repository.save(existingCustomer);

		return new ResponseEntity<Customer>(existingCustomer, HttpStatus.OK);
	}

	public ResponseEntity<?> deleteCustomer(int id) {
		repository.deleteById(id);
		return new ResponseEntity<String>("Customer Deleted", HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<?> getAllCustomers() {
		List<Customer> customers = (List<Customer>) repository.findAll();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	public ResponseEntity<?> getCustomer(int id) {
		Customer customer = repository.findById(id).orElseThrow(() -> new CustomerNotFoundException());
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	private boolean validateEmail(String email) {
		return Pattern.compile(REGEX).matcher(email).matches();

	}

	public double calculatePurchaseAmount(Map<Integer, Item> item, int discount) {
		double totalAmount = 0.0;
		for (Map.Entry<Integer, Item> entry : item.entrySet()) {
			totalAmount += (entry.getKey() * entry.getValue().getItemPrice());
		}
		if (discount <= 0) {
			return totalAmount;
		}
		return totalAmount - (totalAmount * discount / 100);
	}
}
