package main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import main.model.CartDetail;
import main.model.Product;

@Service
public interface CartDetailService {

	public CartDetail getById(long cartDetailId);
	
	public List<CartDetail> getAll();

	public List<CartDetail> getByCartId(long cartId);
	
	public void saveOrUpdate(CartDetail cartDetail);
	
	public void delete(long cardId);

	public Optional<CartDetail> getProductsByProductId(long productId);
}
