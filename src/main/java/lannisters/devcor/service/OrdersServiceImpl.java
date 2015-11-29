package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lannisters.devcor.dao.OrdersDAO;
import lannisters.devcor.entity.Order;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDAO ordersDao;

	@Override
	public List<Order> getAllOrders() {
		return ordersDao.getAllOrders();
	}

	@Override
	public Order getOrderById(int orderId) {
		return ordersDao.getOrderById(orderId);
	}

	@Override
	public void addOrder(Order order) throws SQLException {
		ordersDao.addOrder(order);
	}

	@Override
	public void updateOrder(Order order) throws SQLException {
		ordersDao.updateOrder(order);
	}

	@Override
	public void deleteOrder(int orderId) throws SQLException {
		ordersDao.deleteOrder(orderId);
	}

	@Override
	public List<Order> getALlOrdersOfUser(String email) throws SQLException {
		return ordersDao.getAllOrdersOfUser(email);
	}

	@Override
	public List<Order> getAllOrdersOfTechnician(String email) throws SQLException {
		return ordersDao.getAllOrdersOfTechnician(email);
	}

	@Override
	public List<Order> getAllOrdersSorted() throws SQLException {
		return ordersDao.getAllOrdersSorted();
	}

	@Override
	public List<Order> getAllOrdersOfRoom(int roomId) {
		return ordersDao.getAllOrdersOfRoom(roomId);
	}

	@Override
	public List<Order> getAllOrdersOfRoomNoDevice(int roomId) throws SQLException {
		return ordersDao.getAllOrdersOfRoomNoDevice(roomId);
	}

	@Override
	public List<Order> getAllOrdersOfRoomWithDevice(int roomId) throws SQLException {
		return ordersDao.getAllOrdersOfRoomWithDevice(roomId);
	}
}