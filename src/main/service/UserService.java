package main.service;

import java.security.Principal;

import main.model.Customer;
import main.model.User;

public interface UserService {
	
//	public void getByLogin(String login);
	
	public void createNewAccount(User user);
	
	public boolean loginExists(String login);
	
	public void saveOrUpdate(User user);
	
	public void addCustomerId(Customer customer,Principal principal);
}
