package lannisters.devcor.dao;

import java.util.List;
import lannisters.devcor.entity.Order;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

public interface OrdersDAO {

	public List<Order> getAllOrders();
	
	public Order getOrderById(int order_id);

	public void addOrder(Order order);

	public void deleteOrder(int orderId);
}