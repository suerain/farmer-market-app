package edu.mum.farmer.app.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.farmer.app.domain.Order;
import edu.mum.farmer.app.domain.OrderLine;
import edu.mum.farmer.app.repository.OrderRepository;
import edu.mum.farmer.app.service.IOrderService;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

	@Autowired
	OrderRepository orderRepository;


	@Override
	public Order saveOrder(Order order) {
		return orderRepository.saveAndFlush(order);
	}


	@Override
	public Order getOrderForPerson(String username) {
		return orderRepository.findOrderForPerson(username);
	}


	@Override
	public void deleteOrder(Order order) {
		orderRepository.delete(order);
	}



	/*@Override
	public Order getOrderForPerson(long id) {
		return orderRepository.findOrderForPerson(id);
	}*/

}
