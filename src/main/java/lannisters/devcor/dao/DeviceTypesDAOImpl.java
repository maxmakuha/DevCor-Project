package lannisters.devcor.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lannisters.devcor.entity.DeviceType;
import lannisters.devcor.orm.DeviceTypeMapper;

@Repository
public class DeviceTypesDAOImpl implements DeviceTypesDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_SELECT_ALL_DEVICE_TYPES = "SELECT * FROM device_type";

	public List<DeviceType> getAllDeviceTypes() {
		return jdbcTemplate.query(SQL_SELECT_ALL_DEVICE_TYPES, new DeviceTypeMapper());
	}

}
