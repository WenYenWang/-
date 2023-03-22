package main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.Product;
import main.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository prouctRepository;
	
	@Override
	public List<Product> getAll() {
		return prouctRepository.findAll();
	}

	@Override
	public Product getById(long productId) {
		return prouctRepository.findById(productId).orElse(null);
	}

	@Override
	public void saveOrUpdate(Product product) {
		prouctRepository.saveAndFlush(product);
	}

	@Override
	public void delete(long productId) {
		prouctRepository.deleteById(productId);
	}
}
