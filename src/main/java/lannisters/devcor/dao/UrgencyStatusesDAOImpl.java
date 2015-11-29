package lannisters.devcor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lannisters.devcor.entity.UrgencyStatus;
import lannisters.devcor.orm.UrgencyStatusMapper;

@Repository
public class UrgencyStatusesDAOImpl implements UrgencyStatusesDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_SELECT_ALL_URGENCY_STATUS = "SELECT * FROM urgency_status";
	private static final String SQL_SELECT_URGENCY_STATUS_BY_ID = "SELECT urgency_status_id, urgency_status,minutes FROM urgency_status WHERE urgency_status.urgency_status_id=?";
	private static final String SQL_UPDATE_URGENCY_STATUS = "UPDATE urgency_status SET urgency_status = ?, minutes = ? WHERE urgency_status_id = ?";
	private static final String SQL_INSERT_URGENCY_STATUS = "INSERT INTO urgency_status(urgency_status, minutes) VALUES (?,?)";
	private static final String SQL_DELETE_URGENCY_STATUS = "DELETE urgency_status WHERE urgency_status_id=?";
	private static final String SQL_SELECT_MINUTES_BY_ID = "SELECT urgency_status.minutes FROM urgency_status WHERE urgency_status.urgency_status_id = ?";
	private static final String SQL_SELECT_URGENCY_STATUS_BY_TITLE = "SELECT urgency_status_id, urgency_status,minutes FROM urgency_status WHERE urgency_status.urgency_status=?";

	@Override
	public List<UrgencyStatus> getAllUrgencyStatuses() {
		return jdbcTemplate.query(SQL_SELECT_ALL_URGENCY_STATUS, new UrgencyStatusMapper());
	}

	@Override
	public UrgencyStatus getUrgencyStatusById(int urgencyStatus) {
		return jdbcTemplate.queryForObject(SQL_SELECT_URGENCY_STATUS_BY_ID, new UrgencyStatusMapper(), urgencyStatus);
	}

	@Override
	public void updateUrgencyStatus(UrgencyStatus urgencyStatus) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_UPDATE_URGENCY_STATUS);
			ps.setString(1, urgencyStatus.getUrgencyStatus());
			ps.setInt(2, urgencyStatus.getMinutes());
			ps.setInt(3, urgencyStatus.getUrgencyStatusId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	@Override
	public void addUrgencyStatus(UrgencyStatus urgencyStatus) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_INSERT_URGENCY_STATUS);

			ps.setString(1, urgencyStatus.getUrgencyStatus());
			ps.setInt(2, urgencyStatus.getMinutes());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	@Override
	public void deleteUrgencyStatus(int urgencyStatus) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_DELETE_URGENCY_STATUS);
			ps.setInt(1, urgencyStatus);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	@Override
	public int getUrgencyStatusMinutes(int urgencyStatusId) {
		return jdbcTemplate.queryForInt(SQL_SELECT_MINUTES_BY_ID, urgencyStatusId);
	}

	@Override
	public UrgencyStatus getUrgencyStatusByTitle(String title) {
		return jdbcTemplate.queryForObject(SQL_SELECT_URGENCY_STATUS_BY_TITLE, new UrgencyStatusMapper(), title);
	}
}
