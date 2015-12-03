package lannisters.devcor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.Order;
import lannisters.devcor.orm.OrderMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersDAOImpl implements OrdersDAO {

	private static final String SQL_SELECT_ALL_ORDERS = "SELECT request.request_id, request.problem_type_id, problem_type.problem_type, request.description, room.room_id, room.room_number, device.device_id, device.device_serial_id, execution_status.execution_status_id, execution_status.execution_status, urgency_status.urgency_status_id, urgency_status.urgency_status, request.creation_date, request.due_date, author_id, author.player_email as author_email, author.first_name as author_name, author.last_name as author_surname, technician.player_id as technician_id, technician.player_email as technician_email, technician.first_name as technician_name, technician.last_name as technician_surname, SUBSTR(TO_CHAR((due_date - current_timestamp)), 1, 1) AS overdue FROM (((((((request INNER JOIN problem_type ON request.problem_type_id = problem_type.problem_type_id) INNER JOIN room ON request.room_id = room.room_id) LEFT JOIN device ON request.device_id = device.device_id) INNER JOIN execution_status ON request.execution_status_id = execution_status.execution_status_id) INNER JOIN urgency_status ON request.urgency_status_id = urgency_status.urgency_status_id) INNER JOIN player author ON request.author_id = author.player_id) INNER JOIN player technician ON request.technician_id = technician.player_id)";
	private static final String SQL_SELECT_ORDER_BY_ID = SQL_SELECT_ALL_ORDERS 
			+ " WHERE request.request_id = ?";
	private static final String SQL_INSERT_ORDER = "INSERT INTO request(problem_type_id, description, room_id, device_id, execution_status_id, urgency_status_id, creation_date, due_date, author_id, technician_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_ORDER = "UPDATE request SET problem_type_id = ?, description = ?, room_id = ?, device_id = ?, execution_status_id = ?, urgency_status_id = ?, creation_date = ?, due_date = ?, author_id = ?, technician_id = ? WHERE request_id = ?";
	private static final String SQL_DELETE_ORDER = "DELETE request WHERE request_id = ?";
	private static final String SQL_SELECT_ALL_ORDERS_OF_USER = SQL_SELECT_ALL_ORDERS
			+ " WHERE author.player_email = ? ORDER BY CASE WHEN overdue = '-' AND execution_status_id < 2 THEN 1 WHEN execution_status_id < 3 THEN 2 WHEN execution_status_id = 3 THEN 3 ELSE 4 END, due_date";
	private static final String SQL_SELECT_ALL_ORDERS_OF_TECHNICIAN = SQL_SELECT_ALL_ORDERS
			+ " WHERE technician.player_email =? ORDER BY CASE WHEN overdue = '-' AND execution_status_id < 2 THEN 1 WHEN execution_status_id < 3 THEN 2 WHEN execution_status_id = 3 THEN 3 ELSE 4 END, due_date";
	private static final String SQL_SELECT_ALL_ORDERS_SORTED = SQL_SELECT_ALL_ORDERS
			+ " ORDER BY CASE WHEN overdue = '-' AND execution_status_id < 2 THEN 1 WHEN execution_status_id < 3 THEN 2 WHEN execution_status_id = 3 THEN 3 ELSE 4 END, due_date";
	private static final String SQL_SELECT_ALL_ORDERS_OF_ROOM = SQL_SELECT_ALL_ORDERS + " WHERE request.room_id = ?";
	private static final String SQL_SELECT_ALL_ORDERS_OF_ROOM_NO_DEVICE = SQL_SELECT_ALL_ORDERS
			+ "WHERE request.device_id IS NULL AND request.execution_status_id <3 AND request.room_id = ?";
	private static final String SQL_SELECT_ALL_ORDERS_OF_ROOM_WITH_DEVICE = SQL_SELECT_ALL_ORDERS
			+ "WHERE request.device_id IS NOT NULL AND request.execution_status_id <3 AND request.room_id = ?";
	private static final String SQL_SELECT_ORDERS_BY_URGENCY = "SELECT request.request_id, request.problem_type_id, problem_type.problem_type, request.description, room.room_id, room.room_number, device.device_id, device.device_serial_id, execution_status.execution_status_id, execution_status.execution_status, urgency_status.urgency_status_id, urgency_status.urgency_status, request.creation_date, request.due_date, author_id, author.player_email as author_email, author.first_name as author_name, author.last_name as author_surname, technician.player_id as technician_id, technician.player_email as technician_email, technician.first_name as technician_name, technician.last_name as technician_surname, SUBSTR(TO_CHAR((due_date - current_timestamp)), 1, 1) AS overdue FROM (((((((request INNER JOIN problem_type ON request.problem_type_id = problem_type.problem_type_id) INNER JOIN room ON request.room_id = room.room_id) LEFT JOIN device ON request.device_id = device.device_id) INNER JOIN execution_status ON request.execution_status_id = execution_status.execution_status_id) INNER JOIN urgency_status ON request.urgency_status_id = urgency_status.urgency_status_id) INNER JOIN player author ON request.author_id = author.player_id) INNER JOIN player technician ON request.technician_id = technician.player_id) WHERE request.urgency_status_id = ?";
	private static final String SQL_SELECT_ORDERS_BY_PROBLEM = "SELECT request.request_id, request.problem_type_id, problem_type.problem_type, request.description, room.room_id, room.room_number, device.device_id, device.device_serial_id, execution_status.execution_status_id, execution_status.execution_status, urgency_status.urgency_status_id, urgency_status.urgency_status, request.creation_date, request.due_date, author_id, author.player_email as author_email, author.first_name as author_name, author.last_name as author_surname, technician.player_id as technician_id, technician.player_email as technician_email, technician.first_name as technician_name, technician.last_name as technician_surname, SUBSTR(TO_CHAR((due_date - current_timestamp)), 1, 1) AS overdue FROM (((((((request INNER JOIN problem_type ON request.problem_type_id = problem_type.problem_type_id) INNER JOIN room ON request.room_id = room.room_id) LEFT JOIN device ON request.device_id = device.device_id) INNER JOIN execution_status ON request.execution_status_id = execution_status.execution_status_id) INNER JOIN urgency_status ON request.urgency_status_id = urgency_status.urgency_status_id) INNER JOIN player author ON request.author_id = author.player_id) INNER JOIN player technician ON request.technician_id = technician.player_id) WHERE request.problem_type_id = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Order> getAllOrders() {
		return jdbcTemplate.query(SQL_SELECT_ALL_ORDERS, new OrderMapper());
	}

	@Override
	public Order getOrderById(int orderId) {
		return jdbcTemplate.queryForObject(SQL_SELECT_ORDER_BY_ID, new OrderMapper(), orderId);
	}

	@Override
	public void addOrder(Order order) throws SQLException {
		PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_INSERT_ORDER);
		ps.setInt(1, order.getProblemTypeId());
		ps.setString(2, order.getDescription());
		ps.setInt(3, order.getRoomId());
		ps.setObject(4, order.getDeviceObj());
		ps.setInt(5, order.getExecutionStatusId());
		ps.setInt(6, order.getUrgencyStatusId());
		ps.setTimestamp(7, order.getCreationDate());
		ps.setTimestamp(8, order.getDueDate());
		ps.setInt(9, order.getAuthorId());
		ps.setInt(10, order.getTechnicianId());
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void updateOrder(Order order) throws SQLException {
		PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_UPDATE_ORDER);
		ps.setInt(1, order.getProblemTypeId());
		ps.setString(2, order.getDescription());
		ps.setInt(3, order.getRoomId());
		ps.setObject(4, order.getDeviceId() == -1 ? null : order.getDeviceId());
		ps.setInt(5, order.getExecutionStatusId());
		ps.setInt(6, order.getUrgencyStatusId());
		ps.setTimestamp(7, order.getCreationDate());
		ps.setTimestamp(8, order.getDueDate());
		ps.setInt(9, order.getAuthorId());
		ps.setInt(10, order.getTechnicianId());
		ps.setInt(11, order.getOrderId());
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void deleteOrder(int orderId) throws SQLException {
		PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_DELETE_ORDER);
		ps.setInt(1, orderId);
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public List<Order> getAllOrdersOfUser(String email) throws SQLException {
		return jdbcTemplate.query(SQL_SELECT_ALL_ORDERS_OF_USER, new OrderMapper(), email);
	}

	@Override
	public List<Order> getAllOrdersOfTechnician(String email) throws SQLException {
		return jdbcTemplate.query(SQL_SELECT_ALL_ORDERS_OF_TECHNICIAN, new OrderMapper(), email);
	}

	@Override
	public List<Order> getAllOrdersSorted() throws SQLException {
		return jdbcTemplate.query(SQL_SELECT_ALL_ORDERS_SORTED, new OrderMapper());
	}

	@Override
	public List<Order> getAllOrdersOfRoom(int roomId) {
		return jdbcTemplate.query(SQL_SELECT_ALL_ORDERS_OF_ROOM, new OrderMapper(), roomId);
	}

	@Override
	public List<Order> getAllOrdersOfRoomNoDevice(int roomId) throws SQLException {
		return jdbcTemplate.query(SQL_SELECT_ALL_ORDERS_OF_ROOM_NO_DEVICE, new OrderMapper(), roomId);
	}

	@Override
	public List<Order> getAllOrdersOfRoomWithDevice(int roomId) throws SQLException {
		return jdbcTemplate.query(SQL_SELECT_ALL_ORDERS_OF_ROOM_WITH_DEVICE, new OrderMapper(), roomId);
	}

	@Override
	public List<Order> getOrdersByUrgency(int urgencyStatusId) {
		return jdbcTemplate.query(SQL_SELECT_ORDERS_BY_URGENCY, new OrderMapper(), urgencyStatusId);
	}

	@Override
	public List<Order> getOrdersByProblem(int problemTypeId) {
		return jdbcTemplate.query(SQL_SELECT_ORDERS_BY_PROBLEM, new OrderMapper(), problemTypeId);
	}
}