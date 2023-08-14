package com.example.assessment.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.assessment.model.Item;
import com.example.assessment.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest 
{
	@InjectMocks
	  CustomerService customerService;

	  @Mock
	  CustomerRepository repository;
	  
	  @Test
	  public void calculatePurchaseAmountTest()
	  {
		  Map<Integer,Item> item = new HashMap<>();
		  int discount = 10;
		  Item item1 = new Item("Laptop", 20000.0);
		  Item item2 = new Item("Mobile", 10000.0);
		  
		  item.put(2, item1);
		  item.put(3, item2);
		  
		  
		  double totalAmount = customerService.calculatePurchaseAmount(item, discount);
		  assertThat(totalAmount).isEqualTo(63000.0);
		  
		  
	  }
	
}
