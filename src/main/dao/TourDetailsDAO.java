package main.dao;

import main.model.TourDetails;

public interface TourDetailsDAO {

	public TourDetails getById(int id);
	
	public void saveOrUpdate(TourDetails tourDetails);
	
	public void delete(int id);
	
}
