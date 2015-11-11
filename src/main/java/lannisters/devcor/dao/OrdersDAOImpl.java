package lannisters.devcor.dao;

import java.util.List;

import lannisters.devcor.entity.Order;
import lannisters.devcor.orm.OrderMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersDAOImpl implements OrdersDAO {

	private static final String SQL_SELECT_ALL_ORDERS = "SELECT request.request_id, problem_type.problem_type, request.description, room.room_number, device.device_serial_id, execution_status.execution_status, urgency_status.urgency_status, request.creation_date, request.due_date, author.first_name, author.last_name, technician.first_name, technician.last_name, request.overdue FROM (((((((request INNER JOIN problem_type ON request.problem_type_id = problem_type.problem_type_id) INNER JOIN room ON request.room_id = room.room_id) INNER JOIN device ON request.device_id = device.device_id) INNER JOIN execution_status ON request.execution_status_id = execution_status.execution_status_id) INNER JOIN urgency_status ON request.urgency_status_id = urgency_status.urgency_status_id) INNER JOIN player author ON request.author_id = author.player_id) INNER JOIN player technician ON request.technician_id = technician.player_id)";
	private static final String SQL_SELECT_ORDER_BY_ID = "SELECT request.request_id, problem_type.problem_type, request.description, room.room_number, device.device_serial_id, execution_status.execution_status, urgency_status.urgency_status, request.creation_date, request.due_date, author.first_name, author.last_name, technician.first_name, technician.last_name, request.overdue FROM (((((((request INNER JOIN problem_type ON request.problem_type_id = problem_type.problem_type_id) INNER JOIN room ON request.room_id = room.room_id) INNER JOIN device ON request.device_id = device.device_id) INNER JOIN execution_status ON request.execution_status_id = execution_status.execution_status_id) INNER JOIN urgency_status ON request.urgency_status_id = urgency_status.urgency_status_id) INNER JOIN player author ON request.author_id = author.player_id) INNER JOIN player technician ON request.technician_id = technician.player_id) WHERE request.request_id=?";
	private static final String SQL_INSERT_ORDER = "INSERT INTO request(problem_type_id, description, room_id, device_id, execution_status_id, urgency_status_id, creation_date, due_date, author_id, technician_id, overdue) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE_ORDER = "DELETE request WHERE request_id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Order> getAllOrders() {
		return jdbcTemplate.query(SQL_SELECT_ALL_ORDERS, new OrderMapper());
	}

	public Order getOrderById(int orderId) {
		return jdbcTemplate.queryForObject(SQL_SELECT_ORDER_BY_ID,
				new OrderMapper(), orderId);
	}

	public void addOrder(Order order) {
		jdbcTemplate.update(SQL_INSERT_ORDER, order);
	}

	public void deleteOrder(int orderId) {
		jdbcTemplate.update(SQL_DELETE_ORDER, orderId);
	}
}