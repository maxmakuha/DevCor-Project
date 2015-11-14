package lannisters.devcor.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lannisters.devcor.entity.UrgencyStatus;
import lannisters.devcor.orm.UrgencyStatusMapper;

@Repository
public class UrgencyStatusesDAOImpl implements UrgencyStatusesDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String SQL_SELECT_ALL_PROBLEM_TYPES = "SELECT * FROM urgency_status";
	
	public List<UrgencyStatus> getAllUrgencyStatuses() {
		return jdbcTemplate.query(SQL_SELECT_ALL_PROBLEM_TYPES, new UrgencyStatusMapper());
	}

}
