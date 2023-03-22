package main.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.CartDetail;
import main.repository.CartDetailRepository;

@Service
@Transactional
public class CartDetailServiceImpl implements CartDetailService{

	@Autowired
	private CartDetailRepository cartDetailRepository;
	
	@Override
	public CartDetail getById(long cartDetailId) {
		return cartDetailRepository.findById(cartDetailId).orElse(null);
	}

	@Override
	public List<CartDetail> getAll() {
		return cartDetailRepository.findAll();
	}

	@Override
	public void delete(long cartDetailId) {
		cartDetailRepository.deleteById(cartDetailId);
	}

	@Override
	public List<CartDetail> getByCartId(long cartId) {
		return cartDetailRepository.getByCartId(cartId);
	}

	@Override
	public void saveOrUpdate(CartDetail cartDetail) {
		cartDetailRepository.save(cartDetail);
	}

	@Override
	public Optional<CartDetail> getProductsByProductId(long productId) {
		Optional<CartDetail> data = cartDetailRepository.findById(productId);
		return data;
	}
	
}
