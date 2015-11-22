package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.Order;

public interface OrdersService {

	public List<Order> getAllOrders();

	public Order getOrderById(int orderId);

	public void addOrder(Order order) throws SQLException;
	
	public void updateOrder(Order order) throws SQLException;
	
	public void deleteOrder(int orderId) throws SQLException;

	public List<Order> getALlOrdersOfUser(String email) throws SQLException;

	public List<Order> getAllOrdersOfTechnician(String email) throws SQLException;

	public List<Order> getAllOrdersSorted() throws SQLException; 
}
