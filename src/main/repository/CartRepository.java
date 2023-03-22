package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	
	@Query("from Cart c where c.customerId = :customerId")
	Cart getByCustomerId(@Param("customerId")long customerId);
	
}
