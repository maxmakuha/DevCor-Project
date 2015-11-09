package lannisters.devcor.service;

import java.util.List;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

import lannisters.devcor.entity.Order;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

public interface OrdersService {

	public List<Order> getAllOrders();
	
	public Order getOrderById(int order_id);
	
	public void addOrder(Order order);

	public void deleteOrder(int orderId);
}
