package lannisters.devcor.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import lannisters.devcor.entity.Report;

import org.springframework.jdbc.core.RowMapper;

public class ReportMapperTechnicians implements RowMapper<Report> {
	public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
		Report report = new Report();
		report.setTechnicianId(rs.getInt("player_id"));
		report.setTechnician(rs.getString("last_name")+" "+rs.getString("first_name"));
		report.setCoutOfopenOrders(rs.getString("openn"));
		report.setCountOfinprogressOrders(rs.getString("Inprogress"));
		report.setCountOfunsolvableOrders(rs.getString("Unsolvable"));
		report.setCountOfincorrectOrders(rs.getString("Incorrect"));
		report.setCountOffinishedOrders(rs.getString("Finished"));
		report.setCountOffinishedwithOverdueOrders(rs.getString("FinishedwithOverdue"));
		report.setCountOfnotfinishedwithOverdueOrders(rs.getString("notFinishedwithOverdue"));
		report.setTotalOrders(rs.getString("total"));
		return report;
	}
}