package com.example.assessment.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.assessment.model.Customer;
import com.example.assessment.service.CustomerService;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

  @InjectMocks
  CustomerController customerController;

  @Mock
  CustomerService service;

  @Test
  public void getAllCustomerTest() throws Exception {
    Customer customer1 = new Customer(1, "A", "a@gmail.com", 12345);
    Customer customer2 = new Customer(2, "B", "b@gmail.com", 1234455);
    List<Customer> custList = Arrays.asList(customer1, customer2);
    ResponseEntity response = new ResponseEntity<>(custList, HttpStatus.OK);

    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    when(service.getAllCustomers()).thenReturn(response);

    ResponseEntity<?> responseEntity = customerController.getAllCustomers();
    List<Customer> responseBody = (List<Customer>) responseEntity.getBody();

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseBody).isEqualTo(custList);
  }

  @Test
  public void getCustomerByIdTest() throws Exception {
    Customer customer = new Customer(1, "A", "a@gmail.com", 12345);
    ResponseEntity response = new ResponseEntity<>(customer, HttpStatus.OK);

    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    when(service.getCustomer(anyInt())).thenReturn(response);

    ResponseEntity<?> responseEntity = customerController.getCustomer(1);
    Customer responseBody = (Customer) responseEntity.getBody();

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseBody).isEqualTo(customer);
  }

  @Test
  public void createCustomerTest() throws Exception {

    Customer customer = new Customer(1, "A", "a@gmail.com", 12345);
    ResponseEntity response = new ResponseEntity<>(customer, HttpStatus.CREATED);

    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    when(service.createCustomer(any(Customer.class))).thenReturn(response);

    ResponseEntity<?> responseEntity = customerController.createCustomer(customer);
    Customer responseBody = (Customer) responseEntity.getBody();

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(responseBody).isEqualTo(customer);
  }

  @Test
  public void deleteCustomerTest() throws Exception {
    ResponseEntity response = new ResponseEntity<>(HttpStatus.NO_CONTENT);

    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    when(service.deleteCustomer(anyInt())).thenReturn(response);

    ResponseEntity<?> responseEntity = customerController.deleteCustomer(1);
    Customer responseBody = (Customer) responseEntity.getBody();

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
  }

  @Test
  public void updateCustomerTest() throws Exception {

    Customer customer = new Customer(1, "A", "a@gmail.com", 12345);
    ResponseEntity response = new ResponseEntity<>(customer, HttpStatus.OK);

    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    when(service.updateCustomer(anyInt(),any(Customer.class))).thenReturn(response);

    ResponseEntity<?> responseEntity = customerController.updateCustomer(1,customer);
    Customer responseBody = (Customer) responseEntity.getBody();

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseBody).isEqualTo(customer);
  }
}
