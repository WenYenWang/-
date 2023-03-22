package main.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import main.model.Cart;
import main.model.CartDetail;
import main.model.Customer;
import main.model.Role;
import main.model.TourDetails;
import main.model.User;
import main.repository.RoleRepository;
import main.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private CustomerService customerService;
	
//	@Override
//	public void getByLogin(String login) {
//		 return	userRepository.findByLogin(principal.getName());
//	}

	@Override
	public void createNewAccount(User user) {
		user.setEnabled(true);
		user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
		userRepository.save(user);
		
		Role role = new Role();
		role.setLogin(user.getLogin());
		role.setRole("ROLE_CLIENT");
		roleRepository.save(role);
	}

	@Override
	public boolean loginExists(String login) {
		return userRepository.existsByLogin(login);
	}

	@Override
	public void saveOrUpdate(User user) {
		userRepository.saveAndFlush(user);
	}

	@Override
	public void addCustomerId(Customer customer,Principal principal) {
		User user = userRepository.findByLogin(principal.getName());
		if(user.getCustomer() == null) {
		    user.setCustomer(customer);
		    customer.setUserId(user.getUserId());
			customerService.saveOrUpdate(customer);
			userRepository.saveAndFlush(user);
		}
	}
}
