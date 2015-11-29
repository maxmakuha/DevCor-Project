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

	public List<Order> getAllOrdersOfUser(String email) throws SQLException;

	public List<Order> getAllOrdersOfTechnician(String email) throws SQLException;

	public List<Order> getAllOrdersSorted() throws SQLException;
	
	public List<Order> getAllOrdersOfRoom(int roomId);

	public List<Order> getAllOrdersOfRoomNoDevice(int roomId) throws SQLException;

	public List<Order> getAllOrdersOfRoomWithDevice(int roomId) throws SQLException;
	
	public List<Order> getOrdersByUrgency(int urgencyStatusId);
	
	public List<Order> getOrdersByProblem(int problemTypeId);
}