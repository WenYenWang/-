package main.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dao.TourDetailsDAO;
import main.model.TourDetails;

@Service
@Transactional
public class TourDetailsServiceImpl implements TourDetailsService {

	@Autowired
	private TourDetailsDAO tourDetailsDAO;
	
	@Override
	public TourDetails getById(int id) {
		return tourDetailsDAO.getById(id);
	}

	@Override
	public void saveOrUpdate(TourDetails tourDetails) {
		tourDetailsDAO.saveOrUpdate(tourDetails);
	}

	@Override
	public void delete(int id) {
		tourDetailsDAO.delete(id);
	}

}
