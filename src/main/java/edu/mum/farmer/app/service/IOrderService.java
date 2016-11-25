package edu.mum.farmer.app.service;

import java.util.List;

import edu.mum.farmer.app.domain.Order;
import edu.mum.farmer.app.domain.OrderLine;

public interface IOrderService {
	public Order saveOrder(Order order);

	public Order getOrderForPerson(String username);

	public void deleteOrder(Order order);

}
