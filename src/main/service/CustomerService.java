package main.service;

import java.util.List;

import main.model.Customer;


public interface CustomerService {

	public List<Customer> findAll();
	
	public Customer getById(long cusetomerId);
	
	public void saveOrUpdate(Customer customer);
	
	public void delete(long customerId);
	
	public Customer getByUserId(long userId);

//	public void addUserToCustomer(long customerId, long userId);

	
}
