package main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import main.model.Product;

@Service
public interface ProductService {

	public List<Product> getAll();
	
	public Product getById(long productId);
	
	public void saveOrUpdate(Product product);
	
	public void delete(long productId);
	
}
