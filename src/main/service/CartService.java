package main.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;

import main.model.Cart;
import main.model.CartForm;

@Service
public interface CartService {

	public List<Cart> getAll();
	
	public Cart getById(long cartId);
	
	public void saveOrUpdate(Cart cart);
	
	public void delete(long cartId);
	
	public Cart getByCustomerId(long customerId);
	
	public void addCartDetail(CartForm cartForm,Principal principal);
	
}
