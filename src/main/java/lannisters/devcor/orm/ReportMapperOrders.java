package lannisters.devcor.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import lannisters.devcor.entity.Report;

import org.springframework.jdbc.core.RowMapper;

public class ReportMapperOrders implements RowMapper<Report> {
	public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
		Report report = new Report();
		report.setReportId(rs.getInt("request_id"));
		report.setCreatingDate(rs.getTimestamp("creation_date"));
		report.setDueDate(rs.getTimestamp("due_date"));
		report.setProblemType(rs.getString("problem_type"));
		report.setProblemDescription(rs.getString("description"));
		report.setRoomNumber(rs.getString("room_number"));
		report.setSerialNumber(rs.getString("device_serial_id"));
		report.setExecutionStatus(rs.getString("execution_status"));
		report.setUrgencyStatus(rs.getString("urgency_status"));
		report.setAuthor(rs.getString("author_name") + " " + rs.getString("author_surname"));
		report.setOverdue(rs.getString("overdue"));
		report.setTechnician(rs.getString("technician_name") + " " + rs.getString("technician_surname"));
		return report;
	}
}