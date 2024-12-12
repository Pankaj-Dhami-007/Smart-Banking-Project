package com.bank.dao;

import java.util.List;

import com.bank.models.Customer;

public interface CustomerDao {

	boolean registerCustomer(Customer newCustomer);
	boolean updateCustomer(Customer customer);
	Customer getCustomerById(Long id);
	Customer getCustomerByMobileNo(Long mobNo);
	boolean deleteCustomer(Long id);
	List<Customer> getAllCustomers();
	String[] getAllColumns();	
	
}
