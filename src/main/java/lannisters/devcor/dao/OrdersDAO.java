package lannisters.devcor.dao;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.Order;

public interface OrdersDAO {

	public List<Order> getAllOrders();

	public Order getOrderById(int orderId);

	public void addOrder(Order order) throws SQLException;
	
	public void updateOrder(Order order) throws SQLException;

	public void deleteOrder(int orderId) throws SQLException;
}