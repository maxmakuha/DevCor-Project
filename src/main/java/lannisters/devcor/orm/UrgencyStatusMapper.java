package lannisters.devcor.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import lannisters.devcor.entity.UrgencyStatus;

import org.springframework.jdbc.core.RowMapper;


public class UrgencyStatusMapper implements RowMapper<UrgencyStatus> {

	public UrgencyStatus mapRow(ResultSet resSet, int arg1) throws SQLException {
		return new UrgencyStatus(resSet.getInt("urgency_status_id"), resSet.getString("urgency_status"));
	}

}
