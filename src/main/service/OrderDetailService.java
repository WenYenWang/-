package main.service;

import main.model.OrderDetail;

public interface OrderDetailService {

	public OrderDetail getById(long id);
	
	public void saveOrUpdate(OrderDetail orderDetail);
	
	public void delete(long id);
	
}
