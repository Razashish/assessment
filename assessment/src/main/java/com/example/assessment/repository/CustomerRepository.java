package com.example.assessment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.assessment.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>
{

}
