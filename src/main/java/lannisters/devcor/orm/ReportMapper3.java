package lannisters.devcor.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import lannisters.devcor.entity.Report;
import org.springframework.jdbc.core.RowMapper;
 
public class ReportMapper3 implements RowMapper<Report> {
	
	public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
		Report report = new Report();
		report.setSerialNumber(rs.getString("serId"));
		report.setDeviseType(rs.getString("devType"));
		report.setRoomNumber(rs.getString("roomNum"));
		report.setOrderQuantity(rs.getString("quantity"));
		return report;
	}
}