package lannisters.devcor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lannisters.devcor.dao.OrdersDAO;
import lannisters.devcor.entity.Order;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

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

	public void addOrder(Order order) {
		ordersDao.addOrder(order);
	}

	public void deleteOrder(int orderId) {
		ordersDao.deleteOrder(orderId);
	}
}