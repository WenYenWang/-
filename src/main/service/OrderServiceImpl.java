package main.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import main.model.Order;
import main.model.User;
import main.repository.OrderRepository;
import main.repository.UserRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Order> getAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order getById(long id) {
		return orderRepository.getOne(id);
	}

	@Override
	public void saveOrUpdate(Order order) {
		orderRepository.save(order);
	}

	@Override
	public void delete(long id) {
		orderRepository.deleteById(id);
	}
//	@Override
//	public Order getByIdWithComments(long id) {
//		return orderRepository.getByIdWithComments(id);
//	}
//	
//	@Override
//	public void addUserToOrder(long id,long userId) {
//		Order order = getById(id);
//		if(order.getUsers()==null) {
//			order.setUsers(new ArrayList<>());
//		}
//		User user = userRepository.getOne(userId);
//		if(user != null) {
//			order.getUsers().add(user);
//			saveOrUpdate(order);
//		}
//	}

@Override
public Order getByIdWithComments(long id) {
	// TODO Auto-generated method stub
	return null;
}
	

}
