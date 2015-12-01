package lannisters.devcor.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import lannisters.devcor.entity.Order;

import org.springframework.jdbc.core.RowMapper;

public class OrderMapper implements RowMapper<Order> {

	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		order.setOrderId(rs.getInt("request_id"));
		order.setProblemTypeId(rs.getInt("problem_type_id"));
		order.setProblemType(rs.getString("problem_type"));
		order.setDescription(rs.getString("description"));
		order.setRoomId(rs.getInt("room_id"));
		order.setRoomNumber(rs.getString("room_number"));
		if(rs.getObject("device_id") == null){
			order.setDeviceId(-1);
			order.setDeviceSerialId("Not selected");
		} else {
			order.setDeviceId(rs.getInt("device_id"));
			order.setDeviceSerialId(rs.getString("device_serial_id"));
		}
		order.setExecutionStatusId(rs.getInt("execution_status_id"));
		order.setExecutionStatus(rs.getString("execution_status"));
		order.setUrgencyStatusId(rs.getInt("urgency_status_id"));
		order.setUrgencyStatus(rs.getString("urgency_status"));
		order.setCreationDate(rs.getTimestamp("creation_date"));
		order.setDueDate(rs.getTimestamp("due_date"));
		order.setAuthorId(rs.getInt("author_id"));
		order.setAuthorEmail(rs.getString("author_email"));
		order.setAuthorName(rs.getString("author_name"));
		order.setAuthorSurname(rs.getString("author_surname"));
		order.setTechnicianId(rs.getInt("technician_id"));
		order.setTechnicianEmail(rs.getString("technician_email"));
		order.setTechnicianName(rs.getString("technician_name"));
		order.setTechnicianSurname(rs.getString("technician_surname"));
		order.setOverdue(rs.getString("overdue").equals("-"));
		return order;
	}
}