package lannisters.devcor.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import lannisters.devcor.entity.Order;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

public class OrderMapper implements RowMapper<Order> {

	/**
	 * Creates Order and fills fields with database values recieved from
	 * ResultSet.
	 * 
	 * @param rs
	 *            ResultSet - table which has all data recieved by query.
	 * @param rowNum
	 *            number of ResultSet rows.
	 * @return Order with fields: orderId, problemType, description, roomNumber,
	 *         executionStatus, urgencyStatus, creationDate, dueDate,
	 *         authorName, authorSurname, technicianName, technicianSurname,
	 *         overdue.
	 */
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		order.setOrderId(rs.getInt("request_id"));
		// order.setProblemTypeId(rs.getInt("problem_type_id"));
		order.setProblemType(rs.getString("problem_type"));
		order.setDescription(rs.getString("description"));
		// order.setRoomId(rs.getInt("room_id"));
		order.setRoomNumber(rs.getString("room_number"));
		// order.setDeviceId(rs.getInt("device_id"));
		// order.setDeviceSerialId(rs.getString("device_serial_id"));
		// order.setExecutionStatusId(rs.getInt("execution_status_id"));
		order.setExecutionStatus(rs.getString("execution_status"));
		// order.setUrgencyStatusId(rs.getInt("urgency_status_id"));
		order.setUrgencyStatus(rs.getString("urgency_status"));
		order.setCreationDate(rs.getDate("creation_date"));
		order.setDueDate(rs.getDate("due_date"));
		// order.setAuthorId(rs.getInt("author_id"));
		order.setAuthorName(rs.getString("first_name"));
		order.setAuthorSurname(rs.getString("last_name"));
		// order.setTechnicianId(rs.getInt("technician_id"));
		order.setTechnicianName(rs.getString("first_name"));
		order.setTechnicianSurname(rs.getString("last_name"));
		order.setOverdue(rs.getString("overdue"));
		return order;
	}
}