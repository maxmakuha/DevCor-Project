package lannisters.devcor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lannisters.devcor.entity.DeviceType;
import lannisters.devcor.orm.DeviceTypeMapper;
import lannisters.devcor.orm.ProblemTypeMapper;

@Repository
public class DeviceTypesDAOImpl implements DeviceTypesDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_SELECT_ALL_DEVICE_TYPES = "SELECT * FROM device_type";
	private static final String SQL_SELECT_DEVICE_TYPE_BY_ID = "SELECT device_type_id, device_type FROM device_type WHERE device_type.device_type_id=?";
	private static final String SQL_UPDATE_DEVICE_TYPE = "UPDATE device_type SET device_type = ? WHERE device_type_id = ?";
	private static final String SQL_INSERT_DEVICE_TYPE = "INSERT INTO device_type( device_type_id, device_type) VALUES (?, ?)";
	private static final String SQL_DELETE_DEVICE_TYPE = "DELETE device_type WHERE device_type_id=?";

	
	public List<DeviceType> getAllDeviceTypes() {
		return jdbcTemplate.query(SQL_SELECT_ALL_DEVICE_TYPES, new DeviceTypeMapper());
	}

	@Override
	public DeviceType getDeviceTypeById(int deviceTypeId) {

		return jdbcTemplate.queryForObject(SQL_SELECT_DEVICE_TYPE_BY_ID, new DeviceTypeMapper(), deviceTypeId);
	}

	@Override
	public void updateDeviceType(DeviceType deviceType) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_UPDATE_DEVICE_TYPE);
			ps.setString(1, deviceType.getDeviceType());
			ps.setInt(2, deviceType.getDeviceTypeId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	@Override
	public void addDeviceType(DeviceType deviceType) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_INSERT_DEVICE_TYPE);
			ps.setInt(1, deviceType.getDeviceTypeId());
			ps.setString(2, deviceType.getDeviceType());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	@Override
	public void deleteDeviceType(int deviceType) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_DELETE_DEVICE_TYPE);
			ps.setInt(1, deviceType);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

}
