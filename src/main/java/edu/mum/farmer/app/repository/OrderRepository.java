package edu.mum.farmer.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.farmer.app.domain.Order;
import edu.mum.farmer.app.domain.OrderLine;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

	@Query("Select o from Member m join m.order o join m.credentials a where a.username= :username")
	public Order findOrderForPerson(@Param("username") String username);
	
	public Order saveAndFlush(Order o);
	
}
