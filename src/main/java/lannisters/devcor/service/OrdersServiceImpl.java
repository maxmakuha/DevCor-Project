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
	public List<Order> getAllOrdersOfUser(String email) {
		return ordersDao.getAllOrdersOfUser(email);
	}

	@Override
	public List<Order> getAllOrdersOfTechnician(String email) {
		return ordersDao.getAllOrdersOfTechnician(email);
	}

	@Override
	public List<Order> getAllOrdersSorted() {
		return ordersDao.getAllOrdersSorted();
	}

	@Override
	public List<Order> getAllOrdersOfRoom(int roomId) {
		return ordersDao.getAllOrdersOfRoom(roomId);
	}

	@Override
	public List<Order> getAllOrdersOfRoomNoDevice(int roomId){
		return ordersDao.getAllOrdersOfRoomNoDevice(roomId);
	}

	@Override
	public List<Order> getAllOrdersOfRoomWithDevice(int roomId) {
		return ordersDao.getAllOrdersOfRoomWithDevice(roomId);
	}

	@Override
	public List<Order> getOrdersByUrgency(int urgencyStatusId) {
		return ordersDao.getOrdersByUrgency(urgencyStatusId);
	}

	@Override
	public List<Order> getOrdersByProblem(int problemTypeId) {
		return ordersDao.getOrdersByProblem(problemTypeId);
	}

	@Override
	public boolean checkUrgencyStatusOrders(int urgencyStatusId) {
		boolean existence;
		List<Order> orders = ordersDao.getOrdersByUrgency(urgencyStatusId);
		if (orders.isEmpty())
			existence = false;
		else
			existence = true;
		return existence;
	}

	@Override
	public boolean checkProblemTypeOrders(int problemTypeId) {
		boolean existence;
		List<Order> orders = ordersDao.getOrdersByProblem(problemTypeId);
		if (orders.isEmpty())
			existence = false;
		else
			existence = true;
		return existence;
	}
}