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

	public List<Order> getAllOrders() {
		return ordersDao.getAllOrders();
	}

	public Order getOrderById(int orderId) {
		return ordersDao.getOrderById(orderId);
	}

	public void addOrder(Order order) throws SQLException {
		ordersDao.addOrder(order);
	}

	public void updateOrder(Order order) throws SQLException {
		ordersDao.updateOrder(order);
	}

	public void deleteOrder(int orderId) throws SQLException {
		ordersDao.deleteOrder(orderId);
	}
}