package lannisters.devcor.dao;

import java.util.List;
import lannisters.devcor.entity.Order;

public interface OrdersDAO {

	public List<Order> getAllOrders();

	public Order getOrderById(int orderId);

	public void addOrder(Order order);

	public void deleteOrder(int orderId);
}